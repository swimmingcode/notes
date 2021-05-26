//package com.cico.virmachine.infrastructure.virtualMachine;
//
//import com.cico.virmachine.domain.virmachine.entity.dto.VirtualMachineTypesDTO;
//import com.cico.virmachine.domain.virmachine.entity.vo.MachineSize;
//import com.cico.virmachine.domain.virmachine.entity.vo.MachineType;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Describe: #请描述当前类的功能#
// * @Author: youjiancheng
// * @date 2021/4/19 9:33
// */
//@Component
//public class VirMachineType {
//
//    private static List<VirtualMachineTypesDTO> virtualMachineSettingsList = new ArrayList<>();
//
//    static {
//        VirtualMachineTypesDTO linuxSettingsOne = VirtualMachineTypesDTO.builder().machineType(MachineType.LINUX).machineSize(MachineSize.LINUX_LOW).cpuCores("1核").cpuMemory("2G").disk("20G").bandwidth("10M").build();
//        VirtualMachineTypesDTO linuxSettingsTwo = VirtualMachineTypesDTO.builder().machineType(MachineType.LINUX).machineSize(MachineSize.LINUX_MEDIUM).cpuCores("2核").cpuMemory("4G").disk("100G").bandwidth("10M").build();
//        VirtualMachineTypesDTO linuxSettingsThree = VirtualMachineTypesDTO.builder().machineType(MachineType.LINUX).machineSize(MachineSize.LINUX_HIGH).cpuCores("8核").cpuMemory("16G").disk("500G").bandwidth("10M").build();
//        VirtualMachineTypesDTO linuxSettingsFour = VirtualMachineTypesDTO.builder().machineType(MachineType.LINUX).machineSize(MachineSize.LINUX_TOP).cpuCores("16核").cpuMemory("32G").disk("1024G").bandwidth("10M").build();
//
//        VirtualMachineTypesDTO windowsSettingsOne = VirtualMachineTypesDTO.builder().machineType(MachineType.WINDOWS).machineSize(MachineSize.WINDOWS_LOW).cpuCores("1核").cpuMemory("2G").disk("20G").bandwidth("10M").build();
//        VirtualMachineTypesDTO windowsSettingsTwo = VirtualMachineTypesDTO.builder().machineType(MachineType.WINDOWS).machineSize(MachineSize.WINDOWS_MEDIUM).cpuCores("2核").cpuMemory("4G").disk("100G").bandwidth("10M").build();
//        VirtualMachineTypesDTO windowsSettingsThree = VirtualMachineTypesDTO.builder().machineType(MachineType.WINDOWS).machineSize(MachineSize.WINDOWS_HIGH).cpuCores("8核").cpuMemory("16G").disk("500G").bandwidth("10M").build();
//        VirtualMachineTypesDTO windowsSettingsFour = VirtualMachineTypesDTO.builder().machineType(MachineType.WINDOWS).machineSize(MachineSize.WINDOWS_TOP).cpuCores("16核").cpuMemory("32G").disk("1024G").bandwidth("10M").build();
//
//        virtualMachineSettingsList.add(linuxSettingsOne);
//        virtualMachineSettingsList.add(linuxSettingsTwo);
//        virtualMachineSettingsList.add(linuxSettingsThree);
//        virtualMachineSettingsList.add(linuxSettingsFour);
//
//        virtualMachineSettingsList.add(windowsSettingsOne);
//        virtualMachineSettingsList.add(windowsSettingsTwo);
//        virtualMachineSettingsList.add(windowsSettingsThree);
//        virtualMachineSettingsList.add(windowsSettingsFour);
//    }
//
//    public List<VirtualMachineTypesDTO> getVirtualMachineType() {
//        return virtualMachineSettingsList;
//    }
//}
