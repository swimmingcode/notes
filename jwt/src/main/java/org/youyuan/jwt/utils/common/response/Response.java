package org.youyuan.jwt.utils.common.response;

import lombok.Data;

import java.util.ArrayList;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 15:26
 */
@Data
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response(ResponseCode responseCode,T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    public Response(int code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Response(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        // TODO: 2021/2/12 如何返回一个{}
    }


    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }


}
