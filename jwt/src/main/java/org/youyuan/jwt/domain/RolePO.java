package org.youyuan.jwt.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe:角色类
 * @Author: youjiancheng
 * @Date: 2021/1/30 21:19
 */
@Data
@AllArgsConstructor
@Builder
@TableName("role")
public class RolePO {

    /**
     * ID
     */
    @TableId
    private Integer id;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;



}
