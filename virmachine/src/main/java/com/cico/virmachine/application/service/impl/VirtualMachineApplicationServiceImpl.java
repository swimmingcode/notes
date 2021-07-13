package com.cico.virmachine.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cico.virmachine.domain.virmachine.entity.dto.*;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachineTemplatePO;
import com.cico.virmachine.domain.virmachine.entity.vo.BackupResult;
import com.cico.virmachine.domain.virmachine.entity.vo.State;
import com.cico.virmachine.domain.virmachine.entity.vo.apply.VirtualMachineApply;
import com.cico.virmachine.domain.virmachine.entity.vo.apply.VirtualMachineTemplateInformation;
import com.cico.virmachine.domain.virmachine.service.VirtualMachineDomainService;
import com.cico.virmachine.domain.virmachine.service.VirtualMachineFactory;
import com.cico.virmachine.domain.virmachine.service.VirtualMachineTemplateDomainService;
import com.cico.virmachine.infrastructure.common.exception.ExceptionFactory;
import com.cico.virmachine.infrastructure.common.response.ResponseCode;
import com.cico.virmachine.infrastructure.utils.HttpClientUtils;
import com.cico.virmachine.infrastructure.virtualMachine.ics.IcsApi;
import com.cico.virmachine.interfaces.assembler.VirtualMachineAssembler;
import com.cico.virmachine.application.service.VirtualMachineApplicationService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.cico.virmachine.infrastructure.common.RedisKey.VIRTUAL_MACHINE_TASK_ID;
import static com.cico.virmachine.infrastructure.common.RedisKey.VIRTUAL_MACHINE_TEMPLATE_TASK_ID;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/16 14:12
 */
@Service
public class VirtualMachineApplicationServiceImpl implements VirtualMachineApplicationService {

    @Value(value = "${ics.http.ip}")
    private String baseUrl;

    @Autowired
    private IcsApi icsApi;

    @Autowired
    private HttpClientUtils httpClientUtils;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private VirtualMachineFactory virtualMachineFactory;

    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private VirtualMachineDomainService virtualMachineDomainService;

    @Autowired
    private VirtualMachineTemplateDomainService virtualMachineTemplateDomainService;

