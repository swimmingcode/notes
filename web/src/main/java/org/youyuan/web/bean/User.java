package org.youyuan.web.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe
 * @Author youjiancheng
 * @Date 2021/1/25 17:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Person{
    private String name;
    private Integer age;
}
