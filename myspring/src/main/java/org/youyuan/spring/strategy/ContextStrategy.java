package org.youyuan.spring.strategy;

import java.lang.reflect.Field;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 14:46
 */
public class ContextStrategy {

    IocAnnotation iocAnnotation;

    public ContextStrategy(IocAnnotation iocAnnotation) {
        this.iocAnnotation = iocAnnotation;
    }

    public Object strategy(Field field, Class aClass) {
        return this.iocAnnotation.getIocObject(field,aClass);
    }

}
