package com.cico.virmachine.infrastructure.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Describe: Mybatis-plus分页拦截器
 * @Author: youjiancheng
 * @date 2021/4/20 10:12
 */
@Configuration
public class MybatisPlusPageConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
