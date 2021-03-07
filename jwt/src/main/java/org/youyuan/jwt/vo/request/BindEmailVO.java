package org.youyuan.jwt.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @Describe:用户绑定邮箱
 * @Author: youjiancheng
 * @date 2021/2/17 14:42
 */
@Data
public class BindEmailVO {
    @NotEmpty(message = "账户名称不为空")
    @ApiModelProperty("账户名称")
    private String name;

    @Email(message = "邮箱格式错误")
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty(value = "code码")
    private Integer code;

}
