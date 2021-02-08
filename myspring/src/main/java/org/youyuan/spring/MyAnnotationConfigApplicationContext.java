package org.youyuan.spring;

import org.youyuan.spring.strategy.AutowiredAnnotation;
import org.youyuan.spring.strategy.ContextStrategy;
import org.youyuan.spring.strategy.ValueAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 10:46
 */
public class MyAnnotationConfigApplicationContext {

    private  Set<Class<?>> classes;

    private Set<BeanDefinition> beanDefinitions = new HashSet<BeanDefinition>();

    private Map<String,Object> objectMap = new HashMap<>();

    public MyAnnotationConfigApplicationContext(String packageName) {
        this.classes = MyTools.getClasses(packageName);
    }


    /**
     * 针对Autowired注解进行赋值
     */
    public void beanDefinitionAutowired() {
        try {
            for (BeanDefinition beanDefinition : this.beanDefinitions) {
                String beanName = beanDefinition.getBeanName();
                Class aClass = beanDefinition.getClassName();
                Object o = aClass.newInstance();;
                //创建对象并给其赋值
                for (Field field : aClass.getDeclaredFields()) {
                    Annotation[] annotations = field.getAnnotations();
                    for (Annotation annotation : annotations) {
                        //会有问题 每一个属性返回一个Object 之前的会被覆盖掉
                        String annotationName = annotation.toString();
                        if (annotationName.contains("Autowired")) {
                            try {
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
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                this.objectMap.put(beanName,o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * beanDefinitions注入值
     * @Value注解
     * @Autowired 需要在@Value注入之后才能进行注入
     */
    public void beanDefinitionCreateObject() {
        try {
            for (BeanDefinition beanDefinition : this.beanDefinitions) {
                String beanName = beanDefinition.getBeanName();
                Class aClass = beanDefinition.getClassName();
                Object o = aClass.newInstance();;
                //创建对象并给其赋值
                for (Field field : aClass.getDeclaredFields()) {
                    Annotation[] annotations = field.getAnnotations();
                    for (Annotation annotation : annotations) {
                        //会有问题 每一个属性返回一个Object 之前的会被覆盖掉
                        String annotationName = annotation.toString();
                        if (annotationName.contains("value")) {
                            try {
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
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                this.objectMap.put(beanName,o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        beanDefinitionAutowired();
    }

    /**
     * 获取BeanDefinition
     *
     * @return
     */
    public Set<BeanDefinition> getBeanDefinitions() {
        Set<Class<?>> classes = this.classes;
        for (Class<?> aClass : classes) {
            Component component = aClass.getAnnotation(Component.class);
            if (component != null) {
                try {
                    //获取BeanName与class
                    String value = component.value();
                    //默认赋值
                    if (value == null || ("").equals(value)) {
                        String className = aClass.getName();
                        String packageName = aClass.getPackage().getName();
                        String s = className.replace(packageName + ".", "");
                        value = s.substring(0, 1).toLowerCase() + s.substring(1, s.length());
                    }
                    BeanDefinition beanDefinition = new BeanDefinition(value , aClass);
                    this.beanDefinitions.add(beanDefinition);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this.beanDefinitions;
    }



    public Set<Class<?>> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class<?>> classes) {
        this.classes = classes;
    }

    public void setBeanDefinitions(Set<BeanDefinition> beanDefinitions) {
        this.beanDefinitions = beanDefinitions;
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }
}
