package org.youyuan.Design.build.demo;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/8/28 15:50
 */
public class DecorationPackageMenu {
    private List<Matter> matterList;

    public DecorationPackageMenu(List<Matter> matterList) {
        this.matterList = matterList;
    }

    /**
     * 根据不同业务进行扩展
     *
     * @param matter
     * @return
     */
    public DecorationPackageMenu appendLevel(Matter matter) {
        matterList.add(matter);
        return this;
    }

    public static class Builder {

    }
}
