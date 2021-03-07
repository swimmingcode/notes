package org.youyuan.spring.strategy;

import org.youyuan.spring.Autowired;

import java.lang.reflect.Field;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 14:58
 */
public class AutowiredAnnotation implements IocAnnotation {

    @Override
    public Object getIocObject(Field field, Class aClass) {
        try {
            Object o = aClass.newInstance();
            Autowired valueAnnotation = field.getAnnotation(Autowired.class);
            if (valueAnnotation != null) {
                Class<?> type = field.getType();
                o = type.newInstance();
                System.out.println(o);
                //赋值
            } else {
                throw new RuntimeException("无此注解");
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
