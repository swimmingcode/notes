package org.youyuan.spring.strategy;

import java.lang.reflect.Field;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 14:30
 */
public interface IocAnnotation {
    public Object getIocObject(Field field,Class aClass);
}
