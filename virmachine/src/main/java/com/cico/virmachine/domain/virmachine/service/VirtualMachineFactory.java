package com.cico.virmachine.domain.virmachine.service;

import com.cico.virmachine.domain.virmachine.entity.dto.AddVirtualMachineTemplateDTO;
import com.cico.virmachine.domain.virmachine.entity.dto.CreateMachineDTO;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachineTemplatePO;
import org.springframework.stereotype.Component;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/19 15:10
 */
@Component
public class VirtualMachineFactory {

    public VirtualMachinePO createMachineToVirtualMachinePO(CreateMachineDTO createMachineDTO) {
        return VirtualMachinePO.builder()
                .machineType(createMachineDTO.getMachineType())
                .machineSize(createMachineDTO.getMachineSize())
                .machineName(createMachineDTO.getMachineName())
                .isNotice(createMachineDTO.getIsNotice())
                .cpuCores(createMachineDTO.getCpuCores())
                .cpuMemory(createMachineDTO.getCpuMemory())
                .disk(createMachineDTO.getDisk())
                .bandwidth(createMachineDTO.getBandwidth())
                .machineLevel(createMachineDTO.getMachineSize().getLevel())
                .description(createMachineDTO.getDescription())
                .build();
    }

    public VirtualMachineTemplatePO addVirtualMachineTemplateDtoToVirtualMachineTemplatePO(AddVirtualMachineTemplateDTO addVirtualMachineTemplateDTO) {
        return VirtualMachineTemplatePO.builder()
                .machineType(addVirtualMachineTemplateDTO.getMachineType())
                .machineSize(addVirtualMachineTemplateDTO.getMachineSize())
                .cpuMemory(addVirtualMachineTemplateDTO.getCpuMemory())
                .disk(addVirtualMachineTemplateDTO.getDisk())
                .cpuCores(addVirtualMachineTemplateDTO.getCpuCores())
                .templateDesc(addVirtualMachineTemplateDTO.getTemplateDesc())
                .templateName(addVirtualMachineTemplateDTO.getTemplateName())
                .build();
    }
}
