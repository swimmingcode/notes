package org.youyuan.jwt.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Describe: 修改账号名称
 * @Author: youjiancheng
 * @date 2021/2/17 10:22
 */
@Data
public class UpdateAccountNameVO {

    @NotEmpty
    @Pattern(regexp = "^YJ.*",message = "请以YJ字符串开口，位数不少与三位")
    @ApiModelProperty(value = "旧用户名称")
    private String usernameOld;

    @NotEmpty
    @Pattern(regexp = "^YJ.*",message = "请以YJ字符串开口，位数不少与三位")
    @ApiModelProperty(value = "新用户名称")
    private String usernameNew;
}
