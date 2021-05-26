package com.cico.virmachine.domain.virmachine.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cico.virmachine.domain.virmachine.entity.vo.BackupType;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineSize;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineType;
import com.cico.virmachine.domain.virmachine.entity.vo.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Describe: 虚拟机模板实体类
 * @Author: youjiancheng
 * @date 2021/4/20 10:33
 */
@Data
@Builder
@TableName(value = "virtual_machine_template")
@AllArgsConstructor
@NoArgsConstructor
public class VirtualMachineTemplatePO {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;


    /**
     * 虚拟机类型（Windows/Linux）
     */
    private MachineType machineType;

    /**
     * 虚拟机大小（低/中/高）
     */
    private MachineSize machineSize;

    /**
     * cpu核数 (1核、2核)
     */
    private String cpuCores;

    /**
     * cpu内存（1G、2G）
     */
    private String cpuMemory;

    /**
     * 硬盘大小（20G、40G）
     */
    private String disk;


    /**
     * 带宽（10M、20M）
     */
    private String bandwidth;

    /**
     * 备份限速，默认20M/s
     */
//    private String rateLimit;


    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 虚拟机模板描述
     */
    private String templateDesc;

    /**
     * 虚拟机模板状态（申请中/可使用/创建失败）
     */
    private State state;

    /**
     * 指定要备份的虚拟机id
     */
    private String vmId;

    /**
     *虚拟机备份的描述信息，长度不能超过255个字符
     */
    private String description;

    /**
     * 虚拟机备份的类型，"TOTAL"为全量备份，"INCREMENT"为增量备份，二者必须选其一
     */
//    private BackupType backupType;

    /**
     * 虚拟机备份时指定的目标存储池
     */
//    @TableField(value = "datastore_id")
//    private String datastoreID;

    /**
     * 任务ID
     */
    private String taskId;

}
