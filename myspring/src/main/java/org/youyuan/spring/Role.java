package org.youyuan.spring;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/8 10:58
 */
@Component
public class Role {

    @Value(value = "超级管理员")
    private String name;

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
