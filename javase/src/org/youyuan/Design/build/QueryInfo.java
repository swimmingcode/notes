package org.youyuan.Design.build;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/8/28 15:04
 */
public class QueryInfo {
    private String name;
    private int age;
    private String school;
    private List<Factory> factor;

    public QueryInfo(String name, int age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "QueryInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                ", factor=" + factor +
                '}';
    }

    synchronized public void addFactory(Factory factor) {
        if (this.factor == null) {
            this.factor = new ArrayList<Factory>();
        }
        this.factor.add(factor);
    }


    static class Factory {
        private String key;
        private String value;
        private Action action;

        public Factory(String key, String value, Action action) {
            this.key = key;
            this.value = value;
            this.action = action;
        }

        @Override
        public String toString() {
            return "Factory{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    ", action=" + action +
                    '}';
        }
    }

    static enum Action {
        EQUAL,
        NOT_EQUAL;
    }

    /****************************class Builder*******************************/

    static public class Builder {

        private String name;
        private int age;
        private String school;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder school(String school) {
            this.school = school;
            return this;
        }

        public QueryInfo build() {
            return new QueryInfo(this.name,this.age,this.school);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", school='" + school + '\'' +
                    '}';
        }
    }
}
