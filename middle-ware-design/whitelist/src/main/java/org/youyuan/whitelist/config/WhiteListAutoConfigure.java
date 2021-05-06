package org.youyuan.whitelist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/17 14:30
 */
@Configuration
@ConditionalOnClass(WhiteListProperties.class)
//使@ConfigurationProperties注解生效，如果只配置@ConfigurationProperties注解，在IOC容器中是获取不到properties配置文件转化的bean的
@EnableConfigurationProperties(value = WhiteListProperties.class)
public class WhiteListAutoConfigure {

    //@ConditionalOnClass(WhiteListProperties.class)，当 WhiteListProperties 位于当前类路径上，才会实例化一个类。除此之外还有其他属于此系列的常用的注解。
    //@ConditionalOnBean 仅仅在当前上下文中存在某个对象时，才会实例化一个 Bean
    //@ConditionalOnClass 某个 CLASS 位于类路径上，才会实例化一个 Bean
    //@ConditionalOnExpression 当表达式为 true 的时候，才会实例化一个 Bean
    //@ConditionalOnMissingBean 仅仅在当前上下文中不存在某个对象时，才会实例化一个 Bean
    //@ConditionalOnMissingClass 某个 CLASS 类路径上不存在的时候，才会实例化一个 Bean

    @Bean("whiteListConfig")
    @ConditionalOnMissingBean
    public String whiteListConfig(@Autowired WhiteListProperties properties) {
        return properties.getUser();
    }
}
