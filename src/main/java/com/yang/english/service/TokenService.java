package com.yang.english.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class TokenService {

    private static final long EXPIRATION_TIME = 900000_000; // 15分钟
    private static final String SECRET = "YourSecretKey"; // 应该是一个私密且复杂的字符串

    public String createToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    public boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            // 无效的Token
            return false;
        }
    }
    public String getUsernameFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            // 处理无效的Token或解析错误
            return null;
        }
    }
}
