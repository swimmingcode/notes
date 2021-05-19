package org.youyuan.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/19 11:35
 */
@ConfigurationProperties(prefix = "youyuan.code")
public class CodeProperties {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
