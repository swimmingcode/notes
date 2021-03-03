package org.youyuan.jwt.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/26 18:25
 */
@Data
@Builder
@ApiModel(value = "用户列表")
public class UserVO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = " 创建时间")
    private Date createTime;

    @ApiModelProperty(value = "邮箱")
    private String email;

}
