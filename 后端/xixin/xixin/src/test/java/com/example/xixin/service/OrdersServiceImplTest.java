package com.example.xixin.service;

import com.example.xixin.dto.CalendarDto;
import com.example.xixin.dto.OrderDto;
import com.example.xixin.mapper.OrdersMapper;
import com.example.xixin.service.impl.HospitalServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class OrdersServiceImplTest {

    @Resource
    OrdersMapper ordersMapper;
    @Resource
    HospitalServiceImpl hospitalService;
    @Test
    public void getCalender() {
        Integer year=2024;
        Integer month=6;
        Integer hpId = 1;


        //1.创建万历表
        List<CalendarDto> calenders = new ArrayList<>();

        //1.1 计算当前月距1990-1-1多少天,计算该月第一天周几
        //1.1.1 计算年
        int yearDays = 0;
        for (int i = 1990; i < year; i++) {
            if (isLeapYear(year)) {
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
        int emptyNum = yearDays % 7 + 1;

        //1.2 创建日历表
        int days = monthOfDays(month, year);

        for (int i = 0; i < emptyNum; i++) {
            calenders.add(null);
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

        List<OrderDto> temp = new ArrayList<>(30);

        for(int i = 0; i < 30; i++){
            OrderDto orderDto = new OrderDto();
            orderDto.setHpId(hpId);
            orderDto.setDate(LocalDate.now().plusDays(i));
            temp.add(orderDto);
        }

        temp.stream().forEach(System.out::println);
        stocks = ordersMapper.selectUsedCount(temp);



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
                if (calendarDto == null)
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



    }
    private int monthOfDays(int i,int year) {
        switch (i){
            case 1 : ;
            case 3 : ;
            case 5 : ;
            case 7 : ;
            case 8 : ;
            case 10 : ;
            case 12 : ;
                return 31;
            case 4 : ;
            case 6 : ;
            case 9 : ;
            case 11 : ;
                return 30;
            case 2 : if(isLeapYear(year))
                return 29;
            else
                return 28;
        }
        return i;
    }

    private boolean isLeapYear(int year){
        if((year%4==0&&year%100!=0)||year%400==0)
            return true;
        return false;
    }
}