package xy.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static xy.context.RedisContext.USER_ROLE;
import static xy.context.SystemContent.APIKEY;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeepSeekWebSocketConfiguration extends TextWebSocketHandler {


    private final ObjectMapper mapper = new ObjectMapper();

    private final StringRedisTemplate redisTemplate;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        log.info("收到客户端消息: {}", message.getPayload());

        CompletableFuture.runAsync(() -> {
            Map<String, String> payloadMap = parsePayload(message.getPayload(), session);
            System.out.println(payloadMap);
            if (payloadMap == null) return;

            String prompt = payloadMap.get("prompt");
            String courseId = payloadMap.get("courseId");
            String role = getRoleFromRedis(courseId);

            String requestBody = buildRequestBody(role, prompt);
            System.out.println(requestBody);
            sendToDeepSeekAndStream(requestBody, session);
        });
    }

    /**
     * 从 JSON 中提取 prompt 和 courseId
     */
    private Map<String, String> parsePayload(String payload, WebSocketSession session) {
        try {
            JsonNode node = mapper.readTree(payload);
            String prompt = node.has("prompt") ? node.get("prompt").asText() : null;
            String courseId = node.has("courseId") ? node.get("courseId").asText() : null;

            if (prompt == null || prompt.isBlank()) {
                session.sendMessage(new TextMessage("错误：缺少 prompt 内容"));
                return null;
            }

            if (courseId != null && !courseId.isBlank()) {
                return Map.of("prompt", prompt, "courseId", courseId);
            } else {
                // 只返回 prompt
                return Map.of("prompt", prompt);
            }
        } catch (Exception e) {
            try {
                session.sendMessage(new TextMessage("错误：消息格式不正确"));
            } catch (IOException ignored) {}
            return null;
        }
    }


    /**
     * 从 Redis 中查角色，如果查不到返回默认值
     */
    private String getRoleFromRedis(String courseId) {
        if (courseId == null) return "你是一名老师";
        String redisValue = redisTemplate.opsForValue().get(USER_ROLE + courseId);
        return (redisValue != null && !redisValue.isBlank()) ? redisValue : "你是一名老师";
    }

    /**
     * 构造请求体（注意转义 prompt 引号）
     */
    private String buildRequestBody(String role, String prompt) {
        return String.format("""
                {
                  "model": "deepseek-chat",
                  "messages": [
                    {"role": "system", "content": "%s"},
                    {"role": "user", "content": "%s"}
                  ],
                  "stream": true
                }
                """, role, prompt.replace("\"", "\\\""));
    }

    /**
     * 发请求到 DeepSeek 并把响应推送给前端 WebSocket
     */
    private final HttpClient client = HttpClient.newHttpClient();

    private void sendToDeepSeekAndStream(String requestBody, WebSocketSession session) {
        log.info("调用 DeepSeek 接口，请求体: {}", requestBody);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.deepseek.com/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + APIKEY)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            log.info("DeepSeek 接口响应状态码: {}", response.statusCode());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()))) {
                String line;
                while ((line = reader.readLine()) != null && session.isOpen()) {
                    if (line.startsWith("data: ")) {
                        String chunkJson = line.substring(6).trim();

                        // 流结束标志
                        if ("[DONE]".equals(chunkJson)) {
                            log.info("接收到结束标志 [DONE]");
                            break;
                        }

                        try {
                            JsonNode chunkNode = mapper.readTree(chunkJson);
                            JsonNode contentNode = chunkNode
                                    .path("choices")
                                    .get(0)
                                    .path("delta")
                                    .path("content");

                            if (!contentNode.isMissingNode()) {
                                String content = contentNode.asText();
                                log.info("发送给前端数据块: {}", content);
                                session.sendMessage(new TextMessage(content));
                            }
                        } catch (Exception e) {
                            log.error("解析流分片 JSON 失败", e);
                        }
                    }
                }
                // 告诉前端流结束
                session.sendMessage(new TextMessage("[DONE]"));
            } catch (IOException e) {
                log.error("读取 DeepSeek 响应时发生异常", e);
                session.sendMessage(new TextMessage("ERROR: " + e.getMessage()));
            }

        } catch (Exception e) {
            log.error("调用 DeepSeek 接口失败", e);
            try {
                session.sendMessage(new TextMessage("ERROR: " + e.getMessage()));
            } catch (IOException ignored) {}
        }
    }
}
