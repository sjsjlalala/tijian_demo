package com.example.tianjian.controller;


import com.example.tianjian.service.IOrdersService;
import com.example.tianjian.util.Result;
import com.example.tianjian.vo.CidetailedreportVo;
import com.example.tianjian.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public Result getList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam("maxPageNum") Integer pageSize) {
        return ordersService.getList(pageNum, pageSize);
    }

    @RequestMapping("/searchList")
    public Result searchList(@RequestBody UserVo userVo, Integer pageNum, Integer maxPageNum) {
        return ordersService.searchList(userVo,pageNum,maxPageNum);
    }

    @GetMapping("/getorder")
    public Result getOrder(Integer orderId) {
        return ordersService.getOrder(orderId);
    }

    @PutMapping("/submit")
    public Result submit(@RequestBody List<CidetailedreportVo> cidetailedreportVos) {
        return ordersService.submit(cidetailedreportVos);
    }

    @PutMapping("/submitall")
    public Result submitall(@RequestParam(required = false) Integer orderId,String title,String content ,@RequestParam(required = false)Integer orId) {
        return ordersService.submitall(orId,title,content,orderId);
    }

    @GetMapping("/getall")
    public Result getAll(Integer orderId) {
        return ordersService.getAll(orderId);
    }

    @DeleteMapping("/deleteall")
    public Result deleteall(Integer orId) {
        return ordersService.deleteall(orId);

    }
    @PutMapping("/updateall")
    public Result updateall(Integer orderId) {
        return ordersService.updateall(orderId);

    }
    @GetMapping("/getCount")
    public Result getCount() {
        return ordersService.getCount();
    }
}
