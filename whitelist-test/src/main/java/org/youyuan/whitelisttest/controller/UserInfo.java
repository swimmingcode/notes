package org.youyuan.whitelisttest.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/18 15:46
 */
@Data
@AllArgsConstructor
public class UserInfo {
    private String name;
    private Integer id;
    private String address;
}
