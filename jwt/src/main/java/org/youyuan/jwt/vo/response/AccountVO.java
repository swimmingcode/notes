package org.youyuan.jwt.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Describe: 账户信息（名称，密码）
 * @Author: youjiancheng
 * @date 2021/2/27 14:36
 */
@Data
@Builder
public class AccountVO {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("密码")
    private String pwd;
}
