package org.youyuan.whitelist.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/17 14:21
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "whitelist")
//@Component
public class WhiteListProperties {

    private String user;

}
