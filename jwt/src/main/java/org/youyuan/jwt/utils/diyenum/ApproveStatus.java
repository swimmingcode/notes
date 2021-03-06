package org.youyuan.jwt.utils.diyenum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/6 15:08
 */
@AllArgsConstructor
public enum ApproveStatus {

    APPROVAL("审批中"),
    AGREE("同意"),
    REJECT("拒接");

    @EnumValue
    @Setter
    @Getter
    String message;

}
