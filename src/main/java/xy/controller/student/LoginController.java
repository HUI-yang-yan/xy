package xy.controller.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xy.dto.LoginDTO;
import xy.pojo.Student;
import xy.result.Result;
import xy.service.LoginService;
import xy.utils.JwtUtil;
import xy.vo.LoginVO;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/student/login")
@RestController("studentLogin")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public Result<Map<String,String>> login(@RequestBody LoginDTO loginDTO) {
        log.info("用户登录校验: {}", loginDTO);
//        1.先校验用户名和密码是否正确
        LoginVO loginVO = loginService.login(loginDTO);
        if(loginVO.getIfSuccess()) {
            System.out.println(111);
            //2.1.如果正确,返回jwt
            String token = JwtUtil.generateToken(loginVO.getId());

            Map<String,String> map = new HashMap<>();
            System.out.println(token);
            map.put("token",token);
            return Result.success(map);
        } else {
            //2.2.如果错误,返回错误码
            return Result.error("账号或者密码有误,请重试");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody Student student) {
        log.info("注册学生:{}",student);
        Boolean ifSuccess = loginService.register(student);
        if(ifSuccess) {
            return Result.success();
        }
        return Result.error("该用户已经存在!");
    }
}
