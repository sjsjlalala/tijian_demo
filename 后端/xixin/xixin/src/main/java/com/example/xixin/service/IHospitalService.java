package com.example.xixin.service;

import com.example.xixin.dto.Result;
import com.example.xixin.entity.Hospital;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
public interface IHospitalService extends IService<Hospital> {

    Result getHospital();

    String getRule(Integer hpId);
}
