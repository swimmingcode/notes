package com.cico.virmachine.domain.virmachine.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/21 9:57
 */
@Data
@Builder
public class HostListDTO {
    @ApiModelProperty(value = "主机id")
    private Integer id;
    @ApiModelProperty(value = "主机ip")
    private String ip;
}
