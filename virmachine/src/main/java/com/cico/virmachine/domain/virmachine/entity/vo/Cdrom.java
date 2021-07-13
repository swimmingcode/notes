package com.cico.virmachine.domain.virmachine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe: cdrom设备
 * @Author: youjiancheng
 * @date 2021/4/21 16:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cdrom {
    private CifsDto cifsDto;
}
