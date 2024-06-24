package com.example.tianjian.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.entity.Cidetailedreport;
import com.example.tianjian.mapper.CidetailedreportMapper;
import com.example.tianjian.service.ICidetailedreportService;
import com.example.tianjian.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Result getList() {
        QueryChainWrapper<Cidetailedreport> queryChainWrapper = this.query().orderByAsc("ciId");
        List<Cidetailedreport> cidetailedreports = BeanUtil.copyToList(queryChainWrapper.list(), Cidetailedreport.class);
        return Result.ok(cidetailedreports, this.count());
    }
}
