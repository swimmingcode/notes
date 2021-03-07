package org.youyuan.jwt.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;

}
