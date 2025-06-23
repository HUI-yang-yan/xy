package xy.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xy.result.Result;

@RequestMapping("/admin")
@RestController
@Slf4j
public class LoginController {

    @PostMapping("/login/init")
    public Result login(@RequestParam String username, @RequestParam String password) {
        log.info("用户登录校验");
        return Result.success();
    }
}
