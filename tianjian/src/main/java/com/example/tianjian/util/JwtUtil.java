package com.example.tianjian.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static long time=1000*60*60*24;
    private static String signature="admimn";
    public static String createToken(String username,String role){
        // 生成一个256位的密钥，适合HS256算法
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
        Key secretKey = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("username", username)
                .claim("role", role)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(secretKey)
                .compact();

        return jwtToken;
    }
}
