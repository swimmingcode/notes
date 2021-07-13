package com.cico.virmachine.domain.virmachine.entity.dto;

import com.cico.virmachine.domain.virmachine.entity.vo.MachineSize;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Describe: 创建虚拟机DTO
 * @Author: youjiancheng
 * @date 2021/4/19 10:01
 */
@Data
@Builder
public class CreateMachineDTO {

    @NotNull(message = "虚拟机类型不为空")
    @ApiModelProperty(value = "虚拟机类型（Windows/Linux）")
    private MachineType machineType;

    @NotNull(message = "虚拟机大小不为空")
    @ApiModelProperty(value = " 虚拟机大小（低/中/高）")
    private MachineSize machineSize;

    @ApiModelProperty(value = "虚拟机名称")
    @Pattern(regexp = "[A-Za-z0-9\\u4e00-\\u9fa5]{2,20}", message = "请输入数字、字母、或中文（要求2个及两个以上）")
    private String machineName;

    @NotNull(message = "是否通知不为空")
    @ApiModelProperty(value = "是否通知（true：通知/false: 不通知）")
    private Boolean isNotice;

    @NotBlank(message = "cpu核数不为空")
    @ApiModelProperty(value = "cpu核数 (1核、2核)")
    private String cpuCores;

    @NotBlank(message = "cpu内存不为空")
    @ApiModelProperty(value = "cpu内存（1G、2G）")
    private String cpuMemory;

    @NotBlank(message = "硬盘大小不为空")
    @ApiModelProperty(value = "硬盘大小（20G、40G）")
    private String disk;

    @NotBlank(message = "带宽不为空")
    @ApiModelProperty(value = "带宽（10M、20M）")
    private String bandwidth;

    @ApiModelProperty(value = "描述信息（可选）")
    private String description;

}
