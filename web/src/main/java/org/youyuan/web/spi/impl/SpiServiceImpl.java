package org.youyuan.web.spi.impl;

import lombok.extern.slf4j.Slf4j;
import org.youyuan.web.spi.SpiService;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/4 20:07
 */
@Slf4j
public class SpiServiceImpl implements SpiService {
    @Override
    public void test() {
        log.info("spi test");
    }
}
