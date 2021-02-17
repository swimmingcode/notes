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

    PARAMETER_EXCEPTION(300,"传入参数格式错误"),
    NULL_POINTER_EXCEPTION(400,"空指针错误"),

    USER_NAME_ERROR(2001,"账户名称错误"),
    USER_PASSWORD_ERROR(2002,"密码错误"),
    JWT_VALID_ERROR(2003,"验证JWT失败"),
    NO_TOKEN(2004,"token不存在,请重新登录"),
    TOKEN_ENCRYPTION_ERROR(205,"token解密失败"),
    TOKEN_LOGOUT(206,"账号已退出，Token失效"),
    PASSWORD_ERROR_TIMES(207,"输入密码错误超过五次，账号被锁定五分钟"),
    AUTH_CODE_ERROR(208,"验证码错误");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

}
