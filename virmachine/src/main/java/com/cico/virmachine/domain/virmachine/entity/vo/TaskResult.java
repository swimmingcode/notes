package com.cico.virmachine.domain.virmachine.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/21 14:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResult {
    /**
     * 任务计划id
     */
    private String id;

    /**
     * 任务计划名称
     */
    private String name;

    /**
     * 任务计划描述
     */
    private String description;


    /**
     * 任务计划类型:ONCE表示一次任务，HOURLY表示按小时任务，DAILY表示按天任务
     */
    private String type;


    /**
     * 任务计划最近一次的执行结果
     */
    private String state;
}
