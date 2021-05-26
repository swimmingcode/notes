package com.cico.virmachine.infrastructure.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Describe: Http响应码
 * @Author: youjiancheng
 * @date 2021/4/16 16:08
 */
@AllArgsConstructor
public enum ResponseCode {

    FAIL(-1,"失败"),
    OK(200,"成功"),

    PARAMETER_EXCEPTION(3000,"传入参数格式错误"),
    NULL_POINTER_EXCEPTION(4000,"空指针错误"),

    NOT_EXISTS_VIRTUAL_MACHINE(5000,"无此虚拟机"),
    NOT_EXISTS_VIRTUAL_MACHINE_TEMPLATE(5001,"无此虚拟机模板"),
    CREATE_VIRTUAL_MACHINE_FAIL(5002,"创建虚拟机失败"),
    DELETE_VIRTUAL_MACHINE_FAIL(5003,"删除虚拟机失败"),
    CREATE_VIRTUAL_MACHINE_TEMPLATE_FAIL(5004,"创建虚拟机模板失败"),
    QUERY_TASK_INFORMATION_FAIL(5005,"查询任务状态失败"),
    QUERY_VIRTUAL_MACHINE_MESSAGE_FAIL(5006,"");


    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

}
