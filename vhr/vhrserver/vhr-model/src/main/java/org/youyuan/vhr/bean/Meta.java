package org.youyuan.vhr.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 额外的属性
 */
@Data
public class Meta implements Serializable {
    private Boolean keepalive;
    //是否需要认证
    private Boolean requireauth;
}
