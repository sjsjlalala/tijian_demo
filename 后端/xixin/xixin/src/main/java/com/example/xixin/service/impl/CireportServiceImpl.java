package com.example.xixin.service.impl;

import com.example.xixin.entity.Cireport;
import com.example.xixin.mapper.CireportMapper;
import com.example.xixin.service.ICireportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
@Service
public class CireportServiceImpl extends ServiceImpl<CireportMapper, Cireport> implements ICireportService {
    @Resource
    CireportMapper cireportMapper;

}
