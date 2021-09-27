package org.youyuan.Design.build.demo;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/8/28 16:19
 */
public interface Matter {
    /**
     * 场景
     *
     * @return
     */
    String scene();

    /**
     * 品牌
     *
     * @return
     */
    String brand();

    /**
     * 型号
     *
     * @return
     */
    String model();


    /**
     * 价格
     *
     * @return
     */
    String price();

    /**
     * 描述
     *
     * @return
     */
    String desc();
}