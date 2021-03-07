package org.youyuan.jwt.utils.diyenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/27 15:17
 */
@AllArgsConstructor
public enum Professional {

    COMPUTER("计算机"),
    ENGLISH("英语"),
    CHEMISTRY("化学"),
    ELECTRONIC("电子");

    @Getter
    @Setter
    @EnumValue
    private String name;
}
