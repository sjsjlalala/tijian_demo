package com.example.tianjian.service.Impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.config.JwtProperties;
import com.example.tianjian.dto.DoctorDto;
import com.example.tianjian.entity.Doctor;
import com.example.tianjian.mapper.DoctorMapper;
import com.example.tianjian.service.IDoctorService;
import com.example.tianjian.util.JwtTool;
import com.example.tianjian.util.RegexUtils;
import com.example.tianjian.util.Result;
import com.example.tianjian.util.SendSms;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiestrong
 * @since 2024-06-13
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements IDoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JwtTool JwtUtil;
    @Resource
    private JwtProperties jwtProperties;
    @Override
    public Result login(Doctor doctor) {
        String docId = doctor.getDocId();
        String docCode = doctor.getDocCode();
        String password = doctor.getPassword();
        if ((docCode == null && docId == null) || password == null) {
            return Result.fail("医生名或者密码不能为空");
        }
        Doctor doc = doctorMapper.selectOne(new QueryWrapper<Doctor>().eq("docCode", docCode));

        if (doc == null ) {
            return Result.fail("医生名不存在");
        }
        DoctorDto doctorDto = new DoctorDto();

        if (doc.getPassword().equals(password)) {
            BeanUtils.copyProperties(doc,doctorDto);
            doctorDto.setToken(JwtUtil.createToken(Long.valueOf(doc.getDocId()),jwtProperties.getTokenTTL()));
            return Result.ok(doctorDto);
        }
            return Result.fail("密码错误");

    }

    @Override
    public Result sendCode(String phone) {

        //1.校验手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误");
        };
        Doctor doc1 = doctorMapper.selectById(phone);
        if(doc1 == null){
            return Result.fail("该手机号未注册");
        }
        //3.符合，生成验证码
        String code = RandomUtil.randomNumbers(6);

        //4.保存验证码到redis set key value ex 120
        try {
            SendSms.send(phone, code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
         stringRedisTemplate.opsForValue().set("login:doctor:"+phone,code,2, TimeUnit.MINUTES);

        //5.发送验证码
        System.out.println("发送验证码成功,验证码为"+code);

        //6.返回ok
        return Result.ok();
    }
    @Override
    public Result loginByCode(String phone, String code) {
        //1.校验手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误");
        };
        Doctor doc1 = doctorMapper.selectById(phone);
        if(doc1 == null){
            return Result.fail("该手机号未注册");
        }
        String rCode = stringRedisTemplate.opsForValue().get("login:doctor:" + phone);
        if(rCode == null || !rCode.equals(code)){
            return Result.fail("验证码错误");
        }
        DoctorDto doctorDto = new DoctorDto();
        BeanUtils.copyProperties(doc1,doctorDto);
        doctorDto.setToken(JwtUtil.createToken(Long.valueOf(doc1.getDocId()),jwtProperties.getTokenTTL()));
        return Result.ok(doctorDto);
    }

    @Override
    public Result changepwd(String phone, String code, String newpwd) {
        //1.校验手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            //2.如果不符合，返回错误信息
            return Result.fail("手机号格式错误");
        };
        Doctor doc1 = doctorMapper.selectById(phone);
        if(doc1 == null){
            return Result.fail("该手机号未注册");
        }
        String rCode = stringRedisTemplate.opsForValue().get("login:doctor:" + phone);
        if(rCode == null || !rCode.equals(code)){
            return Result.fail("验证码错误");
        }
        Doctor doctor = this.getById(phone);
        doctorMapper.updateById(doctor.setPassword(newpwd));
        return Result.ok(doctor);
    }
}
