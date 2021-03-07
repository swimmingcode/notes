package org.youyuan.jwt.vo.response;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import org.youyuan.jwt.utils.diyenum.Professional;

import java.util.Date;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/7 17:16
 */
@Data
@Builder
public class TextBookExcel {

    @ExcelProperty(value = "教材名称", index = 0)
    private String textName;


    @ExcelProperty(value = "教材专业", index = 1)
    private String professional;


    @ExcelProperty(value = "教材库存", index = 2)
    private Integer bookNumber;


    @ExcelProperty(value = "教材信息描述", index = 3)
    private String bookDescribe;

    @ExcelProperty(value = "创建时间", index = 4)
    @DateTimeFormat(value = "YYYY-MM-dd HH:MM:SS")
    private Date createTime;


    @ExcelProperty(value = "更新时间", index = 5)
    @DateTimeFormat(value = "YYYY-MM-dd HH:MM:SS")
    private Date updateTime;
}
