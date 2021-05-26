package com.cico.virmachine.domain.virmachine.entity.vo.apply;

import lombok.Builder;
import lombok.Data;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/27 10:16
 */
@Data
@Builder
public class Nics {
    private String deviceId;
    private String deviceType;
    private String networkId;
}
