package org.youyuan.jwt.utils.exception;

import org.youyuan.jwt.utils.common.response.ResponseCode;

/**
 * @Describe: 工程模式自定义异常
 * @Author: youjiancheng
 * @Date: 2021/2/2 15:55
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
