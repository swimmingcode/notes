package com.cico.virmachine.infrastructure.timeTask;

import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachineTemplatePO;
import com.cico.virmachine.domain.virmachine.entity.vo.State;
import com.cico.virmachine.domain.virmachine.entity.vo.TaskResult;
import com.cico.virmachine.domain.virmachine.entity.vo.VirtualMachineInformation;
import com.cico.virmachine.domain.virmachine.service.VirtualMachineDomainService;
import com.cico.virmachine.domain.virmachine.service.VirtualMachineTemplateDomainService;
import com.cico.virmachine.infrastructure.utils.HttpClientUtils;
import com.cico.virmachine.infrastructure.virtualMachine.ics.IcsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static com.cico.virmachine.infrastructure.common.RedisKey.VIRTUAL_MACHINE_TASK_ID;
import static com.cico.virmachine.infrastructure.common.RedisKey.VIRTUAL_MACHINE_TEMPLATE_TASK_ID;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/21 14:27
 */
@Slf4j
@Component
public class VirtualMachineApplySchedule {

    /**
     * 完成状态
     */
    private static final String VIRTUAL_MACHINE_STATE = "FINISHED";

    /**
     * 错误状态
     */
    private static final String VIRTUAL_MACHINE_ERROR = "ERROR";

    @Value(value = "${ics.http.ip}")
    private String baseUrl;

    @Autowired
    private IcsApi icsApi;

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private VirtualMachineDomainService virtualMachineDomainService;

    @Autowired
    private VirtualMachineTemplateDomainService virtualMachineTemplateDomainService;

    /**
     * 1、断虚拟机是否已经创建完成
     * 2、获取虚拟机IP
     * 3、修改虚拟机IP
     * 4、修改用户名/密码
     */
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = " 0 0/1 * * * *")
    public void applyMachineCron() {
        List<Object> taskIds = redisTemplate.opsForList().range(VIRTUAL_MACHINE_TASK_ID , 0, -1);
        if (taskIds != null && taskIds.size() >= 1){
            for (Object taskId : taskIds) {
                String task = taskId.toString();
                //查询其状态
                TaskResult body = icsApi.taskStateInfo(baseUrl + "/tasks/" + task, TaskResult.class);
                log.debug("{}",body);
                //完成状态
                if (body.getState().toUpperCase().equals(VIRTUAL_MACHINE_STATE)) {
                    //虚拟机状态设置为可使用
                    VirtualMachinePO machinePO = virtualMachineDomainService.selectByTaskId(task).get(0);
                    machinePO.setState(State.USE);
                    // todo 获取虚拟机IP、以及用户名/密码
                    VirtualMachineInformation res = icsApi.<VirtualMachineInformation>getVirtualMachineInformation(baseUrl + "/vms/" + machinePO.getIcsId(), VirtualMachineInformation.class);
                    machinePO.setIp(res.getHostIp());
                    machinePO.setMachineUserName(res.getCdrom().getCifsDto().getUserName());
                    machinePO.setMachinePassword(res.getCdrom().getCifsDto().getPassword());
                    virtualMachineDomainService.update(machinePO);

                    // todo 修改虚拟机IP

                    // todo 用户名/密码

                    //删除任务
                    redisTemplate.opsForList().remove(VIRTUAL_MACHINE_TASK_ID,-1,task);
                } else if (body.getState().toUpperCase().equals(VIRTUAL_MACHINE_ERROR)){
                    //错误状态
                    VirtualMachinePO machinePO = virtualMachineDomainService.selectByTaskId(task).get(0);
                    machinePO.setState(State.FAIL);
                    virtualMachineDomainService.update(machinePO);
                    //删除任务
                    redisTemplate.opsForList().remove(VIRTUAL_MACHINE_TASK_ID,-1,task);
                }

            }
        }
    }

    @Scheduled(cron = "5 0/1 * * * *")
    public void applyMachineTemplateCron() {
        List<Object> taskIds = redisTemplate.opsForList().range(VIRTUAL_MACHINE_TEMPLATE_TASK_ID ,0, -1);
        if (taskIds != null && taskIds.size() >= 1) {
            for (Object taskId : taskIds) {
                String task = taskId.toString();
                //查询其状态
                TaskResult body = icsApi.taskStateInfo(baseUrl + "/schedulers/" + task, TaskResult.class);
                log.debug("{}",body);
                if (body.getState().equals(VIRTUAL_MACHINE_STATE)) {
                    //将虚拟机模板状态设置成可使用
                    VirtualMachineTemplatePO machineTemplatePO = virtualMachineTemplateDomainService.selectByMachineTemplateTaskId(task).get(0);
                    machineTemplatePO.setState(State.USE);
                    virtualMachineTemplateDomainService.update(machineTemplatePO);
                    //删除任务
                    redisTemplate.opsForList().remove(VIRTUAL_MACHINE_TEMPLATE_TASK_ID,-1,task);
                }
            }
        }
    }

}
