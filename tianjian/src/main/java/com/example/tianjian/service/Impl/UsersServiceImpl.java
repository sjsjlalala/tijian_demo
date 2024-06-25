package com.example.tianjian.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.config.JwtProperties;
import com.example.tianjian.dto.OrdersDto;
import com.example.tianjian.dto.UserDto;
import com.example.tianjian.entity.Users;
import com.example.tianjian.mapper.UsersMapper;
import com.example.tianjian.service.IUsersService;
import com.example.tianjian.util.JwtTool;
import com.example.tianjian.util.Result;
import com.example.tianjian.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Resource
    UsersMapper usersMapper;

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    @Override
    public Result login(Users user) {
        String userId = user.getUserId();
        String password = user.getPassword();
        if (userId == null || password == null) {
            return Result.fail("用户名或者密码不能为空");
        }
        Users users = usersMapper.selectById(userId);
        if (users == null) {
            return Result.fail("用户不存在");
        }
        if (users.getPassword().equals(password)) {
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(users,userDto);
            // 5.生成TOKEN
            Long userId1 = Long.valueOf(userId);
            String token = jwtTool.createToken(userId1, jwtProperties.getTokenTTL());
            userDto.setToken(token);
            return Result.ok(userDto);
        } else {
            return Result.fail("密码错误");
        }
    }

    @Override
    public Result register(Users user) {
        String userId = user.getUserId();
        Users users = usersMapper.selectById(userId);
        if(users != null){
            return Result.fail("用户已存在");
        }
        int insert = usersMapper.insert(user);
        if(insert == 0){
            return Result.fail("注册失败");
        }
        return Result.ok("注册成功");
    }


}
