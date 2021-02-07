package org.youyuan.service.impl;

import org.youyuan.dao.Dao;
import org.youyuan.dao.MyDao1;
import org.youyuan.factory.DaoFactory;
import org.youyuan.service.SpringService;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/7 18:07
 */
public class SpringServiceImpl implements SpringService {

    private Dao dao = DaoFactory.getDate("MyDao2");

    public String getDate() {
        //需求变化时，需要切换数据库，如何处理
        //处理方法：1、构建工厂 只需要把你想要你的DAO传入即可 ，但需要修改代码，不够方便。
        //2、采用读取配置文件的方式获取
        return dao.getDate();
    }
}
