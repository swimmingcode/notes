package com.cico.virmachine.infrastructure.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Describe: http返回结果
 * @Author: youjiancheng
 * @date 2021/4/16 16:06
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
        class Empty {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            public List<Integer> e = null;
        }
        this.data = (T) new Empty();
    }


    public Response(int code, String message) {
        this.code = code;
        this.message = message;
        class Empty {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            public List<Integer> e = null;
        }
        this.data = (T) new Empty();
    }


}
