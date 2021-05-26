package com.cico.virmachine.domain.virmachine.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.cico.virmachine.domain.virmachine.entity.vo.BackupType;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineSize;
import com.cico.virmachine.domain.virmachine.entity.vo.MachineType;
import com.cico.virmachine.domain.virmachine.entity.vo.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @Describe: 虚拟机实体类
 * @Author: youjiancheng
 * @date 2021/4/16 14:31
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "virtual_machine")
@Data
public class VirtualMachinePO {

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
     *
     * 虚拟机名称
     */
    private String machineName;

    /**
     * 是否通知（true：通知/false: 不通知）
     */
    private Boolean isNotice;

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
     * 虚拟机IP
     */
    private String ip;

    /**
     * 机器类型（windows-level-1，linux-level-3）
     */
    private String machineLevel;


    /**
     * 虚拟机状态（申请中/可使用/创建失败）
     */
    private State state;

    /**
     * 申请时间
     */
    @TableField(value = "apply_time")
    private Date applyTime;

    /**
     * 下层虚拟机备份id
     */
    private String icsId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 虚拟机用户名
     */
    private String machineUserName;

    /**
     * 虚拟机密码
     */
    private String machinePassword;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE,value = "update_time")
    private Date updateTime;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 备份限速，默认20M/s
     */
    private String rateLimit;

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
    private BackupType backupType;

    /**
     * 虚拟机备份时指定的目标存储池
     */
    @TableField(value = "datastore_id")
    private String datastoreID;

    /**
     * 逻辑删除（0代表未删除，1代表已删除）
     */
    @TableLogic(value = "0",delval = "1")
    @TableField(select = false)
    private Integer deleted;
}
