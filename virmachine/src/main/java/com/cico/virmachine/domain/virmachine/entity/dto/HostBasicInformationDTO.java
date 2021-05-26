package com.cico.virmachine.domain.virmachine.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Describe: 主机基础信息
 * @Author: youjiancheng
 * @date 2021/4/21 10:18
 */
@Data
@Builder
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class HostBasicInformationDTO {
    @ApiModelProperty(value = "主机名")
    private String machineName;
    @ApiModelProperty(value = "ip")
    private String ip;
    @ApiModelProperty(value = "操作系统")
    private String operatingSystem;
    @ApiModelProperty(value = "版本号")
    private String version;
    @ApiModelProperty(value = "运行时长")
    private Integer runningTime;
    @ApiModelProperty(value = "软件总数")
    private Integer softwareTotal;
    @ApiModelProperty(value = "用户数")
    private Integer userNumber;
    @ApiModelProperty(value = "CPU核数")
    private String cpuCores;
    @ApiModelProperty(value = "CPU品牌")
    private String cpuBrand;
}
