package com.cico.virmachine.infrastructure.virtualMachine.ics.impl;

import com.cico.virmachine.domain.virmachine.entity.vo.apply.VirtualMachineApply;
import com.cico.virmachine.domain.virmachine.entity.vo.apply.VirtualMachineTemplateInformation;
import com.cico.virmachine.infrastructure.common.exception.ExceptionFactory;
import com.cico.virmachine.infrastructure.common.response.ResponseCode;
import com.cico.virmachine.infrastructure.utils.HttpClientUtils;
import com.cico.virmachine.infrastructure.virtualMachine.ics.IcsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;


/**
 * @Describe: 浪潮Api接口
 * @Author: youjiancheng
 * @date 2021/4/23 16:31
 */
@Slf4j
@Service
public class IcsApiImpl implements IcsApi {

    private static final String CREATE_MACHINE_URL = "/vms?action=createByTemplate";

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Override
    public <T> T createVirtualMachine(String url, VirtualMachineApply virtualMachineApply, Class<T> aClass) {
        log.info("{}","开始调用创建虚拟机Api");
        T body = null;
        try {
            ResponseEntity<T> post = httpClientUtils.<T>post(url, virtualMachineApply,aClass);
            body = post.getBody();
        } catch (RestClientException e) {
            log.info("{}","创建虚拟机Api调用失败");
            throw new ExceptionFactory(ResponseCode.CREATE_VIRTUAL_MACHINE_FAIL);
        }
        return body;
    }

    @Override
    public void deleteVirtualMachine(String url) {
        log.info("{}","开始调用删除虚拟机Api");
        try {
            httpClientUtils.delete(url);
        } catch (RestClientException e) {
            log.info("{}","调用删除虚拟机Api失败");
            throw new ExceptionFactory(ResponseCode.DELETE_VIRTUAL_MACHINE_FAIL);
        }
    }

    @Override
    public <T> T createVirtualMachineTemplate(String url, Class<T> tClass) {
        log.info("{}","开始调用创建虚拟机模板Api");
        T body = null;
        try {
            ResponseEntity<T> post = httpClientUtils.<T>post(url, tClass);
            body = post.getBody();
        } catch (RestClientException e) {
            log.info("{}","调用创建虚拟机模板Api失败");
            throw new ExceptionFactory(ResponseCode.CREATE_VIRTUAL_MACHINE_TEMPLATE_FAIL);
        }
        return body;
    }

    @Override
    public <T> T taskStateInfo(String url, Class<T> tClass) {
        log.info("{}","开始调用查询任务状态Api");
        T body = null;
        try {
            ResponseEntity<T> post = httpClientUtils.<T>get(url,tClass);
            body = post.getBody();
        } catch (RestClientException e) {
            log.info("{}","调用查询任务状态Api失败");
            throw new ExceptionFactory(ResponseCode.QUERY_TASK_INFORMATION_FAIL);
        }
        return body;
    }

    @Override
    public <T> T getVirtualMachineInformation(String url, Class<T> tClass) {
        log.info("{}","开始调用获取虚拟机信息Api");
        T body = null;
        try {
            ResponseEntity<T> post = httpClientUtils.<T>get(url,tClass);
            body = post.getBody();
        } catch (RestClientException e) {
            log.info("{}","调用获取虚拟机信息Api失败");
            throw new ExceptionFactory(ResponseCode.QUERY_VIRTUAL_MACHINE_MESSAGE_FAIL);
        }
        return body;
    }

    @Override
    public <T> T  getVirtualMachineTemplateInformation(String url, Class<T> t) {
        log.info("{}","开始调用获取虚拟机模板信息Api");
        T body = null;
        try {
            ResponseEntity<T> post = httpClientUtils.<T>get(url, (Class<T>) t.getClass());
            body = post.getBody();
        } catch (RestClientException e) {
            log.info("{}","调用获取虚拟机信息Api失败");
            throw new ExceptionFactory(ResponseCode.QUERY_VIRTUAL_MACHINE_MESSAGE_FAIL);
        }
        return body;
    }


}
