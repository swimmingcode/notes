package com.cico.virmachine.domain.virmachine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe: 挂载CIFS类型镜像时，填充此对象
 * @Author: youjiancheng
 * @date 2021/4/21 16:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CifsDto {
    private String  userName;
    private String  password;
}
