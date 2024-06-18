package com.example.xixin.service.impl;

import com.example.xixin.entity.Cidetailedreport;
import com.example.xixin.mapper.CidetailedreportMapper;
import com.example.xixin.service.ICidetailedreportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
@Service
public class CidetailedreportServiceImpl extends ServiceImpl<CidetailedreportMapper, Cidetailedreport> implements ICidetailedreportService {
    @Resource
    CidetailedreportMapper cidetailedreportMapper;
    @Override
    public void saveAll(List<Cidetailedreport> cidrs) {
        cidetailedreportMapper.saveAll(cidrs);
    }

    @Override
    public List<Cidetailedreport> selectBySmId(Integer smId) {
        return cidetailedreportMapper.selectBySmId(smId);
    }
}
