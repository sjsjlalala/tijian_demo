package com.example.xixin.mapper;

import com.example.xixin.dto.SetmealDto;
import com.example.xixin.entity.Setmeal;
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
public interface SetmealMapper extends BaseMapper<Setmeal> {

    @Select("select * from setmeal where type = #{sex}")
    List<SetmealDto> getSetmelBySex(Integer sex);
}
