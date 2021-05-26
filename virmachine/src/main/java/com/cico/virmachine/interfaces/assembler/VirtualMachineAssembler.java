package com.cico.virmachine.interfaces.assembler;


import com.cico.virmachine.domain.virmachine.entity.dto.HostListDTO;
import com.cico.virmachine.domain.virmachine.entity.dto.VirtualMachineListDTO;
import com.cico.virmachine.domain.virmachine.entity.dto.VirtualMachineTemplateDTO;
import com.cico.virmachine.domain.virmachine.entity.dto.VirtualMachineTypesDTO;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachineTemplatePO;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/16 16:50
 */
public class VirtualMachineAssembler {

    static public VirtualMachineListDTO toVirtualMachineListDTO(VirtualMachinePO virtualMachinePO) {
        return VirtualMachineListDTO.builder()
                .ip(virtualMachinePO.getIp())
                .applyTime(virtualMachinePO.getApplyTime())
                .machineLevel(virtualMachinePO.getMachineLevel())
                .machineName(virtualMachinePO.getMachineName())
                .state(virtualMachinePO.getState())
                .build();
    }

    static public VirtualMachineTemplateDTO toVirtualMachineTemplateDTO(VirtualMachineTemplatePO virtualMachineTemplatePO) {
        String name = virtualMachineTemplatePO.getMachineSize().name();
        return VirtualMachineTemplateDTO.builder()
                .id(virtualMachineTemplatePO.getId())
                .cpuCores(virtualMachineTemplatePO.getCpuCores())
                .cpuMemory(virtualMachineTemplatePO.getCpuMemory())
                .disk(virtualMachineTemplatePO.getDisk())
                .machineType(virtualMachineTemplatePO.getMachineType().name().toLowerCase())
                .machineSize(name.contains("LOW") ? "低配版" : (name.contains("MEDIUM") ? "中配版" : (name.contains("HIGH") ? "高配版" : "顶配版")))
                .build();
    }

    static public VirtualMachineTypesDTO toVirtualMachineTypesDTO(VirtualMachineTemplatePO virtualMachineTemplatePO) {
        return VirtualMachineTypesDTO.builder()
                .machineType(virtualMachineTemplatePO.getMachineType().getName())
                .machineSize(virtualMachineTemplatePO.getMachineSize().getName())
                .disk(virtualMachineTemplatePO.getDisk())
                .cpuCores(virtualMachineTemplatePO.getCpuCores())
                .cpuMemory(virtualMachineTemplatePO.getCpuMemory())
                .bandwidth(virtualMachineTemplatePO.getBandwidth())
                .build();
    }

    static public HostListDTO toHostListDTO(VirtualMachinePO virtualMachinePO) {
        return HostListDTO.builder()
                .id(virtualMachinePO.getId())
                .ip(virtualMachinePO.getIp())
                .build();
    }
}


