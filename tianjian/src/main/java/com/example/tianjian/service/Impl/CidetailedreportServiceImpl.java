package com.example.tianjian.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.dto.CidetailedreportDto;
import com.example.tianjian.entity.Checkitem;
import com.example.tianjian.entity.Cidetailedreport;
import com.example.tianjian.entity.Cireport;
import com.example.tianjian.mapper.CidetailedreportMapper;
import com.example.tianjian.service.ICheckitemService;
import com.example.tianjian.service.ICidetailedreportService;
import com.example.tianjian.service.ICireportService;
import com.example.tianjian.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
public class CidetailedreportServiceImpl extends ServiceImpl<CidetailedreportMapper, Cidetailedreport> implements ICidetailedreportService {
    @Resource
    private ICheckitemService checkitemService;

    @Resource
    private ICireportService cireportService;

    @Override
    public Result getList(Integer orderId) {
<<<<<<< HEAD
        QueryChainWrapper<Cidetailedreport> queryChainWrapper = this.query().eq("orderId", orderId).orderByAsc("ciId");
        List<Cidetailedreport> cidetailedreports = BeanUtil.copyToList(queryChainWrapper.list(), Cidetailedreport.class);
        return Result.ok(cidetailedreports, this.count());
=======
        QueryWrapper<Cireport> query = new QueryWrapper<Cireport>().eq("orderId", orderId);
        List<Integer> ciIds = cireportService.list(query).stream().map(Cireport::getCiId).collect(Collectors.toList());
        System.out.println(ciIds);
        List<CidetailedreportDto> cidetailedreportDtos = new ArrayList<>();
        for(int i = 0; i < ciIds.size(); i++){
            Integer ciId = ciIds.get(i);
            CidetailedreportDto cidetailedreportDto = new CidetailedreportDto();
            cidetailedreportDto.setCiName(checkitemService.getById(ciIds.get(i)).getCiName());
            QueryWrapper<Cidetailedreport> query1 = new QueryWrapper<Cidetailedreport>().eq("ciId", ciIds.get(i));
            cidetailedreportDto.setCidetailedreports(this.list(query1));
            cidetailedreportDtos.add(cidetailedreportDto);
            System.out.println(cidetailedreportDtos);
            System.out.println("/n");
        }
        return Result.ok(cidetailedreportDtos);
>>>>>>> b55bfb3b6a8f2500164ec1d0f536382b4d7fee69
    }
}
