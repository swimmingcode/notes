package org.youyuan.factory;

import org.youyuan.dao.Dao;
import org.youyuan.dao.MyDao1;
import org.youyuan.dao.MyDao2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Describe: 工厂模式返回DAO
 * @Author: youjiancheng
 * @Date: 2021/2/7 18:14
 */
public class DaoFactory {

    /**
     * 根据传递过来的参数直接返回DAO
     *
     * @param type
     * @return
     */
    public static Dao getDate1(String type) {
        // TODO: 2021/2/7 根据不同类型返回不同DAO
        if (type.equals("MyDao1")) {
            return new MyDao1();
        } else if (type.equals("MyDao2")){
            return new MyDao2();
        }
        return null;

    }


    public static Dao getDate(String type) {
        //读取配置文件
        InputStream resourceAsStream = DaoFactory.class.getClassLoader().getResourceAsStream("data.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            System.out.println("读取错误");
            e.printStackTrace();
        }
        Object myDao = properties.get("MyDao");

        //根据反射创建对象
        DaoFactory.class;
        return null;
    }

    public static void main(String[] args) {
        getDate("MyDao");
    }
}
