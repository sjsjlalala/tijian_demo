package com.example.xixin.mapper;

import com.example.xixin.entity.Cidetailedreport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
public interface CidetailedreportMapper extends BaseMapper<Cidetailedreport> {

    void saveAll(List<Cidetailedreport> cidrs);

    List<Cidetailedreport> selectBySmId(Integer smId);
}
