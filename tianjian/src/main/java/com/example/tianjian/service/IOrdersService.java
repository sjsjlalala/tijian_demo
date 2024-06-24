package com.example.tianjian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tianjian.entity.Orders;
import com.example.tianjian.util.Result;
import com.example.tianjian.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
public interface IOrdersService extends IService<Orders> {

    Result getList(Integer pageNum, Integer pageSize);

    Result searchList(UserVo userVo);

    Result getOrder(Integer orderId, Integer smId);

    Result submit(int cidrId, int isError, String value);

    Result submitall(int orId, String title, String content);
}
