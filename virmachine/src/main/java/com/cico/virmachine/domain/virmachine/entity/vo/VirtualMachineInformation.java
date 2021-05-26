package com.cico.virmachine.domain.virmachine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe: 虚拟机信息
 * @Author: youjiancheng
 * @date 2021/4/21 16:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirtualMachineInformation {
    private String hostIp;
    private Cdrom cdrom;
}
