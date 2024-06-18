package com.example.xixin.service.impl;

import com.example.xixin.dto.Result;
import com.example.xixin.dto.SetmealDto;
import com.example.xixin.entity.Checkitem;
import com.example.xixin.entity.Setmeal;
import com.example.xixin.mapper.CheckitemMapper;
import com.example.xixin.mapper.SetmealMapper;
import com.example.xixin.service.ISetmealService;
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
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private CheckitemMapper checkitemMapper;
    @Override
    public Result getSetmelBySex(Integer sex) {
        List<SetmealDto> setmels = setmealMapper.getSetmelBySex(sex);
        for(SetmealDto setmealDto:setmels){
            List<Checkitem>  checkitem = checkitemMapper.getCheckitemBySetmealId(setmealDto.getSmId());
            setmealDto.setCheckitem(checkitem);
        }
        return Result.ok(setmels, (long) setmels.size());
    }
}
