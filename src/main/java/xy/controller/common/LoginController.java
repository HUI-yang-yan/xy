//package xy.controller.common;
//
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import xy.dto.LoginDTO;
//import xy.result.Result;
//import xy.service.LoginService;
//import xy.utils.JwtUtil;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RequestMapping("/common/login")
//@RestController("commonLogin")
//@Slf4j
//public class LoginController {
//
//    @Resource
//    private LoginService loginService;
//
//    @PostMapping
//    public Result<Map<String,String>> login(@RequestParam LoginDTO loginDTO) {
//        //todo 这里可以改成用LoginVO接受前端发送的数据
//        log.info("用户登录校验: {}", loginDTO);
////        1.先校验用户名和密码是否正确
//        Boolean ifSuccess = loginService.login(loginDTO);
//        if(ifSuccess) {
//            //2.1.如果正确,返回jwt
//            String token = JwtUtil.generateToken(loginDTO.getUserId());
//            Map<String,String> map = new HashMap<>();
//            map.put("token",token);
//            return Result.success(map);
//        } else {
//            //2.2.如果错误,返回错误码
//            return Result.error("账号或者密码有误,请重试");
//        }
//    }
//
//}
