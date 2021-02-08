package org.youyuan.spring;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 10:50
 */
public class BeanDefinition {

    private String beanName;
    private Class className;

    public BeanDefinition(String beanName, Class className) {
        this.beanName = beanName;
        this.className = className;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "beanName='" + beanName + '\'' +
                ", className=" + className +
                '}';
    }
}
