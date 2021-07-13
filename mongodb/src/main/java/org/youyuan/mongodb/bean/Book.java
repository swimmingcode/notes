package org.youyuan.mongodb.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/8 22:04
 */
@Data
@Builder
public class Book {
    private Integer id;
    private String name;
    private String author;
}
