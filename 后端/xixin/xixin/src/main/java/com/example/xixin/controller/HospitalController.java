package com.example.xixin.controller;

import com.example.xixin.dto.Result;
import com.example.xixin.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private IHospitalService hospitalService;
    @GetMapping("/getHospital")
    public Result getHospital()
    {
        return hospitalService.getHospital();
    }

}
