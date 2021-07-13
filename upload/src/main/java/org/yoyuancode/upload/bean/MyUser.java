package org.yoyuancode.upload.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/10 19:52
 */
@Data
@Component
@PropertySource(value = {"classpath:person.properties"})
@ConfigurationProperties(prefix = "person1")
public class MyUser {
    private String name;
    private String address;
}
