package com.cico.virmachine.infrastructure.common.exception;

import com.cico.virmachine.infrastructure.common.response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/19 10:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private int code;

    public BaseException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }


    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }
}
