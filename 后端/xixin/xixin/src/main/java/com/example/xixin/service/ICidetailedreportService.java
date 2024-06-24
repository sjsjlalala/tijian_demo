package com.example.xixin.service;

import com.example.xixin.entity.Cidetailedreport;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
public interface ICidetailedreportService extends IService<Cidetailedreport> {

    void saveAll(List<Cidetailedreport> cidrs);

    List<Cidetailedreport> selectBySmId(Integer smId);
}
