package org.youyuan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/19 11:42
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(CodeProperties.class)
public class CodeeAutoConfiguration {


    @Bean
    public CodeService codeService(@Autowired CodeProperties codeProperties) {
        CodeService codeService = new CodeService(codeProperties);
        return codeService;
    }

}
