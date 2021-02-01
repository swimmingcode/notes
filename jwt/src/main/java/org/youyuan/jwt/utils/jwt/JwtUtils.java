package org.youyuan.jwt.utils.jwt;

import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: JWT工具：用于生成、验证Token
 *  JwtToken生成的工具类
 *  JWT token的格式：header.payload.signature
 *  header的格式（算法、token的类型）：
 *  {"alg": "HS512","typ": "JWT"}
 *  payload的格式（用户名、创建时间、生成时间）：
 *  {"name":"wang","created":1489079981393,"exp":1489684781}
 *  signature的生成算法：
 *  HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 *
 * @Author: youjiancheng
 * @Date: 2021/1/30 21:16
 */
@Slf4j
@Data
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private  final String TOKEN = "token";


    /**
     * 创建JWT实例
     *
     * @param token 加密的token字符串
     * @return
     */
    public String createJWT(String token) {

        //使用JWT签名算法对令牌进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //使用密钥签署JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //设置Header
        HashMap<String, Object> headMap = new HashMap<>();
        headMap.put("alg","HS512");
        headMap.put("typ","JWT");
        //设置claims
        HashMap<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(TOKEN,token);
        //设置JWT
        JwtBuilder builder = Jwts.builder()
                .setHeader(headMap)
                .setIssuedAt(now)
                .setClaims(claimsMap)
                .signWith(signatureAlgorithm, signingKey);
        if (expiration >= 0) {
            long expMillis = nowMillis + expiration;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    /**
     * 验证JWT
     *
     * @param jwt
     * @return
     */
    public  String parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(jwt)
                .getBody();
        return ((String) claims.get(TOKEN));
//        System.out.println("ID: " + claims.getId());
//        System.out.println("Subject: " + claims.getSubject());
//        System.out.println("Issuer: " + claims.getIssuer());
//        System.out.println("Expiration: " + claims.getExpiration());
    }


}
