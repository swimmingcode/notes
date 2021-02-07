package org.youyuan.relationship;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/3 22:30
 */
public class Person {
    private int id;
    private String name;

    public Person(int id) {
        this.id = id;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
