package org.youyuan.web.spi;

import java.util.ServiceLoader;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/4 20:18
 */
public class App {

    public static void main(String[] args) {
        ServiceLoader<SpiService> loaders = ServiceLoader.load(SpiService.class);
        loaders.forEach(SpiService::test);
    }

}
