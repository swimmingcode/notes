package org.youyuan.exception;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 17:54
 */
public class ServiceException extends BaseException{

    public ServiceException (ResponseCode responseCode) {
        super(responseCode);
    }

    public ServiceException (String message, int code) {
        super(message,code);
    }


}
