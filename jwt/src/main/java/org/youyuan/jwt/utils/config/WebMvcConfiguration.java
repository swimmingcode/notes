package org.youyuan.jwt.utils.config;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.youyuan.jwt.utils.interceptor.JwtInterceptor;
import org.youyuan.jwt.utils.interceptor.PermissionInterceptor;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/1 14:56
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Autowired
    PermissionInterceptor permissionInterceptor;

    @Autowired
    WebMethodArgumentResolver webMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).excludePathPatterns("/img/**");
        registry.addInterceptor(permissionInterceptor).excludePathPatterns("/img/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(webMethodArgumentResolver);
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
    }



//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //解决swagger页面打不开
//        //发现如果继承了WebMvcConfigurationSupport，则在properties中配置的相关内容会失效。需要重新指定静态资源
////        registry.addResourceHandler("/swagger-ui.html")
////                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
////        registry.addResourceHandler("/webjars/**")
////                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//        //Webapp下静态资源文件无法访问的问题
//        // TODO: 2021/2/28 无法找到路径
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/public/");
//
//        //定义静态资源过滤策
////        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }






}
