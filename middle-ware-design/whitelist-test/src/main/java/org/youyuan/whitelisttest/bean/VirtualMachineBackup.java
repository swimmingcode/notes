package org.youyuan.whitelisttest.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/19 15:55
 */
@Data
@Builder
public class VirtualMachineBackup {

    /**
     * 【路径参数，非必须】备份限速，默认20M/s
     */
    private String rateLimit;

    /**
     * 【非必须参数】：虚拟机备份id
     */
    private String id;

    /**
     * 【必须参数】：指定要备份的虚拟机id
     */
    private String vmId;

    /**
     * 【必须参数】：虚拟机备份的名称，长度不能超过80个字符，且只能包含数字、字母、汉字以及下划线
     */
    private String name;

    /**
     * 【非必须参数】：虚拟机备份的描述信息，长度不能超过255个字符
     */
    private String description;

    /**
     * 【必须参数】：虚拟机备份的类型，"TOTAL"为全量备份，"INCREMENT"为增量备份，二者必须选其一
     */
    private String backupType;

    /**
     * 【必须参数】：虚拟机备份时指定的目标存储池
     */
    private String datastoreID;
}

