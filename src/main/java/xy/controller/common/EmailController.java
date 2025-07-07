package xy.controller.common;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xy.result.Result;
import xy.utils.EmailUtil;

@RequestMapping("/common")
@RestController
@Slf4j
public class EmailController {


    @Resource
    private EmailUtil emailUtil;
    /**
     * 请求发送验证码接口
     * @param email 用户邮箱
     * @return 返回成功消息
     */
    @PostMapping("/email/sendCode")
    public Result<String> sendCode(@RequestParam String email) {
        log.info("用户邮箱为:{}", email);
        String code = emailUtil.sendVerificationCode(email);
        // 通常这里不返回验证码给前端，实际项目应该存缓存或数据库做验证
        return Result.success("验证码已经发送至邮箱");
    }
}
