package com.example.xixin.service.impl;

import com.example.xixin.entity.Checkitem;
import com.example.xixin.entity.Cireport;
import com.example.xixin.mapper.CheckitemMapper;
import com.example.xixin.service.ICheckitemService;
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
public class CheckitemServiceImpl extends ServiceImpl<CheckitemMapper, Checkitem> implements ICheckitemService {
    @Resource
    CheckitemMapper checkitemMapper;
    @Override
    public List<Cireport> selectCheckitems(Integer smId) {
        return checkitemMapper.selectCheckitems(smId);
    }
}
