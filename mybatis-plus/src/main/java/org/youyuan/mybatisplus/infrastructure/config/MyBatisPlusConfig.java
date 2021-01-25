package org.youyuan.mybatisplus.infrastructure.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Configuration;

/**
 * @Author youjiancheng
 * @Date 2021/1/21 22:44
 */
@Configuration
public class MyBatisPlusConfig {
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
