package org.youyuan.jwt.utils.diyenum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/17 15:36
 */
@AllArgsConstructor
public enum EmailCodeType {

    PASSWORD("修改密码"),

    BIND_EMAIL("绑定邮箱");

    @Setter
    @Getter
    String message;
}
