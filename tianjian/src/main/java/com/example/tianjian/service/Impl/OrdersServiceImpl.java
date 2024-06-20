package com.example.tianjian.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.dto.OrdersDto;
import com.example.tianjian.entity.Hospital;
import com.example.tianjian.entity.Orders;
import com.example.tianjian.entity.Setmeal;
import com.example.tianjian.entity.Users;
import com.example.tianjian.mapper.OrdersMapper;
import com.example.tianjian.service.IOrdersService;
import com.example.tianjian.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private UsersServiceImpl usersService;

    @Resource
    private SetmealServiceImpl setmealService;

    @Resource
    private HospitalServiceImpl hospitalService;

    @Override
    public Result getList() {
        List<Orders> list = ordersMapper.selectList(new QueryWrapper<Orders>().orderByDesc("orderDate"));
        Long total = ordersMapper.selectCount(new QueryWrapper<Orders>());
        List<OrdersDto> ordersDtoList = new LinkedList<>();
        for(Orders orders : list){
            OrdersDto ordersDto = new OrdersDto();
            BeanUtil.copyProperties(orders, ordersDto);
            Users users = usersService.getById(orders.getUserId());
            ordersDto.setRealName(users.getRealName());
            ordersDto.setSex(users.getSex());
            Hospital hospital = hospitalService.getById(orders.getHpId());
            ordersDto.setHpName(hospital.getName());
            Setmeal setmeal = setmealService.getById(orders.getSmId());
            ordersDto.setSmName(setmeal.getName());
            ordersDtoList.add(ordersDto);
        }
        return Result.ok(ordersDtoList, total);
    }
}
