package com.cico.virmachine.domain.virmachine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/16 15:05
 */
@AllArgsConstructor
public enum MachineSize {

    WINDOWS_LOW("windows低配版","windows-level-1"),
    WINDOWS_MEDIUM("windows中配版","windows-level-2"),
    WINDOWS_HIGH("windows高配版","windows-level-3"),
    WINDOWS_TOP("windows顶配版","windows-level-4"),

    LINUX_LOW("linux低配版","linux-level-1"),
    LINUX_MEDIUM("linux中配版","linux-level-2"),
    LINUX_HIGH("linux高配版","linux-level-3"),
    LINUX_TOP("linux顶配版","linux-level-4");


    @Getter
    private String name;

    @Getter
    @Setter
    private String level;

}
