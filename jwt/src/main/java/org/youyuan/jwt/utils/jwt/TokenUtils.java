package org.youyuan.jwt.utils.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.encryption.RsaEncryptionUtils;
import org.youyuan.jwt.utils.exception.ExceptionFactory;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/1 14:14
 */
@Slf4j
@Component
public class TokenUtils {


    @Value(value = "${rsa.publicKey}")
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCeNXRYGjs0HciycFo7wiNac3rDWq7DsD6UpJPHmc+2KReKSIvs/1aC9o4pX6wDTkVovlYmAisF7JxLs6KBwAQ1S/Q8MR0Ayrx4pc6ABlFy5saPAKnTrPS5XoyLnW0ETVwDQcHWCcBQVx1QXBA3k8T7z9Rhfah0cZCl2dd1GOFebwIDAQAB";

    @Value(value = "${rsa.privateKey}")
    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ41dFgaOzQdyLJwWjvCI1pzesNarsOwPpSkk8eZz7YpF4pIi+z/VoL2jilfrANORWi+ViYCKwXsnEuzooHABDVL9DwxHQDKvHilzoAGUXLmxo8AqdOs9LlejIudbQRNXANBwdYJwFBXHVBcEDeTxPvP1GF9qHRxkKXZ13UY4V5vAgMBAAECgYBb7m/Wqhdw50Wx+ntUcjEc5DGXxt/6sjULJr8Aoy4Bics1/YXhh1X7780X2ZIYsai/fqqMl4HshVsk2hM6Fhva9pzDwKGg9LcmP1JFeEnKkimKYIz4t33uKtFtvlgX+DVbXATxM5RvfYZQreOaWvfuqoabrgbMDFc5iq3YYlAqQQJBAPHET6H0KMKFLzy4OwAK4E686a0FiCQNcJGJsucXrB4ybQ0aiAj0iSba/1JNA27iF/4OpjXFKsKhM9DH3UOLvHECQQCnhdVDf0FsLSgNFOSYdu1mRzgB6n6fFTNB5t1I8tjA67C8eDaXqT62P/0/44zH7Yph5AViMiPsTTx8ZLe1p7jfAkBogJqLPqZ/rmIEtP2LvyfF4RKAn2uBae3GeubBzRGw28sXZuh2Nn0yNmc2g+OAAmqsfliJ1XfuOQ/Upw+VKsmBAkABSSas4PIomWQgTZO/k9IYod1NLnaQVAJTtCjooWyGdoihua55lYP0tCMGdYGkGy+ftrdP3KCn9KPNNJywWTwzAkEAxO4d2iDcvrOCAtxDPMw0nmaeJTtmxcTCDPhhyuqOfNY5YYgkWvYxE1Y1tODRfFj8gfEFrULdttQA237p8YjFuw==";

    /**
     * 创建Token字符串，包含用户信息
     * 采用RSA加密
     *
     * @return
     */
    public  String toTokenString(Token token) {
        ObjectMapper objectMapper = new ObjectMapper();
        String tokenRes = null;
        //加密
        try {
            byte[] bytes = new RsaEncryptionUtils().encryptByPublicKey(objectMapper.writeValueAsString(token).getBytes(), Base64.decode(publicKey));
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
    public Token toTokenEntity(String token) {
        Token tokenRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] decode = Base64.decode(token);
        try {
            byte[] bytes = new RsaEncryptionUtils().decryptByPrivateKey(decode, Base64.decode(privateKey));
            tokenRes = objectMapper.readValue(new String(bytes,"UTF-8"),Token.class);
        } catch (Exception e) {
            log.info("token解密失败");
            throw new ExceptionFactory(ResponseCode.TOKEN_ENCRYPTION_ERROR);
        }
        return tokenRes;
    }

    public static void main(String[] args) {
        Token token = new TokenUtils().toTokenEntity("c/pX0QsbMe+9IDf8Jk13YSNH/LVcq+MnMXfeJDthHq/C8VYnfL81D9NI6xKMIEhUxmAElJJloyfS/R7oO1gZZoj0+hi6VMdWClDftexHWIlNAqvBBManA1YIqrX9gmKzzsRSi7PJ0M4r8xsFOqehCE9hPIzPuh+JQSgxNcIDJco=");
        log.info("{}",token);
    }
}
