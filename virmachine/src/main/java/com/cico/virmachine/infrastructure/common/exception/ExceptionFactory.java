package com.cico.virmachine.infrastructure.common.exception;

import com.cico.virmachine.infrastructure.common.response.ResponseCode;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/19 10:11
 */
public class ExceptionFactory extends BaseException {

    //应该由static静态方法返回
    public ExceptionFactory(int code, String message) {
        super(code, message);
    }

    public ExceptionFactory(ResponseCode responseCode) {
        super(responseCode);
    }
}
