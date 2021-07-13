package com.cico.virmachine.domain.virmachine.entity.dto;

import com.cico.virmachine.domain.virmachine.entity.vo.MachineSize;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Describe: 虚拟机配置种类
 * @Author: youjiancheng
 * @date 2021/4/16 16:39
 */
@Data
@Builder
public class VirtualMachineTypesDTO {

     @ApiModelProperty(value = "虚拟机类型（Windows/Linux）")
     private String machineType;

     @ApiModelProperty(value = " 虚拟机大小（低/中/高）")
     private String machineSize;

     @ApiModelProperty(value = "cpu核数 (1核、2核)")
     private String cpuCores;

     @ApiModelProperty(value = "cpu内存（1G、2G）")
     private String cpuMemory;


     @ApiModelProperty(value = "硬盘大小（20G、40G）")
     private String disk;

     @ApiModelProperty(value = "带宽（10M、20M）")
     private String bandwidth;

}
