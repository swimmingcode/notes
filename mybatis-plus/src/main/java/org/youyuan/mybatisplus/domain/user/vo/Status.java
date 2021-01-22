package org.youyuan.mybatisplus.domain.user.vo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

/**
 * @Author youjiancheng
 * @Date 2021/1/21 22:48
 */
@AllArgsConstructor
public enum Status {
    WORK(1,"上班"),
    REST(0,"休息");

    @EnumValue
    private Integer code;
    private String msg;
}
