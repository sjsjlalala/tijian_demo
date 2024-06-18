package com.example.xixin.mapper;

import com.example.xixin.entity.Checkitem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xixin.entity.Cireport;
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
public interface CheckitemMapper extends BaseMapper<Checkitem> {

    @Select("select * from checkitem where ciId IN (select ciId from setmealdetailed where smId = #{smId})")
    List<Checkitem> getCheckitemBySetmealId(Integer smId);

    List<Cireport> selectCheckitems(Integer smId);
}
