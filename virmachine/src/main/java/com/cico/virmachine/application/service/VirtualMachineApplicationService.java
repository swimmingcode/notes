package com.cico.virmachine.application.service;

import com.cico.virmachine.domain.virmachine.entity.dto.*;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/16 14:11
 */
public interface VirtualMachineApplicationService {

    /**
     * 获取虚拟机配置种类
     *
     * @return
     */
    List<VirtualMachineTypesDTO> getVirtualMachineTypes();


    /**
     * 创建虚拟机
     * @param createMachineDTO  虚拟机实体类
     */
    void createVirtualMachine(CreateMachineDTO createMachineDTO);

    /**
     * 编辑虚拟机
     * @param id 虚拟机ID
     * @param name 虚拟机名称
     */
    void updateVirtualMachine(Integer id, String name);

    /**
     * 删除虚拟机
     * @param id 虚拟机ID
     */
    void deleteVirtualMachine(Integer id);

    /**
     * 获取虚拟机详情
     * @param id   虚拟机ID
     * @return
     */
    VirtualMachinePO getVirtualMachineDetail(Integer id);

    /**
     * 分页获取虚拟机列表
     * @param page  当前页数
     * @param size  每页大小
     * @return
     */
    Pair<Long, List<VirtualMachineListDTO>> listVirtualMachine(Integer page, Integer size);

    /**
     * 虚拟机模板
     *
     * @return
     */
    List<VirtualMachineTemplateDTO> listVirtualMachineTemplate();

    /**
     * 新增虚拟机模板"
     *
     * @param addVirtualMachineTemplateDTO
     */
    void addVirtualMachineTemplate(AddVirtualMachineTemplateDTO addVirtualMachineTemplateDTO);


    /**
     * 主机列表
     *
     * @return
     */
    List<HostListDTO> listHost();

}
