package org.youyuan.web.assemble;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.youyuan.web.assemble.model.Yellow;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/4 18:52
 */
@Configuration
public class ColorRegistrarConfiguration {

    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

}
