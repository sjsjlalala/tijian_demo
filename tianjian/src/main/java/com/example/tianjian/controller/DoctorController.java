package com.example.tianjian.controller;


import com.example.tianjian.entity.Doctor;
import com.example.tianjian.service.IDoctorService;
import com.example.tianjian.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
    private IDoctorService doctorService;

    @RequestMapping("/login")
    public Result login(@RequestBody Doctor doctor)
    {
        return doctorService.login(doctor);
    }

    @RequestMapping("/loginByCode")
    public Result loginByCode(@RequestParam("phone") String phone,@RequestParam("code") String code)
    {
        return doctorService.loginByCode(phone,code);
    }

    /**
     * 发送手机验证码
     */
    @PostMapping("/code")
    public Result sendCode(@RequestParam("phone") String phone) {
        // TODO 发送短信验证码并保存验证码
        return doctorService.sendCode(phone);
    }

    @PostMapping("/changepwd")
    public Result changepwd( String phone, String code, String newpwd)
    {
        return doctorService.changepwd(phone,code,newpwd);
    }

    @GetMapping("/exit")
    public Result exit(HttpSession session)
    {
        session.removeAttribute("user");
        return Result.ok();
    }
}
