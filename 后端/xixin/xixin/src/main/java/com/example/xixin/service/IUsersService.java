package com.example.xixin.service;

import com.example.xixin.entity.Users;
import com.example.xixin.dto.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author moki
 * @since 2024-06-13
 */
public interface IUsersService extends IService<Users> {

    Result login(Users user, HttpSession session);

    Result register(Users user);

    Result loginByCode(String phone, String code);

    Result sendCode(String phone);

    Result changepwd(String phone, String code, String newpwd);
}
