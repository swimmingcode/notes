package org.youyuan.jwt.utils.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.youyuan.jwt.utils.interceptor.JwtInterceptor;
import org.youyuan.jwt.utils.interceptor.PermissionInterceptor;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/1 14:56
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Autowired
    PermissionInterceptor permissionInterceptor;

    @Autowired
    WebMethodArgumentResolver webMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor);
        registry.addInterceptor(permissionInterceptor);
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(webMethodArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);

    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //解决swagger页面打不开
        //发现如果继承了WebMvcConfigurationSupport，则在properties中配置的相关内容会失效。需要重新指定静态资源
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/**");
        super.addResourceHandlers(registry);
    }

}