    @Override
    public List<VirtualMachineTypesDTO> getVirtualMachineTypes() {
        List<VirtualMachineTemplatePO> templatePOS = virtualMachineTemplateDomainService.selectWrapper(null);
        return templatePOS.stream().map(VirtualMachineAssembler::toVirtualMachineTypesDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createVirtualMachine(CreateMachineDTO createMachineDTO) {
        //todo 需要获取用户信息  通过 ThreadLocal 上下文提供
        //todo 通知消息中间件 【站内信和邮件MQ】 规定入参以及出参 暂时需求不是很明确
        //随机生成一个虚拟机ID
        //icsId创建成功时返回

        String machineSize = createMachineDTO.getMachineSize().name();
        VirtualMachineTemplatePO machineTemplatePO = virtualMachineTemplateDomainService.selectByMachineSize(machineSize).get(0);

        Optional.ofNullable(machineTemplatePO).orElseThrow(()->new ExceptionFactory(ResponseCode.NOT_EXISTS_VIRTUAL_MACHINE_TEMPLATE));

        //通过模板接口获取 hostId、guestosLabel、guestosType、disks、nics
        //其余的从createMachineDTO 取
        VirtualMachineTemplateInformation templateInformation = icsApi.<VirtualMachineTemplateInformation>getVirtualMachineTemplateInformation(baseUrl + "/vmtemplates/" + machineTemplatePO.getId(), VirtualMachineTemplateInformation.class);

        VirtualMachineApply backup = VirtualMachineApply.builder()
                .id(templateInformation.getId())
                .name(createMachineDTO.getMachineName())
                .hostId(templateInformation.getHostId())
                .description(createMachineDTO.getDescription())
                .guestosLabel(templateInformation.getGuestosLabel())
                .guestosType(templateInformation.getGuestosType())
                .disks(templateInformation.getDisks())
                .nics(templateInformation.getNics())
                .build();

        //DTO转PO
        VirtualMachinePO virtualMachinePO = virtualMachineFactory.createMachineToVirtualMachinePO(createMachineDTO);


        //设置虚拟机状态为申请中
        virtualMachinePO.setState(State.APPLICATION);
        //入库
        virtualMachineDomainService.insert(virtualMachinePO);
        //异步
        threadPoolTaskExecutor.execute(()->{
            BackupResult body = icsApi.<BackupResult>createVirtualMachine(baseUrl+"/vms?action=createByTemplate",backup,BackupResult.class);
            String taskId = body.getTaskId();
            //将任务ID set进去
            virtualMachinePO.setTaskId(taskId);
            virtualMachineDomainService.update(virtualMachinePO);
            //将任务ID存入redis 定时查询其状态
            redisTemplate.opsForList().leftPush(VIRTUAL_MACHINE_TASK_ID,taskId);
        });
    }

    //@Override
//    @Transactional(rollbackFor = Exception.class)
//    public void createVirtualMachine1(CreateMachineDTO createMachineDTO) {
//        //todo 需要获取用户信息  通过 ThreadLocal 上下文提供
//        //todo 通知消息中间件 【站内信和邮件MQ】 规定入参以及出参 暂时需求不是很明确
//        //随机生成一个虚拟机ID
//        String id = UUID.randomUUID().toString().replace("-","").replace("_","");
//        String machineSize = createMachineDTO.getMachineSize().name();
//        VirtualMachineTemplatePO machineTemplatePO = virtualMachineTemplateDomainService.selectByMachineSize(machineSize).get(0);
//
//        Optional.ofNullable(machineTemplatePO).orElseThrow(()->new ExceptionFactory(ResponseCode.NOT_EXISTS_VIRTUAL_MACHINE_TEMPLATE));
//        VirtualMachineApply backup = VirtualMachineApply.builder()
//                .id(id)
////                .vmId(machineTemplatePO.getVmId())
//                .name(createMachineDTO.getMachineName())
////                .backupType(machineTemplatePO.getBackupType().name())// todo 写入数据库还是用户选择
////                .datastoreID(machineTemplatePO.getDatastoreID())// todo 写入数据库还是用户选择
//                .build();
//
//        //DTO转PO
//        VirtualMachinePO virtualMachinePO = virtualMachineFactory.createMachineToVirtualMachinePO(createMachineDTO);
//
//        //补充字段 从虚拟机模板中获取
////        virtualMachinePO.setRateLimit(machineTemplatePO.getRateLimit());
//        virtualMachinePO.setIcsId(id);
//        virtualMachinePO.setVmId(machineTemplatePO.getVmId());
//        virtualMachinePO.setDescription(machineTemplatePO.getDescription());
//        // TODO: 2021/4/22 是否需要入库
////        virtualMachinePO.setBackupType(machineTemplatePO.getBackupType());
////        virtualMachinePO.setDatastoreID(machineTemplatePO.getDatastoreID());
//        //设置虚拟机状态为申请中
//        virtualMachinePO.setState(State.APPLICATION);
//        //入库
//        virtualMachineDomainService.insert(virtualMachinePO);
//        //异步
//        threadPoolTaskExecutor.execute(()->{
//            BackupResult body = icsApi.<BackupResult>createVirtualMachine(baseUrl+"/vmbackups",backup,BackupResult.class);
//            String taskId = body.getTaskId();
//            //将任务ID set进去
//            virtualMachinePO.setTaskId(taskId);
//            virtualMachineDomainService.update(virtualMachinePO);
//            //将任务ID存入redis 定时查询其状态
//            redisTemplate.opsForList().leftPush(VIRTUAL_MACHINE_TASK_ID,taskId);
//        });
//
//    }

    @Override
    public void updateVirtualMachine(Integer id, String name) {
        VirtualMachinePO virtualMachineById = virtualMachineDomainService.selectById(id);
        Optional.ofNullable(virtualMachineById).orElseThrow(()-> new ExceptionFactory(ResponseCode.NOT_EXISTS_VIRTUAL_MACHINE));
        virtualMachineById.setMachineName(name);
        virtualMachineDomainService.update(virtualMachineById);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteVirtualMachine(Integer id) {
        VirtualMachinePO machinePO = virtualMachineDomainService.selectById(id);
        Optional.ofNullable(machinePO).orElseThrow(()-> new ExceptionFactory(ResponseCode.NOT_EXISTS_VIRTUAL_MACHINE));
        String icsId = machinePO.getIcsId();
        //异步
        threadPoolTaskExecutor.execute(()->{
            icsApi.deleteVirtualMachine(baseUrl+"/vmbackups/"+ icsId);
        });
        virtualMachineDomainService.delete(id);
    }

    @Override
    public VirtualMachinePO getVirtualMachineDetail(Integer id) {
        VirtualMachinePO virtualMachineById = virtualMachineDomainService.selectById(id);
        Optional.ofNullable(virtualMachineById).orElseThrow(()-> new ExceptionFactory(ResponseCode.NOT_EXISTS_VIRTUAL_MACHINE));
        return virtualMachineById;
    }

    @Override
    public Pair<Long, List<VirtualMachineListDTO>> listVirtualMachine(Integer pageNum, Integer size) {
        QueryWrapper<VirtualMachinePO> queryWrapper = new QueryWrapper<>();
        // TODO: 2021/4/20 添加查询条件 如：ID
        Page<VirtualMachinePO> page = new Page(pageNum,size);
        IPage iPage = virtualMachineDomainService.selectByPage(page, queryWrapper);
        List<VirtualMachinePO> records = iPage.getRecords();
        List<VirtualMachineListDTO> listDTOS = records.stream().map(VirtualMachineAssembler::toVirtualMachineListDTO).collect(Collectors.toList());
        long total = iPage.getTotal();
        return Pair.of(total,listDTOS);
    }

    @Override
    public List<VirtualMachineTemplateDTO> listVirtualMachineTemplate() {
        List<VirtualMachineTemplatePO> virtualMachineTemplatePO = virtualMachineTemplateDomainService.selectWrapper(null);
        return virtualMachineTemplatePO.stream().map(VirtualMachineAssembler::toVirtualMachineTemplateDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addVirtualMachineTemplate(AddVirtualMachineTemplateDTO addVirtualMachineTemplateDTO) {
        VirtualMachineTemplatePO virtualMachineTemplatePO = virtualMachineFactory.addVirtualMachineTemplateDtoToVirtualMachineTemplatePO(addVirtualMachineTemplateDTO);
        //获取需要的vmId 通过MachineSize来获取
        VirtualMachineTemplatePO machineTemplatePO = virtualMachineTemplateDomainService.selectByMachineSize(virtualMachineTemplatePO.getMachineSize().name()).get(0);
        String vmId = machineTemplatePO.getVmId();
        virtualMachineTemplatePO.setVmId(vmId);
        virtualMachineTemplateDomainService.inset(virtualMachineTemplatePO);

        //异步
        threadPoolTaskExecutor.execute(()->{
            StringBuilder builder = new StringBuilder();
            builder.append("vmId=" + vmId + "&")
                    .append("templateName="+ addVirtualMachineTemplateDTO.getTemplateName()+"&")
                    .append("templateDesc="+ addVirtualMachineTemplateDTO.getTemplateDesc());
            BackupResult body = icsApi.<BackupResult>createVirtualMachineTemplate(baseUrl + "/vmtemplates?" + builder.toString(),BackupResult.class);
            String taskId = body.getTaskId();
            VirtualMachineTemplatePO templatePO = virtualMachineTemplateDomainService.selectByMachineId(virtualMachineTemplatePO.getId()).get(0);
            templatePO.setTaskId(taskId);
            virtualMachineTemplateDomainService.update(templatePO);
            //将任务ID存入redis 定时查询其状态
            redisTemplate.opsForList().leftPush(VIRTUAL_MACHINE_TEMPLATE_TASK_ID,taskId);
        });

    }

    @Override
    public List<HostListDTO> listHost() {
        List<VirtualMachinePO> virtualMachinePOS = virtualMachineDomainService.selectByQueryWarp(null);
        return virtualMachinePOS.stream().map(VirtualMachineAssembler::toHostListDTO).collect(Collectors.toList());
    }

}
