package org.youyuan.service.impl;

import org.youyuan.dao.Dao;
import org.youyuan.factory.DaoFactory;
import org.youyuan.service.SpringService;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/7 18:07
 */
public class SpringServiceImpl implements SpringService {

    private Dao dao = DaoFactory.getDate1("MyDao2");

    /**
     * 配置文件读取
     * 如下格式：MyDao=org.youyuan.dao.MyDao2
     */
    private Dao daoTwo = (Dao) DaoFactory.getDate("MyDao");

    @Override
    public String getDate() {
        //需求变化时，需要切换数据库，如何处理
        //处理方法：1、构建工厂 只需要把你想要你的DAO传入即可 ，但需要修改代码，不够方便。
        //2、采用读取配置文件的方式获取

        //并非单例 每次获取都是新的对象
        //写成单例模式
        //多线程条件下，并未出现问题
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println((Dao) DaoFactory.getDate("MyDao") + Thread.currentThread().getName());
                }
            }).start();

        }
        return daoTwo.getDate();
    }
}
