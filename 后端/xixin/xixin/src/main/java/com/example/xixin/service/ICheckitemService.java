package com.example.xixin.service;

import com.example.xixin.entity.Checkitem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xixin.entity.Cireport;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
public interface ICheckitemService extends IService<Checkitem> {

    List<Cireport> selectCheckitems(Integer smId);
}
