package org.youyuan.mybatisplus.domain.user.po;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youyuan.mybatisplus.domain.user.vo.Status;

import java.util.Date;

/**
 * @Author youjiancheng
 * @Date 2021/1/21 17:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class UserPO {
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 映射非主键字段，value 映射字段名
     * */
    @TableField(value = "name",select = false)
    private String title;

    private String name;
    private Integer age;

    @TableField(exist = false)
    private String gender;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    private Integer version;

    private Status status;

//    @TableLogic(value = "1",delval = "2")
    private Integer deleted;
}
