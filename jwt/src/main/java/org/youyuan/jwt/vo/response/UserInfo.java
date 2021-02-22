package org.youyuan.jwt.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youyuan.jwt.domain.RolePO;

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
    private List<RolePO> rolePOS;
}
