package com.example.tianjian.controller;


import com.example.tianjian.service.IOrdersService;
import com.example.tianjian.util.Result;
import com.example.tianjian.vo.UserVo;
import org.springframework.web.bind.annotation.*;

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
    public Result getList(Integer pageNum, Integer pageSize) {
        return ordersService.getList(pageNum, pageSize);
    }

    @GetMapping("/searchList")
    public Result searchList(@RequestBody UserVo userVo) {
        return ordersService.searchList(userVo);
    }

    @GetMapping("/getorder")
    public Result getOrder(Integer orderId,Integer smId) {
        return ordersService.getOrder(orderId,smId);
    }

    @PutMapping("/submit")
    public Result submit(int cidrId,int isError,String value) {
        return ordersService.submit(cidrId,isError,value);
    }

    @PutMapping("/submitall")
    public Result submitall(int orId,String title,String content) {
        return ordersService.submitall(orId,title,content);
    }
}
