package subject.xy;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import xy.XyApplication;

@SpringBootTest(classes = XyApplication.class)
class XyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    @PostConstruct
    public void testRedis() {
        try {
            redisTemplate.opsForValue().set("test", "ok");
            String value = redisTemplate.opsForValue().get("test");
            System.out.println("Redis test value: " + value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
