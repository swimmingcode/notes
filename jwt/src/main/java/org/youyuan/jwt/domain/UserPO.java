package org.youyuan.jwt.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe: 用户类
 * @Author: youjiancheng
 * @Date: 2021/1/30 21:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "user")
public class UserPO {

    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 密码
     */
    private String password;


}
