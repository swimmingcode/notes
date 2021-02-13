package org.youyuan.exception;

/**
 * @Describe: 继承RunTime类，并添加code字段
 * @Author: youjiancheng
 * @Date: 2021/2/3 17:30
 */
public class BaseException extends RuntimeException {

    private int code;


    public BaseException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code=" + code +
                '}';
    }
}
