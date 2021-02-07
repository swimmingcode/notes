package org.youyuan.relationship;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 22:31
 */
public class Children extends Person{

    private int age;

    public Children(int age,int id,String name) {
        super(id);
//        super(id,name);
        this.age = age;

    }

    @Override
    public String toString() {
        return "Children{" +
                "age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
