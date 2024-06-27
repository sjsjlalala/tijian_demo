package com.example.tianjian.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.dto.OrdersDto;
import com.example.tianjian.entity.*;
import com.example.tianjian.mapper.OrdersMapper;
import com.example.tianjian.service.ICidetailedreportService;
import com.example.tianjian.service.IOrdersService;
import com.example.tianjian.service.IOverallresultService;
import com.example.tianjian.util.Result;
import com.example.tianjian.vo.CidetailedreportVo;
import com.example.tianjian.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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


    @Override
    public Result searchList(UserVo userVo, Integer pageNum, Integer pageSize) {
        // 创建分页对象
        Page<Orders> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<Users>().eq(StringUtils.isNotBlank(userVo.getUserId()), "userId", userVo.getUserId())
                .eq(StringUtils.isNotBlank(userVo.getRealName()),"realName", userVo.getRealName())
                .eq(userVo.getSex() != null, "sex", userVo.getSex());
        List<String> userIds = usersService.list(usersQueryWrapper).stream()
                .map(Users::getUserId)
                .collect(Collectors.toList());

        QueryWrapper<Orders> ordersQueryWrapper = new QueryWrapper<Orders>()
                .eq(userVo.getSmId() != null,"smId", userVo.getSmId())
                .eq(userVo.getOrderDate() != null,"orderDate", userVo.getOrderDate())
                .eq(userVo.getState() != null,"state", userVo.getState())
                .eq("pay",2)
                .in("userId", userIds)
                .orderByDesc("orderDate");
        // 执行分页查询
        IPage<Orders> ordersIPage = ordersMapper.selectPage(page, ordersQueryWrapper);
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
        Integer maxPageNum = records.size();
        // 返回分页结果，包含total（总记录数）和maxPageNum（当前页数据数量）
        return Result.ok(ordersDtoList, ordersIPage.getTotal(), maxPageNum);
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
    public Result submit(List<CidetailedreportVo> cidetailedreportVos) {
        for (int i=0;i<cidetailedreportVos.size();i++) {
            CidetailedreportVo cidetailedreportVo = cidetailedreportVos.get(i);
            if (cidetailedreportVo.getIsError() == 0) {
                Cidetailedreport cidetailedreport = cidetailedreportService.getById(cidetailedreportVo.getCidrId());
                cidetailedreport.setValue(cidetailedreportVo.getValue());
                cidetailedreportService.updateById(cidetailedreport);
            } else {
                Cidetailedreport cidetailedreport = cidetailedreportService.getById(cidetailedreportVo.getCidrId());
                cidetailedreport.setValue(cidetailedreportVo.getValue());
                cidetailedreport.setIsError(1);
                cidetailedreportService.updateById(cidetailedreport);
            }
        }
        return Result.ok("保存成功");
    }

    @Override
    public Result submitall(Integer orId, String title, String content,Integer orderId) {
        if(orderId!=null){
            Overallresult overallresult = new Overallresult();
            overallresult.setTitle(title);
            overallresult.setContent(content);
            overallresult.setOrderId(orderId);
            overallresultService.save(overallresult);
        }
        else if(orId!=null){
            Overallresult overallresult = overallresultService.getById(orId);
            overallresult.setTitle(title);
            overallresult.setContent(content);
            overallresultService.updateById(overallresult);
        }
        return Result.ok("保存成功");
    }

    @Override
    public Result getAll(Integer orderId) {
        QueryChainWrapper<Overallresult> orderId1 = overallresultService.query().eq("orderId", orderId);
        return Result.ok(orderId1.list(), orderId1.count());
    }

    @Override
    public Result deleteall(Integer orId) {
        overallresultService.removeById(orId);
        return Result.ok("删除成功");
    }


    @Override
    public Result updateall(Integer orderId) {
        if(orderId!=null){
            Orders orders = getById(orderId);
            orders.setState(2);
            updateById(orders);
        }
        return Result.ok("修改成功");
    }

}
