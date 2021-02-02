package org.youyuan.jwt.utils.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.youyuan.jwt.domain.RolePO;
import org.youyuan.jwt.utils.encryption.RsaEncryptionUtils;
import sun.misc.BASE64Encoder;

import java.util.List;
import java.util.Random;

/**
 * @Describe: 自定义Token，用于存储用户的信息
 * @Author: youjiancheng
 * @Date: 2021/1/30 21:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Token {

    private  Integer id;

    private String name;

}
