package com.example.xixin.service;

import com.example.xixin.dto.Result;
import com.example.xixin.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
public interface IOrdersService extends IService<Orders> {

    Result createOrder(Orders order);


    Result cancelOrder(Integer orderId);

    Result getCalender(Integer year, Integer month, Integer hpId);

    Result getOrdersByUserId(String userId);

    Result getAllOrdersForDoctor(String userId);
}
