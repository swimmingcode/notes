package org.youyuan.exception;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 18:09
 */
public class DaoException extends BaseException{
    public DaoException (ResponseCode responseCode) {
        super(responseCode);
    }

    public DaoException (String message, int code) {
        super(message,code);
    }

}
