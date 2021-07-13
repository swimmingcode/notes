package com.cico.virmachine.infrastructure.virtualMachine.ics;

import com.cico.virmachine.domain.virmachine.entity.vo.apply.VirtualMachineApply;
import com.cico.virmachine.domain.virmachine.entity.vo.apply.VirtualMachineTemplateInformation;


/**
 * @Describe: 浪潮Api接口
 * @Author: youjiancheng
 * @date 2021/4/23 9:17
 */
public interface IcsApi {
    /**
     *  创建虚拟机
     *
     * @param url url地址
     * @param virtualMachineApply body参数
     * @param aClass 响应类型
     * @return
     */
    <T> T createVirtualMachine(String url, VirtualMachineApply virtualMachineApply, Class<T> aClass);

    /**
     *删除虚拟机
     *
     * @param url  url地址
     */
    void deleteVirtualMachine(String url);

    /**
     * 创建虚拟机模板
     *
     * @param url  url地址
     * @param tClass 响应类型
     * @param <T>
     * @return
     */
    <T> T createVirtualMachineTemplate(String url,Class<T> tClass);


    /**
     * 任务状态查询
     *
     * @param url url地址
     * @param tClass 响应类型
     * @param <T>
     * @return
     */
    <T> T taskStateInfo(String url,Class<T> tClass);

    /**
     * 获取虚拟机信息
     * <p>
     *     虚拟机IP、以及用户名/密码
     * </p>
     *
     * @param url url地址
     * @param tClass 响应类型
     * @param <T>
     * @return
     */
    <T> T getVirtualMachineInformation(String url,Class<T> tClass);


    /**
     * 获取虚拟机模板详情
     *
     * @param <T>
     * @param url url地址
     * @param t  返回class类型
     * @return
     */
    <T> T getVirtualMachineTemplateInformation(String url, Class<T> t);
}
