package com.school.flashmeal.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.Security;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String key = "LiJiaMing3613||1970502785||Aa,15868824857";
    private static final Long time = 60*60*1000*24L;
    private static final SecretKey KEY= Keys.hmacShaKeyFor(key.getBytes());

    public static String creatJwt(Map<String,Object> keyMap){
        return Jwts.builder()
                .claims(keyMap)         //返回JSON格式
                .issuedAt(new Date())   //什么时候产生
                .expiration(new Date(System.currentTimeMillis()+time))      //什么时候结束
                .signWith(KEY)          //使用HMAC签名加密
                .compact();             //拼接成字符串
    }

    public static Claims parseJwt(String token){
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
