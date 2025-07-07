package xy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xy.interceptor.LoginInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")            // 拦截所有请求
                .excludePathPatterns(
                        "/student/login/**",                   // 登录页不拦截
                        "/register",
                        "/email/sendCode",
                        "/css/**", "/js/**", "/images/**", "/webjars/**"); // 静态资源不拦截

    }

}
