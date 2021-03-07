package org.youyuan.jwt.utils.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.youyuan.jwt.vo.response.Role;

import java.util.List;

/**
 * @Describe: 自定义Token，用于存储用户的信息
 * @Author: youjiancheng
 * @Date: 2021/1/30 21:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Token {

    private  Integer id;

    private String name;

    private List<Role> roles;

}
