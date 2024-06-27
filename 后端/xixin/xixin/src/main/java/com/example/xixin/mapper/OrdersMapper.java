package com.example.xixin.mapper;

import com.example.xixin.dto.CalendarDto;
import com.example.xixin.dto.OrderDto;
import com.example.xixin.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
public interface OrdersMapper extends BaseMapper<Orders> {



    @Select("select count(*) from orders where userId = #{userId} and hpId = #{hpId} and smId = #{smId} and orderDate = #{orderDate}")
    Integer isExist(Orders order);

    @Select("select count(*) from orders where hpId = #{hpId}  and orderDate = #{orderDate} and state = 1 and pay = 2")
    Integer countByDate(Integer hpId,  LocalDate orderDate);

    @Delete("delete from orders where orderId = #{orderId}")
    Integer cancelOrder(Integer orderId);


    List<CalendarDto> selectUsedCount(List<OrderDto> orderDtoList);

    boolean insertOrdesAndCireportAndCidetailedreport(Orders orders);
}
