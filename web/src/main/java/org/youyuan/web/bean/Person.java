package org.youyuan.web.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe
 * @Author youjiancheng
 * @Date 2021/1/25 18:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    Integer id;
    private String job;
}
