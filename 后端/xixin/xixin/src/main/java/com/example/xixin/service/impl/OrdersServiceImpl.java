package com.example.xixin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpResponse;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.xixin.dto.*;
import com.example.xixin.entity.*;
import com.example.xixin.mapper.OrdersMapper;
import com.example.xixin.service.*;
import com.example.xixin.util.AlipayTradePagePay;
import com.example.xixin.util.IdCreater;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpRequest;
import java.sql.SQLOutput;
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
    private IOverallresultService overallresultService;
    @Resource
    private ISetmealService setmealService;


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
        String alipay = null;
        int ruleStock = Integer.parseInt(j);
        int id = 0;
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
            List<Orders> orders = ordersMapper.selectList(new QueryWrapper<Orders>().eq("hpId", order.getHpId()).eq("smId", order.getSmId()).eq("orderDate", order.getOrderDate()).eq("pay", 2).eq("userId", order.getUserId()).eq("state",1));
            int count = orders.size();
            if (count != 0)
                return Result.fail("当天不能重复下单同一套餐", Result.ORDER_IS_EXIST);

            //2.判断是否还有空位（双重检查锁定）
            orderCount = ordersMapper.countByDate(order.getHpId(), order.getOrderDate());
            if (orderCount >= ruleStock)
                return Result.fail("该医院当天已满", Result.ORDER_FAIL);
            //生成订单id
            int orderId = (int) idCreater.getNextId();
            id = orderId;
            //往prders表中插入订单
            order.setOrderId( orderId);
            order.setState(1);
            ordersMapper.insert(order);

            //3.1.2查询ciIds对应的ciName
            //3.往cireport中插入数据
            List<Cireport> cireports1 = checkitemService.selectCheckitems(order.getSmId());
            cireports1.stream().forEach(cireport -> {
                cireport.setOrderId((int) orderId);
            });
            cireportService.saveBatch(cireports1);


            //4.往cidetailedreport表中插入
            List<Cidetailedreport> cidetailedreports = cidetailedreportService.selectBySmId(order.getSmId());
            cidetailedreports.forEach(cidetailedreport -> {
                cidetailedreport.setOrderId((int) orderId);
                cidetailedreport.setIsError(0);
            });
            //5.支付
            //5.1获取金额和套餐名字
            Setmeal setmeal = setmealService.getById(order.getSmId());
            String name = setmeal.getName();
            int price = setmeal.getPrice();

             alipay = AlipayTradePagePay.alipay(String.valueOf(orderId), name, String.valueOf(price));
            cidetailedreportService.saveAll(cidetailedreports);

        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }


        //4.返回结果
        System.out.println(id);
        return Result.ok(alipay, (long) id);
    }




    @Transactional
    @Override
    public Result cancelOrder(Integer orderId) {
       /* Integer i = ordersMapper.cancelOrder(orderId);
        boolean remove = cireportService.remove(new QueryWrapper<Cireport>().eq("orderId", orderId));
        boolean remove1 = cidetailedreportService.remove(new QueryWrapper<Cidetailedreport>().eq("orderId", orderId));
        if (i == 0 || !remove || !remove1)

            return Result.fail("取消失败", Result.ORDER_CANCEL_FAIL);*/

        Orders orders = ordersMapper.selectById(orderId);
        orders.setState(3);
        int i = ordersMapper.updateById(orders);
        if(i == 0)
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
        List<Orders> orders = ordersMapper.selectList(new QueryWrapper<Orders>().eq("userId", userId).eq("pay",2));
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>(orders.size());
        for (Orders order : orders) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            BeanUtil.copyProperties(order, orderDetailDto);
            orderDetailDtos.add(orderDetailDto);
        }


        return Result.ok(orderDetailDtos, (long) orders.size());
    }

    @Override
    public Result getAllOrdersForDoctor(String userId) {

        List<OrdersDetailForDoctor> ods = ordersMapper.selectList(new QueryWrapper<Orders>().eq("userId", userId).eq("state", 2))
                .stream()
                .map(orders -> {
                    OrdersDetailForDoctor ordersDetailForDoctor = new OrdersDetailForDoctor();
                    BeanUtil.copyProperties(orders, ordersDetailForDoctor);
                    return ordersDetailForDoctor;
                }).toList();
        //判断用户有没有预约订单
        if (ods.isEmpty() )
            return Result.ok();

        //2.查询预约单详情
        ods.stream().forEach(od -> {
            List<Map<String, List<?>>> map = new ArrayList<>();
            cireportService.list(new QueryWrapper<Cireport>().eq("orderId",od.getOrderId()))
                    .forEach(cireport -> {
                        List<Cidetailedreport> cddrs = cidetailedreportService.query().in("ciId", cireport.getCiId()).eq("orderId",od.getOrderId()).list();
                        Map<String, List<?>> map1 = new HashMap<>();
                        map1.put("name", Collections.singletonList(cireport.getCiName()));
                        map1.put("cddrs", cddrs);
                        map.add(map1);
                    });
                    od.setMap(map);
            //3.总检报告
            List<Overallresult> result = overallresultService.list(new QueryWrapper<Overallresult>().eq("orderId", od.getOrderId()));
            od.setOverallresults(result);
        });

        //4.按时间排序

        return Result.ok(ods, (long) ods.size());
    }

    @Override
    public Result confirmOrder(Integer out_trade_no, HttpServletResponse response) {
        Orders order = getById(out_trade_no);
        if(order != null ){
            order.setPay(2);
        }
        if (updateById(order)) {

            return Result.ok();
        }
        return Result.fail("确认订单失败",500);
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
