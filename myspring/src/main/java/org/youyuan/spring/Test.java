package org.youyuan.spring;

import java.util.Iterator;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 10:51
 */
public class Test {
    public static void main(String[] args) {
        MyAnnotationConfigApplicationContext context = new MyAnnotationConfigApplicationContext("org.youyuan.spring");
        Iterator<BeanDefinition> iterator = context.getBeanDefinitionsIOC().iterator();
        while (iterator.hasNext()) {
            BeanDefinition next = iterator.next();
            System.out.println(next.getBeanName()+"------>"+next.getClassName());
        }

        context.getObjectMap().forEach((k,v)->{
            System.out.println(k + "------>" + v);
        });

        for (String beanName : context.getBeanNames()) {
            System.out.println(beanName);
        }
    }
}
