package com.cico.virmachine.domain.virmachine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Describe: 虚拟机类型
 * @Author: youjiancheng
 * @date 2021/4/16 15:01
 */
@AllArgsConstructor
public enum MachineType {

    WINDOWS("Windows"),
    LINUX("Linux");

    @Getter
    private String name;
}
