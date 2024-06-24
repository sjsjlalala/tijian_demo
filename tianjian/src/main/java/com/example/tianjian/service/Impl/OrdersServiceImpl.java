package com.example.tianjian.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.dto.OrdersDto;
import com.example.tianjian.entity.*;
import com.example.tianjian.mapper.OrdersMapper;
import com.example.tianjian.service.ICidetailedreportService;
import com.example.tianjian.service.IOrdersService;
import com.example.tianjian.service.IOverallresultService;
import com.example.tianjian.util.Result;
import com.example.tianjian.vo.UserVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.stream.Collectors;

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

    @Resource
    private ICidetailedreportService cidetailedreportService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private IOverallresultService overallresultService;

    //List<OrdersDto> ordersDtoList = new LinkedList<>();

//    Integer nowOrderId = 0;

    private List<OrdersDto> getAllOrdersAsDtoList() {
        List<Orders> allOrders = ordersMapper.selectList(new QueryWrapper<Orders>().orderByDesc("orderDate"));
        return allOrders.stream().map(order -> {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtil.copyProperties(order, ordersDto);

            Users users = usersService.getById(order.getUserId());
            if (users != null) {
                ordersDto.setRealName(users.getRealName());
                ordersDto.setSex(users.getSex());
            }

            Hospital hospital = hospitalService.getById(order.getHpId());
            if (hospital != null) {
                ordersDto.setHpName(hospital.getName());
            }

            Setmeal setmeal = setmealService.getById(order.getSmId());
            if (setmeal != null) {
                ordersDto.setSmName(setmeal.getName());
            }

            return ordersDto;
        }).collect(Collectors.toList());
    }


    @Override
    public Result getList(Integer pageNum, Integer pageSize) {
        // 创建分页对象
        Page<Orders> page = new Page<>(pageNum, pageSize);

        // 执行分页查询
        IPage<Orders> ordersIPage = ordersMapper.selectPage(page, new QueryWrapper<Orders>().orderByDesc("orderDate"));

        // 获取分页数据
        List<Orders> records = ordersIPage.getRecords();

        // 将Orders转换为OrdersDto并设置额外信息
        List<OrdersDto> ordersDtoList = records.stream().map(order -> {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtil.copyProperties(order, ordersDto);

            Users users = usersService.getById(order.getUserId());
            if (users != null) {
                ordersDto.setRealName(users.getRealName());
                ordersDto.setSex(users.getSex());
            }

            Hospital hospital = hospitalService.getById(order.getHpId());
            if (hospital != null) {
                ordersDto.setHpName(hospital.getName());
            }

            Setmeal setmeal = setmealService.getById(order.getSmId());
            if (setmeal != null) {
                ordersDto.setSmName(setmeal.getName());
            }

            return ordersDto;
        }).collect(Collectors.toList());

        // 添加maxPageNum字段，这里实际上是当前页的记录数（pageSize），按需求命名为了maxPageNum
        Integer maxPageNum = records.size();

        // 返回分页结果，包含total（总记录数）和maxPageNum（当前页数据数量）
        return Result.ok(ordersDtoList, ordersIPage.getTotal(), maxPageNum);
    }


//    @Override
//    public Result getList() {
//        List<Orders> list = ordersMapper.selectList(new QueryWrapper<Orders>().orderByDesc("orderDate"));
//        Long total = ordersMapper.selectCount(new QueryWrapper<Orders>());
//        for(Orders orders : list){
//            OrdersDto ordersDto = new OrdersDto();
//            BeanUtil.copyProperties(orders, ordersDto);
//            Users users = usersService.getById(orders.getUserId());
//            ordersDto.setRealName(users.getRealName());
//            ordersDto.setSex(users.getSex());
//            Hospital hospital = hospitalService.getById(orders.getHpId());
//            ordersDto.setHpName(hospital.getName());
//            Setmeal setmeal = setmealService.getById(orders.getSmId());
//            ordersDto.setSmName(setmeal.getName());
//            ordersDtoList.add(ordersDto);
//        }
//        return Result.ok(ordersDtoList, total);
//    }



    @Override
    public Result searchList(UserVo userVo) {
        OrdersDto ordersDto = BeanUtil.copyProperties(userVo, OrdersDto.class);
        List<OrdersDto> ordersDtoList1 = new LinkedList<>();
        Long total = 0L;
        List<OrdersDto> ordersDtoList = getAllOrdersAsDtoList();
        for (OrdersDto ordersDto1 : ordersDtoList){
            if ((ordersDto.getOrderDate() == null || (ordersDto1.getOrderDate().equals(ordersDto.getOrderDate())))
            && (ordersDto.getUserId() == null || ordersDto1.getUserId().equals(ordersDto.getUserId()))
            && (ordersDto.getRealName() == null || ordersDto1.getRealName().equals(ordersDto.getRealName()))
            && (ordersDto.getSmName() == null || ordersDto1.getSmName().equals(ordersDto.getSmName()))
            && (ordersDto.getSex() == null || ordersDto1.getSex().equals(ordersDto.getSex()))
            && (ordersDto.getState() == null || ordersDto1.getState().equals(ordersDto.getState()))) {
                // 你的逻辑代码
                ordersDtoList1.add(ordersDto1);
                total++;
            }
        }
        return Result.ok(ordersDtoList1, total);
    }

    @Override
    public Result getOrder(Integer orderId) {
        stringRedisTemplate.opsForValue().set("now:orderId", orderId.toString());
        OrdersDto ordersDto = new OrdersDto();
        List<OrdersDto> ordersDtoList = getAllOrdersAsDtoList();
        for(OrdersDto ordersDto1 : ordersDtoList){
            if(ordersDto1.getOrderId().equals(orderId)){
                ordersDto=ordersDto1;
            }
        }
        return Result.ok(ordersDto);
    }

    @Override
    public Result submit(int cidrId, int isError, String value) {
        if(isError==0){
            Cidetailedreport cidetailedreport = cidetailedreportService.getById(cidrId);
            cidetailedreport.setValue(value);
            cidetailedreportService.updateById(cidetailedreport);
        }else{
            Cidetailedreport cidetailedreport = cidetailedreportService.getById(cidrId);
            cidetailedreport.setValue(value);
            cidetailedreport.setIsError(1);
            cidetailedreportService.updateById(cidetailedreport);
        }
        return Result.ok("保存成功");
    }

    @Override
    public Result submitall(int orId, String title, String content) {
        Overallresult overallresult = new Overallresult();
        String s = stringRedisTemplate.opsForValue().get("now:orderId");
        if(s!=null){
            overallresult.setOrderId(Integer.parseInt(s));
            overallresult.setTitle(title);
            overallresult.setContent(content);
            overallresult.setOrId(orId);
            overallresultService.save(overallresult);
        }
        return Result.ok("保存成功");
    }
}
