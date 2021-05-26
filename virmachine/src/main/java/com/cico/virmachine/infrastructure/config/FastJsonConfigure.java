package com.cico.virmachine.infrastructure.config;


import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Describe: 重写fastJson HttpMessageConverter Bean
 * @Author: youjiancheng
 * @date 2021/4/16 16:20
 */
@Configuration
public class FastJsonConfigure {
//
//    @Bean
//    FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig config = new FastJsonConfig();
//        //配置日期格式
//        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        //其他配置
//        converter.setFastJsonConfig(config);
//        return converter;
//    }


}
