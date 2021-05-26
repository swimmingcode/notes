package com.cico.virmachine.domain.virmachine.entity.vo.apply;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/27 11:20
 */
@Data
@Builder
public class VirtualMachineTemplateInformation {

    /**
     * 虚拟机模板Id
     */
    private String id;

    /**
     *  虚拟机模板主机Id
     */
    private String hostId;

    /**
     * 操作系统标签
     */
    private String guestosLabel;

    /**
     * 操作系统类型
     */
    private String guestosType;

    /**
     * 磁盘
     */
    private List<Disk> disks;

    /**
     * 网卡
     */
    private List<Nics> nics;


}
