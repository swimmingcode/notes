package org.youyuan.jwt.utils.exception;

import lombok.Data;
import org.youyuan.jwt.utils.common.Response;
import org.youyuan.jwt.utils.common.ResponseCode;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 15:54
 */
@Data
public abstract class BaseException extends RuntimeException {
    private int code;
    private String message;
    private Response response;

    public BaseException(int code, String message) {
        super(message);
        response = new Response(code,message);
    }



}
