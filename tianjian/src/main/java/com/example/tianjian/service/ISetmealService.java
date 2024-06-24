package com.example.tianjian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tianjian.entity.Setmeal;
import com.example.tianjian.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
public interface ISetmealService extends IService<Setmeal> {


    Result getList();
}
