package org.youyuan.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 10:46
 */
public class MyAnnotationConfigApplicationContext {

    /**
     * 包下的所有Class
     */
    private  Set<Class<?>> classes;

    /**
     * 所有的BeanDefine
     */
    private Set<BeanDefinition> beanDefinitionsIOC = new HashSet<BeanDefinition>();

    /**
     * Bean对象：key/value=beanName/Object
     */
    private Map<String,Object> objectMap = new HashMap<>();

    public MyAnnotationConfigApplicationContext(String packageName) {
        //1、获取包下的所有Class
        this.classes = MyTools.getClasses(packageName);
        //2、获取所有的BeanDefine
        this.beanDefinitionsIOC = getBeanDefinitionsIOC();
        //3、根据原材料创建bean
        beanDefinitionCreateObject();
        //4、Autowired自动注入
        beanDefinitionAutowired();
    }


    public Object getBean(String beanName) {
        return objectMap.get(beanName);
    }

    /**
     * 获取所有Bean的名字
     *
     * @return
     */
    public String[] getBeanNames() {
        Set<String> strings = this.objectMap.keySet();
        return strings.toArray(new String[0]);
    }

    /**
     * 针对Autowired/Qualified注解进行赋值
     */
    public void beanDefinitionAutowired()  {
        Iterator<BeanDefinition> iterator = this.beanDefinitionsIOC.iterator();
        while (iterator.hasNext()) {
            BeanDefinition next = iterator.next();
            Class className = next.getClassName();
            for (Field declaredField : className.getDeclaredFields()) {
                Autowired annotation = declaredField.getAnnotation(Autowired.class);
                if (annotation != null) {
                    Qualifier qualifier = declaredField.getAnnotation(Qualifier.class);
                    if (qualifier != null) {
                        //通过名称注入
                        String value = qualifier.value();
                        //需要赋值的Bean
                        Object bean = getBean(value);
                        try {
                            Method method = className.getMethod("set" + value.substring(0, 1).toUpperCase() + value.substring(1),declaredField.getType());
                            method.invoke(getBean(next.getBeanName()),bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        //通过类型自动注入
                        //该字段的字节码类型
                        Class<?> type = declaredField.getType();
                        //该类的Bean
                        //需要找到该字段的Bean，再给其赋值
                        Object bean = getBean(next.getBeanName());
                        String name = declaredField.getName();
                        try {
                            Method method = className.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1),declaredField.getType());
                            //将实例化后的Bean注入
                            Object o = getIocObjectByBeanClass(type);
                            method.invoke(bean, o);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    /**
     * 通过class获取IOC的Bean对象
     *
     * @param type
     * @return
     */
    public Object getIocObjectByBeanClass(Class<?> type ) {
        //先获取BeanName
        Iterator<BeanDefinition> iterator = this.getBeanDefinitionsIOC().iterator();
        while (iterator.hasNext()) {
            BeanDefinition next = iterator.next();
            if (next.getClassName() == type) {
                return getBean(next.getBeanName());
            }
        }
        return null;
    }


    /**
     * beanDefinitions注入值
     * @Value注解
     * @Autowired 需要在@Value注入之后才能进行注入
     */
    public void beanDefinitionCreateObject() {
        try {
            for (BeanDefinition beanDefinition : this.beanDefinitionsIOC) {
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
                                    String name = field.getType().getName();
                                    if ("java.lang.Integer".equals(name)) {
                                        int i = Integer.parseInt(value);
                                        method.invoke(o, i);
                                    } else if ("java.lang.String".equals(name)) {
                                        String s = String.valueOf(value);
                                        method.invoke(o, s);
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
     * 获取BeanDefinition
     *
     * @return
     */
    public Set<BeanDefinition> getBeanDefinitionsIOC() {
        Set<BeanDefinition> beanDefinitions = new HashSet<BeanDefinition>();
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
                    beanDefinitions.add(beanDefinition);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return beanDefinitions;
    }



    public Set<Class<?>> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class<?>> classes) {
        this.classes = classes;
    }

    public void setBeanDefinitionsIOC(Set<BeanDefinition> beanDefinitionsIOC) {
        this.beanDefinitionsIOC = beanDefinitionsIOC;
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }
}
