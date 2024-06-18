package com.example.xixin.interceptor;

import com.example.xixin.dto.UsersDto;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @projectName: xixin
 * @package: com.example.xixin.interceptor
 * @className: LoginInterceptor
 * @author: moki
 * @description: TODO
 * @date: 2024/6/13 12:25
 * @version: 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UsersDto user = (UsersDto) request.getSession().getAttribute("user");
        if(user == null) {
            response.setStatus(302);
            // 设置Location头，指定重定向的URL路径

            return false;
        }
        return true;

    }
}
