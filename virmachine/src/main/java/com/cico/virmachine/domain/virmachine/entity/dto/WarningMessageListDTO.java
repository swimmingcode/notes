package com.cico.virmachine.domain.virmachine.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Describe: 预警消息列表
 * @Author: youjiancheng
 * @date 2021/4/21 10:38
 */
@Data
@Builder
public class WarningMessageListDTO {
    @ApiModelProperty(value = "预警项")
    private String warningItems;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "计数")
    private Integer number;
    @ApiModelProperty(value = "方法")
    private String method;
    @ApiModelProperty(value = "当前值")
    private String currentValue;
    @ApiModelProperty(value = "黄色阈值")
    private String yellowThreshold;
    @ApiModelProperty(value = "红色阈值")
    private String redThreshold;
    @ApiModelProperty(value = "单位")
    private String unit;

}
