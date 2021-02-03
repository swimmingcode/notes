package org.youyuan.exception;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 18:10
 */
public class ExceptionFactory {

    public static ServiceException serviceException(ResponseCode response) {
        return new ServiceException(response);
    }

    public static DaoException daoException(ResponseCode response) {
        return new DaoException(response);
    }

}
