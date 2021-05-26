package com.cico.virmachine.domain.virmachine.entity.dto;

import com.cico.virmachine.domain.virmachine.entity.vo.MachineSize;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/20 11:37
 */
@Data
@Builder
public class AddVirtualMachineTemplateDTO {

    @ApiModelProperty(value = "虚拟机模板名称")
    private String templateName;

    @ApiModelProperty(value = "虚拟机模板描述")
    private String templateDesc;

    @ApiModelProperty(value = "虚拟机类型（Windows/Linux）")
    private MachineType machineType;


    @ApiModelProperty(value = "虚拟机大小（低/中/高）")
    private MachineSize machineSize;


    @ApiModelProperty(value = "cpu核数 (1核、2核)")
    private String cpuCores;


    @ApiModelProperty(value = "cpu内存（1G、2G）")
    private String cpuMemory;

    @ApiModelProperty(value = " 硬盘大小（20G、40G）")
    private String disk;

}
