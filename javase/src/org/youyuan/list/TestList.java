package org.youyuan.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/10/11 16:08
 */
public class TestList {
    private List list = new ArrayList();

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TestList{" +
                "list=" + list +
                '}';
    }

}
