package org.youyuan.jwt.utils.encryption;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Describe: md5加密函数，不可逆，用于用于密码加密
 * @Author: youjiancheng
 * @Date: 2021/1/30 21:46
 */
@Component
public class Md5Utils {

    @Value(value = "${md5.salt}")
    private  String salt;

    public String encryptedMd5(String str) {
        //加密两次
        return DigestUtils.md5Hex(DigestUtils.md5Hex(str + salt) + salt);
    }

}
