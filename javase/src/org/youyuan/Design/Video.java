package org.youyuan.Design;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/8/27 9:38
 */

import java.util.Date;

/**
 * 1、实现一个接口 Cloneable
 * 2、重写一个方法
 * */
//video
public class Video implements Cloneable {
    private String name;
    private Date createTime;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object = super.clone();
        //实现深克隆
        Video v = (Video) object;
        v.createTime = ((Date) this.createTime.clone());
        return super.clone();
    }

    public Video(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }

    public Video() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        //原型对象 v1
        Date date = new Date();
        Video v1 = new Video("youyuan", date);
        //v1 克隆 v2
        Video v2 = (Video) v1.clone();//克隆出来的对象和原来是一模一样的
        System.out.println("v1="+v1);
        System.out.println("v2="+v2);
        System.out.println("=========================");
        date.setTime(12435);

        System.out.println("v1="+v1);
        System.out.println("v2="+v2);
    }

}


