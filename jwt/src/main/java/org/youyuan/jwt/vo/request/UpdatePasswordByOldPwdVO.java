package org.youyuan.jwt.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/17 15:12
 */
@Data
public class UpdatePasswordByOldPwdVO {
    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("旧密码")
    private String oldPwd;

    @ApiModelProperty("新密码")
    @Size(min = 6,max = 18,message = "密码长度最小6位，最大18位")
    private String newPwd;
}
