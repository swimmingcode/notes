package org.youyuan.jwt.utils.common;

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
    OK(200,"成功");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

}