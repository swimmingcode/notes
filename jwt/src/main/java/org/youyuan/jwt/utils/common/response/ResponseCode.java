package org.youyuan.jwt.utils.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 15:17
 */
@AllArgsConstructor
public enum ResponseCode {

    FAIL(-1,"失败"),
    OK(200,"成功"),

    USER_Name_ERROR(2001,"账户名称错误"),
    USER_PASSWORD_ERROR(2002,"账号密码错误");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

}
