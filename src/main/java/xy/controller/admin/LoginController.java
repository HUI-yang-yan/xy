package xy.controller.admin;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xy.result.Result;
import xy.service.LoginService;
import xy.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@RestController
@Slf4j
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login/init")
    public Result login(@RequestParam String username, @RequestParam String password) {
        log.info("用户登录校验: username={}, password={}", username, password);
//        1.先校验用户名和密码是否正确
        boolean success = loginService.login(username,password);
        if(success) {
            //2.1.如果正确,返回jwt
            String token = JwtUtil.generateToken(username);
            Map<String,String> map = new HashMap<String,String>();
            map.put("token",token);
            return Result.success(map);
        } else {
            //2.2.如果错误,返回错误码
            return Result.error("账号或者密码有误,请重试");
        }
    }


}
