package org.youyuan.jwt.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youyuan.jwt.utils.diyenum.ApproveStatus;
import org.youyuan.jwt.utils.diyenum.RecordType;

import java.util.Date;

/**
 * @Describe: 记录实体类
 * @Author: youjiancheng
 * @date 2021/3/6 14:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "record")
public class RecordPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 申请人ID
     */
    private Integer userId;

    /**
     * 申请人
     */
    private String userName;

    /**
     * 教材ID
     */
    private Integer textId;

    /**
     * 教材名称
     */
    private String textBookName;

    /**
     *教材数量
     */
    private Integer textBookSize;

    /**
     * 申请时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date approveDate;

    @EnumValue
    private RecordType recordType;

    /**
     * 审批状态
     */
    @EnumValue
    private ApproveStatus approveStatus;

}
