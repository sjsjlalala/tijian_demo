package com.example.xixin.interceptor;


import com.example.xixin.util.JwtTool;
import com.example.xixin.util.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;


@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtTool jwtTool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        // 1.获取请求头中的 token
        String token = request.getHeader("authorization");
        Long userId = null;
        try {
            // 2.校验token
            userId = jwtTool.parseToken(token);
        } catch (Exception e) {
           response.setStatus(302);
            return false;
        }
        // 3.存入上下文
        UserContext.setUser(userId);
        // 4.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户
        UserContext.removeUser();
    }
}
