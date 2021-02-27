package org.youyuan.jwt.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.youyuan.jwt.utils.diyenum.Professional;

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
    @TableId
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
    private String describe;

}
