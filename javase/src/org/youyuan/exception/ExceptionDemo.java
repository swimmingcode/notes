package org.youyuan.exception;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 17:26
 */
public class ExceptionDemo {

    public static void main(String[] args) throws ServiceException {
        int x = 0;
        if (x > 1) {
            throw new BaseException(ResponseCode.OK);
        }
        //对new BaseException进行再一次的封装
        //采用工程模式，对不同层次的异常进行封装
       // throw new ServiceException(ResponseCode.OK);
        //throw ExceptionFactory.serviceException(ResponseCode.OK);
        throw new DaoException(ResponseCode.OK);

    }
}
