package org.youyuan.jwt.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

/**
 * @Describe: 用户信息实体类
 * @Author: youjiancheng
 * @date 2021/2/22 19:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer uid;
    private String userName;
    private String email;
    private List<Role> rolePOS;
}
