package com.cico.virmachine.domain.virmachine.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineSize;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/20 10:58
 */
@Data
@Builder
public class VirtualMachineTemplateDTO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "虚拟机类型（windows/linux）")
    private String machineType;

    @ApiModelProperty(value = "虚拟机大小（低/中/高）")
    private String machineSize;

    @ApiModelProperty(value = "cpu核数 (1核、2核)")
    private String cpuCores;


    @ApiModelProperty(value = "cpu内存（1G、2G）")
    private String cpuMemory;

    @ApiModelProperty(value = "硬盘大小（20G、40G）")
    private String disk;
}
