package com.example.xixin.mapper;

import com.example.xixin.entity.Hospital;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
public interface HospitalMapper extends BaseMapper<Hospital> {

    @Select("select * from hospital")
    List<Hospital> getHospital();

    @Select("select rule from hospital where hpId=#{hpId}")
    String getRule(Integer hpId);
}
