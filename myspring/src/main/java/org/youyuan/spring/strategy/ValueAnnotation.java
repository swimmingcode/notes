package org.youyuan.spring.strategy;

import org.youyuan.spring.Value;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 14:34
 */
public class ValueAnnotation implements IocAnnotation {
    @Override
    public Object getIocObject(Field field,Class aClass){
        try {
            Object o = aClass.newInstance();
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (valueAnnotation != null) {
                //对其进行赋值 value的值都为String，需要相应转换
                String value = valueAnnotation.value();
                //添加set方法 调用set方法对其进行赋值
    //                        System.out.println(field.getName());
                String methodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length());
                Method method = aClass.getMethod(methodName,field.getType());
    //                        System.out.println(field.getType().getName());
                switch (field.getType().getName()) {
                    case "java.lang.Integer":
                        int i = Integer.parseInt(value);
                        method.invoke(o,i);
                        break;
                    case "java.lang.String":
                        String s = String.valueOf(value);
                        method.invoke(o,s);
                        break;
                }
                return o;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
