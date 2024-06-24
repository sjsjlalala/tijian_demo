package com.example.xixin.controller;

import com.example.xixin.service.IOrdersService;
import com.example.xixin.dto.Result;
import com.example.xixin.entity.Orders;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/orders")
@CrossOrigin
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;
    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody Orders order)
    {
        return ordersService.createOrder(order);
    }


    @GetMapping("/getCalender")
    public Result getStock(Integer year, Integer month,Integer hpId)
    {

        return ordersService.getCalender(year, month,hpId);
    }
    @DeleteMapping("/cancelOrder")
    public Result cancelOrder(Integer orderId)
    {
        return ordersService.cancelOrder(orderId);
    }

    @GetMapping("/getOrdersByUserId")
    public Result getStock(String userId)
    {
        return ordersService.getOrdersByUserId(userId);
    }

    @GetMapping("/getAllOrdersForDoctor")
    public Result getAllOrdersForDoctor(String userId)
    {
        return ordersService.getAllOrdersForDoctor(userId);
    }
}
