package org.youyuan.spring;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 10:51
 */
public class Test {
    public static void main(String[] args) {
        MyAnnotationConfigApplicationContext context = new MyAnnotationConfigApplicationContext("org.youyuan.spring");
        Set<BeanDefinition> beanDefinitions = context.getBeanDefinitions();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            System.out.println(beanDefinition);
        }
        context.beanDefinitionCreateObject();
        Map<String, Object> objectMap = context.getObjectMap();

        objectMap.forEach((k,v)->{
            System.out.println("beanName=" + k + "    Object=" + v);
        });


    }
}
