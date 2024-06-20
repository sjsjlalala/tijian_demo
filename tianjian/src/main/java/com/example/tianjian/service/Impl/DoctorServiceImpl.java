package com.example.tianjian.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tianjian.dto.DoctorDto;
import com.example.tianjian.dto.UserDto;
import com.example.tianjian.entity.Doctor;
import com.example.tianjian.entity.Users;
import com.example.tianjian.mapper.DoctorMapper;
import com.example.tianjian.service.IDoctorService;
import com.example.tianjian.util.JwtUtil;
import com.example.tianjian.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements IDoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    @Override
    public Result login(Doctor doctor) {
        String docId = doctor.getDocId();
        String docCode = doctor.getDocCode();
        String password = doctor.getPassword();
        if ((docCode == null && docId == null) || password == null) {
            return Result.fail("医生名或者密码不能为空");
        }
        Doctor doc = doctorMapper.selectOne(new QueryWrapper<Doctor>().eq("docCode", docCode));
        Doctor doc1 = doctorMapper.selectById(docId);
        if (doc == null && doc1 == null) {
            return Result.fail("医生名不存在");
        }
        DoctorDto doctorDto = new DoctorDto();
        if(doc != null){
        if (doc.getPassword().equals(password)) {
            BeanUtils.copyProperties(doc,doctorDto);
            doctorDto.setToken(JwtUtil.createToken(doc.getDocCode(),"doctor"));
        }return Result.ok(doctorDto);
        } else if (doc1.getPassword().equals(password)) {
            BeanUtils.copyProperties(doc1,doctorDto);
            doctorDto.setToken(JwtUtil.createToken(doc1.getDocCode(),"doctor"));
            return Result.ok(doctorDto);
        } else {
            return Result.fail("密码错误");
        }
    }
}
