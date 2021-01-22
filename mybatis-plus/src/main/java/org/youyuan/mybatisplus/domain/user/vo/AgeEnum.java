package org.youyuan.mybatisplus.domain.user.vo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;

/**
 * @Author youjiancheng
 * @Date 2021/1/21 22:54
 */
@AllArgsConstructor
public enum  AgeEnum implements IEnum<Integer> {

    ONE(1,"一岁"),
    TWO(2,"两岁"),
    THREE(3,"三岁");

    @EnumValue
    private Integer code;
    private String msg;

    @Override
    public Integer getValue() {
        return null;
    }
}
