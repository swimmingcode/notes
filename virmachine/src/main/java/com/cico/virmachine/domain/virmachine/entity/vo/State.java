package com.cico.virmachine.domain.virmachine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Describe: 虚拟机/虚拟机模板 状态
 * @Author: youjiancheng
 * @date 2021/4/16 14:39
 */
@AllArgsConstructor
public enum  State {

    APPLICATION("申请中"),
    USE("可使用"),
    FAIL("创建失败");


    @Getter
    private String name;
}
