package com.example.tianjian.controller;


import com.example.tianjian.service.IOrdersService;
import com.example.tianjian.util.Result;
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
@RequestMapping("/orders")
public class OrdersController {
    @Resource
    private IOrdersService ordersService;

    @RequestMapping("/getlist")
    public Result getList() {
        return ordersService.getList();
    }
}
