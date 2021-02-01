package org.youyuan.jwt.utils.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.youyuan.jwt.utils.encryption.RsaEncryptionUtils;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/1 14:14
 */
@Slf4j
public class TokenUtils {


    @Value(value = "${rsa.publicKey}")
    private static String publicKey;

    @Value(value = "${rsa.privateKey}")
    private static String privateKey;

    /**
     * 创建Token字符串，包含用户信息
     * 采用RSA加密
     *
     * @param id
     * @param name
     * @return
     */
    public static String toTokenString(Integer id,String name) {
        Token token = Token.builder()
                .id(id)
                .name(name)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String tokenRes = null;
        //加密
        try {
            byte[] bytes = new RsaEncryptionUtils().decryptByPublicKey(objectMapper.writeValueAsString(token).getBytes(), Base64.decode(publicKey));
            //转成String
            tokenRes = Base64.encode(bytes);
        } catch (Exception e) {
            log.info("token加密失败");
        }
        return tokenRes;
    }

    /**
     *解析Token字符串为实体类Token
     *
     * @param token
     * @return
     */
    public static Token toTokenPO(String token) {
        Token tokenRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] decode = Base64.decode(token);
        try {
            byte[] bytes = new RsaEncryptionUtils().decryptByPrivateKey(decode, Base64.decode(privateKey));
            tokenRes = objectMapper.readValue(new String(bytes,"UTF-8"),Token.class);
        } catch (Exception e) {
            log.info("token解密失败");
        }
        return tokenRes;
    }
}
