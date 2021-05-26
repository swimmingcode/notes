package com.cico.virmachine.domain.virmachine.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.cico.virmachine.domain.virmachine.entity.vo.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/20 9:22
 */
@Data
@Builder
public class VirtualMachineListDTO {

    @ApiModelProperty(value = "虚拟机名称")
    private String machineName;

    @ApiModelProperty(value = "虚拟机IP")
    private String ip;

    @ApiModelProperty(value = "机器类型（windows-level-1，linux-level-3）")
    private String machineLevel;

    @ApiModelProperty(value = "虚拟机状态（申请中/可使用）")
    private State state;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

}
