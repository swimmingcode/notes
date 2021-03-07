package org.youyuan.jwt.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 17:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户账号")
public class UserAccountVO {

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不为空")
    private String username;

    @NotEmpty(message = "密码不为空")
    @ApiModelProperty("密码")
    private String password;
}
