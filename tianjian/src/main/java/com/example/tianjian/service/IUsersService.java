package com.example.tianjian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tianjian.entity.Users;
import com.example.tianjian.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
public interface IUsersService extends IService<Users> {

    Result login(Users user);

    Result register(Users user);
}
