package com.example.xixin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.xixin.dto.Result;
import com.example.xixin.dto.UsersDto;
import com.example.xixin.entity.Users;
import com.example.xixin.mapper.UsersMapper;
import com.example.xixin.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import static com.example.xixin.dto.Result.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Resource
    private UsersMapper usersMapper;
    @Override
    public Result login(Users user, HttpSession session) {
        //1.查看是否有该用户
        boolean exists = usersMapper.exists(new QueryWrapper<Users>().lambda().eq(Users::getUserId, user.getUserId()));
        if(!exists)
            //不存在
            return Result.fail("用户不存在",USERID_ERROR);


        //2.查看密码是否正确
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Users::getUserId,user.getUserId()).eq(Users::getPassword,user.getPassword());
        Users users = usersMapper.selectOne(queryWrapper);

        if(users==null)
            //密码错误
            return Result.fail("用户名或密码错误",USERID_OR_PASSWORD_ERROR);

        //传回UsersDto
        UsersDto usersDto = new UsersDto();
        BeanUtil.copyProperties(users,usersDto);
        session.setAttribute("user",usersDto);
        return Result.ok(usersDto);

    }

    @Override
    public Result register(Users user) {
        //1.判断用户是否已经注册
        if(usersMapper.exists(new QueryWrapper<Users>().lambda().eq(Users::getUserId, user.getUserId())))
            return Result.fail("用户已存在",USER_EXIST);

        //2.注册用户
        int insert = usersMapper.insert(user);
        if(insert==0)
            return Result.fail("注册失败",REGISTER_FAIL);
        return Result.ok("注册成功");
    }
}
