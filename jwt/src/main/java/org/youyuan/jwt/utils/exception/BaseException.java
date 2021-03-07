package org.youyuan.jwt.utils.exception;

import lombok.Data;
import org.youyuan.jwt.utils.common.response.ResponseCode;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 15:54
 */
@Data
public class BaseException extends RuntimeException {
    private int code;
    //message在RuntimeException中包含，不需要自定义
//    private String message;
//    private Response response;

    public BaseException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
//        response = new Response(code,message);
    }


    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }
}
