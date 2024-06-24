package com.example.tianjian.controller;


import com.example.tianjian.entity.Users;
import com.example.tianjian.service.IUsersService;
import com.example.tianjian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private IUsersService usersService;

    @RequestMapping("/login")
    public Result login(@RequestBody Users user) {
        return usersService.login(user);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Users user) {
        return usersService.register(user);
    }
}
