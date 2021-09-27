package org.youyuan.Design.build;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/8/28 14:35
 */
public class Builder {
    public static void main(String[] args) {
        QueryInfo queryInfo = QueryInfo.builder().age(24).name("zs").school("etcd").build();
        queryInfo.addFactory(new QueryInfo.Factory("price","20", QueryInfo.Action.EQUAL));
        System.out.println(queryInfo);
    }
}
