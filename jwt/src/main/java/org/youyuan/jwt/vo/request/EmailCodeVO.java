package org.youyuan.jwt.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.youyuan.jwt.utils.diyenum.EmailCodeType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Describe: 用户获取邮箱code码
 * @Author: youjiancheng
 * @date 2021/2/17 11:30
 */
@Data
public class EmailCodeVO {

    @NotEmpty(message = "账户名称不为空")
    @ApiModelProperty("账户名称")
    private String name;

    @Email(message = "邮箱格式错误")
    @ApiModelProperty("邮箱")
    private String email;

    @NotNull
    @ApiModelProperty("code码类型")
    private EmailCodeType emailCodeType;
}
