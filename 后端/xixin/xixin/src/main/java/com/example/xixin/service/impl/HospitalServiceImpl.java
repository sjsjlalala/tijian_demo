package com.example.xixin.service.impl;

import com.example.xixin.dto.Result;
import com.example.xixin.entity.Hospital;
import com.example.xixin.mapper.HospitalMapper;
import com.example.xixin.service.IHospitalService;
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
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements IHospitalService {

    @Resource
    private HospitalMapper hospitalMapper;
    @Override
    public Result getHospital() {
        List<Hospital> hospitals = hospitalMapper.getHospital();
        return Result.ok(hospitals, (long) hospitals.size());
    }

    @Override
    public String getRule(Integer hpId) {
        return hospitalMapper.getRule(hpId);
    }
}
