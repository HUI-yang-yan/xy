package xy.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        // 从 session 里取登录标识，比如 user 对象或登录状态
        Object user = request.getSession().getAttribute("user");
        if (user != null) {
            // 已登录，放行请求
            return true;
        }
        // 未登录，重定向到登录页
        response.sendRedirect("/login");
        return false; // 不放行请求
    }
}
