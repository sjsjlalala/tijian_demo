package com.example.xixin.util;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @projectName: xixin
 * @package: com.example.xixin.util
 * @className: IDCreater
 * @author: moki
 * @description: TODO
 * @date: 2024/6/14 11:22
 * @version: 1.0
 */
@Component
public class IdCreater {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    //开始时间戳（24年Unix 时间戳）
    private static final long BEGIN_TIMESTAMP = 757382400000L;

    public  long getNextId() {
        //1.生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long nowTimeStamp = nowSecond - BEGIN_TIMESTAMP;

        //2.生成序列号
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        //2.2自增
        long count = stringRedisTemplate.opsForValue().increment("ID:" +"Order:"+ date);
        return nowTimeStamp << 32 | count;
    }


}
