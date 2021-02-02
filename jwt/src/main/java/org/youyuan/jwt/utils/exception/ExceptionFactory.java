package org.youyuan.jwt.utils.exception;

import org.youyuan.jwt.utils.common.ResponseCode;

/**
 * @Describe: 自定义异常工厂
 * @Author: youjiancheng
 * @Date: 2021/2/2 15:55
 */
public class ExceptionFactory extends BaseException {

    public ExceptionFactory(int code, String message) {
        super(code, message);
    }

    public ExceptionFactory(ResponseCode responseCode) {
        super(responseCode.getCode(),responseCode.getMessage());
    }
}
