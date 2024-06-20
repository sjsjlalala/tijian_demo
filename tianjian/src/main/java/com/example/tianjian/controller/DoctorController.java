package com.example.tianjian.controller;


import com.example.tianjian.entity.Doctor;
import com.example.tianjian.service.IDoctorService;
import com.example.tianjian.util.Result;
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
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
    private IDoctorService doctorService;

    @RequestMapping("/login")
    public Result login(@RequestBody Doctor doctor)
    {
        return doctorService.login(doctor);
    }
    
}
