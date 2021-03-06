package org.youyuan.jwt.utils.diyenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/6 15:02
 */
@AllArgsConstructor
public enum  RecordType {

    SCHEDULED("预定"),
    WITHDRAW("退回");

    @Setter
    @Getter
    @EnumValue
    String message;

}
