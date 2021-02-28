package org.youyuan.jwt.vo.response;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.youyuan.jwt.utils.diyenum.Professional;

import java.util.Date;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/28 18:44
 */
@Data
public class TextBookVO {


    @ApiModelProperty(value = "ID")
    private Integer id;


    @ApiModelProperty(value = "教材名称")
    private String textName;

    @ApiModelProperty(value = " 教材图片url")
    private String imageUrl;


    @ApiModelProperty(value = "教材专业")
    private Professional professional;

    @ApiModelProperty(value = "教材库存")
    private Integer bookNumber;


    @ApiModelProperty(value = "教材信息描述")
    private String bookDescribe;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
