package com.example.xixin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.xixin.dto.CalendarDto;
import com.example.xixin.dto.OrderDetailDto;
import com.example.xixin.dto.OrderDto;
import com.example.xixin.dto.Result;
import com.example.xixin.entity.*;
import com.example.xixin.mapper.OrdersMapper;
import com.example.xixin.service.*;
import com.example.xixin.util.IdCreater;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Autowired
    private IdCreater idCreater;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private IHospitalService hospitalService;
    @Resource
    private ICireportService cireportService;
    @Resource
    private ICheckitemService checkitemService;


    @Resource
    private ICidetailedreportService cidetailedreportService;
    @Transactional
    @Override
    public Result createOrder(Orders order) {
        //判断库存是否充足
        //1.判断是否是周日
        DayOfWeek dayOfWeek = order.getOrderDate().getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY)
            return Result.fail("该医院不提供周日挂号", 105);
        //2.获取医院当天规定好的体检套餐人数
        String rule = hospitalService.getRule(order.getHpId());
        String[] list = rule.split(",");
        String j = list[dayOfWeek.getValue()];
        int ruleStock = Integer.parseInt(j);

        //2.判断是否还有空位
        Integer orderCount = ordersMapper.countByDate(order.getHpId(), order.getOrderDate());
        if (orderCount >= ruleStock)
            return Result.fail("该医院当天已满", 105);


        //插入订单(分布式锁)
        //1.获取锁
        String lockName = "order:" + ":" + order.getHpId() + ":" + order.getSmId() + ":" + order.getOrderDate();
        RLock lock = redissonClient.getLock(lockName);
        try {
            //2.加锁
            lock.lock();
            //3.执行业务逻辑
            //查询当天是否已经有该订单
            Integer count = ordersMapper.isExist(order);
            if (count != 0)
                return Result.fail("当天不能重复下单同一套餐", Result.ORDER_IS_EXIST);

            //2.判断是否还有空位（双重检查锁定）
            orderCount = ordersMapper.countByDate(order.getHpId(), order.getOrderDate());
            if (orderCount >= ruleStock)
                return Result.fail("该医院当天已满", Result.ORDER_FAIL);
            //生成订单id
            long orderId = idCreater.getNextId();
            //往prders表中插入订单
            order.setOrderId((int)orderId);
            order.setState(1);
            ordersMapper.insert(order);
//            //3往cireport表中插入
//            //3.查询套餐明细
//            //3.1.1获取ciId集合
//
//            List<Integer> ciIds = setmealdetailedService.query().eq("smId", order.getSmId())
//                    .list()
//                    .stream()
//                    .map(Setmealdetailed::getCiId)
//                    .collect(Collectors.toList());
//
//            List<Cireport> cireports = new ArrayList<>(ciIds.size());

            //3.1.2查询ciIds对应的ciName
            //3.往cireport中插入数据
            List<Cireport> cireports1 = checkitemService.selectCheckitems(order.getSmId());
            cireports1.stream().forEach(cireport -> {
                cireport.setOrderId((int) orderId);
            });
            cireportService.saveBatch(cireports1);

//            List<Checkitem> checkitems = checkitemService.query().in("ciId", ciIds).list();
//            checkitems.stream().forEach(checkitem -> {
//                Cireport cireport = new Cireport();
//                cireport.setCiId(checkitem.getCiId());
//                cireport.setOrderId((int) orderId);
//                cireport.setCiName(checkitem.getCiName());
//                cireports.add(cireport);
//            });

            //3.1.3 插入cireport表


            //4往checkitemdetailed表中插入
            //4.1根据ciIds查询相应的checkitemdetailed表
            //List<Checkitemdetailed> checkitemdetaileds = checkitemdetailedService.query().in("ciId", ciIds).list();
            //4.往cidetailedreport表中插入
            List<Cidetailedreport> cidetailedreports = cidetailedreportService.selectBySmId(order.getSmId());
            cidetailedreports.forEach(cidetailedreport -> {
                cidetailedreport.setOrderId((int) orderId);
                cidetailedreport.setIsError(0);
            });
            cidetailedreportService.saveAll(cidetailedreports);

            //4.3插入cidetailedreport表
//            List<Cidetailedreport> cidrs = new ArrayList<>();
//
//                    checkitemdetaileds.stream().forEach(
//                    checkitemdetailed -> {
//                        Cidetailedreport cidetailedreport = new Cidetailedreport();
//                        BeanUtil.copyProperties(checkitemdetailed,cidetailedreport);
//                        cidetailedreport.setOrderId((int) orderId);
//                        cidetailedreport.setIsError(0);
//                        cidrs.add(cidetailedreport);
//                    }
//            );

        } finally {
            lock.unlock();
        }


        //4.返回结果
        return Result.ok("下单成功");
    }




    @Transactional
    @Override
    public Result cancelOrder(Integer orderId) {
        Integer i = ordersMapper.cancelOrder(orderId);
        boolean remove = cireportService.remove(new QueryWrapper<Cireport>().eq("orderId", orderId));
        boolean remove1 = cidetailedreportService.remove(new QueryWrapper<Cidetailedreport>().eq("orderId", orderId));
        if (i == 0 || !remove || !remove1)

            return Result.fail("取消失败", Result.ORDER_CANCEL_FAIL);

        return Result.ok("取消成功");
    }


    @Override
    public Result getCalender(Integer year, Integer month, Integer hpId) {

        //1.创建万历表
        List<CalendarDto> calenders = new ArrayList<>();

        //1.1 计算当前月距1990-1-1多少天,计算该月第一天周几
        //1.1.1 计算年
        int yearDays = 0;
        for (int i = 1990; i < year; i++) {
            if (isLeapYear(i)) {
                yearDays += 366;
            } else {
                yearDays += 365;
            }
        }


        //1.1.2 计算月
        for (int i = 1; i < month; i++) {
            yearDays += monthOfDays(i, year);
        }

        //1.1.3计算该月第一天是周几
        yearDays++;
        int emptyNum = yearDays % 7 ;

        //1.2 创建日历表
        int days = monthOfDays(month, year);

        for (int i = 0; i < emptyNum; i++) {
            calenders.add(new CalendarDto());
        }

        for (int i = 1; i <= days; i++) {
            CalendarDto calendarDto = new CalendarDto();
            calendarDto.setDate(LocalDate.of(year, month, i));
            calenders.add(calendarDto);
        }


        //2.查询该日开始30天内预约情况

        //2.1获取该医院预约规则
        String rule = hospitalService.getRule(hpId);
        String[] split = rule.split(",");
        int[] rules = new int[split.length];
        for (int i = 0; i < split.length; i++)
            rules[i] = Integer.parseInt(split[i]);

        //2.2获取当天是周几
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();

        //2.3从当天开始30天内预约情况
        List<CalendarDto> stocks = new ArrayList<>(30);

        //2.3.1 创建一个中间对象，xml连续查询
        List<OrderDto> temp = new ArrayList<>(30);
        for(int i = 0; i < 30; i++){
            OrderDto orderDto = new OrderDto();
            orderDto.setHpId(hpId);
            orderDto.setDate(LocalDate.now().plusDays(i));
            temp.add(orderDto);
        }

        temp.stream().forEach(System.out::println);
        //2,3,2xml查询的结果返回
        stocks = ordersMapper.selectUsedCount(temp);


        //计算预约情况
        for (int i = 0; i < 30; i++) {
            CalendarDto calendarDto = stocks.get(i);
            //2.5获取该天预约规则
            calendarDto.setTotal(rules[(dayOfWeek+i)%7]);
            //2.6计算剩余数量
            calendarDto.setRemainder(rules[(dayOfWeek+i)%7] - stocks.get(i).getUsed());

        }


        stocks.stream().forEach(System.out::println);
        //3.合并万历表和30天内预约情况表

        for (CalendarDto calendarDto : calenders) {
            for (CalendarDto stock : stocks) {
                if (calendarDto.getDate() == null)
                    continue;
                if (calendarDto.getDate().isEqual(stock.getDate())) {
                    calendarDto.setTotal(stock.getTotal());
                    calendarDto.setUsed(stock.getUsed());
                    calendarDto.setRemainder(stock.getRemainder());
                }
            }
        }
        calenders.stream().forEach(System.out::println);


        calenders.stream().forEach(System.out::println);

        return Result.ok(calenders);

    }

    @Override
    public Result getOrdersByUserId(String userId) {
        List<Orders> orders = ordersMapper.selectList(new QueryWrapper<Orders>().eq("userId", userId));
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>(orders.size());
        for (Orders order : orders) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            BeanUtil.copyProperties(order, orderDetailDto);
            orderDetailDtos.add(orderDetailDto);
        }


        return Result.ok(orderDetailDtos, (long) orders.size());
    }

    private int monthOfDays(int i, int year) {
        switch (i) {
            case 1:
                ;
            case 3:
                ;
            case 5:
                ;
            case 7:
                ;
            case 8:
                ;
            case 10:
                ;
            case 12:
                ;
                return 31;
            case 4:
                ;
            case 6:
                ;
            case 9:
                ;
            case 11:
                ;
                return 30;
            case 2:
                if (isLeapYear(year))
                    return 29;
                else
                    return 28;
        }
        return i;
    }

    private boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
            return true;
        return false;
    }

}
