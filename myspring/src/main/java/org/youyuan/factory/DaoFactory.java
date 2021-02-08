package org.youyuan.factory;

import org.youyuan.dao.Dao;
import org.youyuan.dao.MyDao1;
import org.youyuan.dao.MyDao2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
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

    static Map map = new HashMap<String,Object>();

    /**
     * 配置文件
     */
    static Properties properties = new Properties();

    static {
        //读取配置文件
        InputStream resourceAsStream = DaoFactory.class.getClassLoader().getResourceAsStream("data.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            System.out.println("读取错误");
            e.printStackTrace();
        }
    }

    /**
     * 单例模式，加缓冲
     *
     * @param
     * @return
     */
    public static Object getDate(String className) {
        String packageClass = ((String) properties.get(className));
        //判断是否已经创建 双重锁检测
        if (!map.containsKey(packageClass)) {
            //根据反射创建对象
            try {
                synchronized (DaoFactory.class) {
                    if (!map.containsKey(packageClass)) {
                        Class<?> aClass = Class.forName(packageClass);
                        Object o = aClass.getConstructor().newInstance();
                        if (o == null) {
                            throw new RuntimeException("配置出错，无此类");
                        }
                        map.put(packageClass, o);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map.get(packageClass);
    }

    public static void main(String[] args) {
        getDate("MyDao");
    }
}
