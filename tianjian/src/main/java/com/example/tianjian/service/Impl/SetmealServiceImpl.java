package com.example.tianjian.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.entity.Setmeal;
import com.example.tianjian.mapper.SetmealMapper;
import com.example.tianjian.service.ISetmealService;
import com.example.tianjian.util.Result;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {

    @Override
    public Result getList() {
        return Result.ok(this.list(), (long) this.count());
    }
}
