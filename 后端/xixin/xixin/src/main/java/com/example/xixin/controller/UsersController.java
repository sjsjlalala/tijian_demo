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

    @RequestMapping("/loginByCode")
    public Result loginByCode(@RequestParam("phone") String phone,@RequestParam("code") String code)
    {
        return usersService.loginByCode(phone,code);
    }

    /**
     * 发送手机验证码
     */
    @PostMapping("/code")
    public Result sendCode(@RequestParam("phone") String phone) {
        return usersService.sendCode(phone);
    }

    @PostMapping("/changepwd")
    public Result changepwd( String phone, String code, String newpwd)
    {
        return usersService.changepwd(phone,code,newpwd);
    }

}
