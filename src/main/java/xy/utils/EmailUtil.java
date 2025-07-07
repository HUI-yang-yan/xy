package xy.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

import static xy.context.EmailContext.MAX_LENGTH;


@Slf4j
@Component
public class EmailUtil {
    private final JavaMailSender mailSender;

    public EmailUtil(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    // 发送验证码到指定邮箱，返回验证码字符串
    public String sendVerificationCode(String toEmail) {
        //todo将验证码存储到redis
        String code = generateCode();
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("【验证码】来自系统的身份校验");
            helper.setText("您的验证码是：" + code + "，有效期为5分钟，请尽快使用。", true);

            mailSender.send(message);
            log.info("验证码发送成功：{}", code);
        } catch (MessagingException e) {
            log.error("发送验证码失败", e);
            throw new RuntimeException("邮件发送失败");
        }
        return code;
    }

    // 生成随机数字验证码
    private String generateCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < MAX_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
