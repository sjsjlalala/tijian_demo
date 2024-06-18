package com.example.xixin.controller;

import com.example.xixin.dto.Result;
import com.example.xixin.entity.Users;
import com.example.xixin.service.IUsersService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private IUsersService usersService;
    @PostMapping("/login")
    public Result login(@RequestBody Users User, HttpSession session) {
        return usersService.login(User,session);
    }
    @PostMapping("/register")
    public Result register(@RequestBody Users User) {
        return usersService.register(User);
    }
    @PostMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("user");
        return Result.ok("退出成功");
    }

}
