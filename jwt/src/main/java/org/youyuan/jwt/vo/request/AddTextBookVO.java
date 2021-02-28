package org.youyuan.jwt.vo.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.youyuan.jwt.utils.diyenum.Professional;

/**
 * @Describe: 添加教材实体类
 * @Author: youjiancheng
 * @date 2021/2/28 18:06
 */
@Data
public class AddTextBookVO {

    @ApiModelProperty(value = "教材名称")
    private String textName;


    @ApiModelProperty(value = "教材图片url")
    private String imageUrl;

    @ApiModelProperty(value = "教材专业")
    private Professional professional;


    @ApiModelProperty(value = "教材库存")
    private Integer bookNumber;


    @ApiModelProperty(value = "教材信息描述")
    private String bookDescribe;

}
