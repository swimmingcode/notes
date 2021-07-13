package com.cico.virmachine.domain.virmachine.entity.vo.apply;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Describe: 底下申请虚拟机实体类
 * @Author: youjiancheng
 * @date 2021/4/19 15:27
 */
@Data
@Builder
public class VirtualMachineApply {

    /**
     * 模板ID
     */
    private String id;

    /**
     * 新虚拟机名称
     */
    private String name;

    /**
     *  物理目标主机的ID
     */
    private String hostId;

    /**
     * 虚拟机备份的描述信息
     */
    private String description;


    private String  guestosLabel ;


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
