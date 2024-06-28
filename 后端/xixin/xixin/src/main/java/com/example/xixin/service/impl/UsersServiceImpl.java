package com.example.xixin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.xixin.config.JwtProperties;
import com.example.xixin.dto.Result;
import com.example.xixin.dto.UsersDto;
import com.example.xixin.entity.Users;
import com.example.xixin.mapper.UsersMapper;
import com.example.xixin.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xixin.util.JwtTool;
import com.example.xixin.util.RegexUtils;
import com.example.xixin.util.SendSms;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private JwtTool jwtTool ;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JwtProperties jwtProperties;
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
        String token = jwtTool.createToken(Long.valueOf(users.getUserId()), Duration.ofDays(1));
        return Result.ok(usersDto,token);

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

    @Override
    public Result loginByCode(String phone, String code) {
        //1.校验手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误",500);
        };
        Users doc1 = usersMapper.selectById(phone);
        if(doc1 == null){
            return Result.fail("该手机号未注册",500);
        }
        String rCode = stringRedisTemplate.opsForValue().get("login:user:" + phone);
        if(rCode == null || !rCode.equals(code)){
            return Result.fail("验证码错误",500);
        }
        UsersDto doctorDto = new UsersDto();
        BeanUtils.copyProperties(doc1,doctorDto);
        String token = jwtTool.createToken(Long.valueOf(doc1.getUserId()), jwtProperties.getTokenTTL());
        return Result.ok(doctorDto,token);
    }

    @Override
    public Result sendCode(String phone) {
        //1.校验手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误",500);
        };
        Users doc1 = usersMapper.selectById(phone);
        if(doc1 == null){
            return Result.fail("该手机号未注册",500);
        }
        //3.符合，生成验证码
        String code = RandomUtil.randomNumbers(6);

        //4.保存验证码到redis set key value ex 120
        stringRedisTemplate.opsForValue().set("login:user:"+phone,code,10, TimeUnit.MINUTES);
        try {
            SendSms.send(phone,code);
        }catch (Exception e){
            return Result.fail("发送验证码失败",500);
        }

        //5.发送验证码
        System.out.println("发送验证码成功,验证码为"+code);

        //6.返回ok
        return Result.ok();
    }

    @Override
    public Result changepwd(String phone, String code, String newpwd) {
        //1.校验手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误",500);
        };
        Users doc1 = usersMapper.selectById(phone);
        if(doc1 == null){
            return Result.fail("该手机号未注册",500);
        }
        String rCode = stringRedisTemplate.opsForValue().get("login:user:" + phone);
        if(rCode == null || !rCode.equals(code)){
            return Result.fail("验证码错误",500);
        }
        Users doctor = this.getById(phone);
        doctor.setPassword(newpwd);
        UsersDto doctorDto = new UsersDto();
        BeanUtils.copyProperties(doctor,doctorDto);

        usersMapper.updateById(doctor);
        String token = jwtTool.createToken(Long.valueOf(doc1.getUserId()), jwtProperties.getTokenTTL());
        return Result.ok(doctorDto,token);

    }
}
