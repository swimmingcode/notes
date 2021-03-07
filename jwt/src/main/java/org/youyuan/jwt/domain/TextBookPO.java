package org.youyuan.jwt.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youyuan.jwt.utils.diyenum.Professional;

import java.util.Date;

/**
 * @Describe: 教材类
 * @Author: youjiancheng
 * @date 2021/2/27 15:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "text_book")
public class TextBookPO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 教材名称
     */
    @TableField(value = "text_name")
    private String textName;

    /**
     * 教材图片url
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 教材专业
     */
    @EnumValue
    private Professional professional;

    /**
     * 教材库存
     */
    @TableField(value = "book_number")
    private Integer bookNumber;

    /**
     * 教材信息描述
     */
    private String bookDescribe;

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
     * 逻辑删除
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;

}
