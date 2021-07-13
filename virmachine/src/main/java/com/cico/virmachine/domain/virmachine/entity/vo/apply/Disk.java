package com.cico.virmachine.domain.virmachine.entity.vo.apply;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/27 10:14
 */
@Data
@Builder
public class Disk {
    private List<Volume> volume;
}
