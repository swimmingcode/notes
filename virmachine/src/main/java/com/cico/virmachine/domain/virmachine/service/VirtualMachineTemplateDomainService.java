package com.cico.virmachine.domain.virmachine.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachineTemplatePO;
import com.cico.virmachine.domain.virmachine.mapper.VirtualMachineTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/20 15:31
 */
@Service
public class VirtualMachineTemplateDomainService {

    @Autowired
    private VirtualMachineTemplateMapper virtualMachineTemplateMapper;

    public int update(VirtualMachineTemplatePO virtualMachineTemplatePO) {
        return virtualMachineTemplateMapper.updateById(virtualMachineTemplatePO);
    }

    public List<VirtualMachineTemplatePO> selectWrapper(Wrapper<VirtualMachineTemplatePO> wrapper) {
        return virtualMachineTemplateMapper.selectList(wrapper);
    }

    public int inset(VirtualMachineTemplatePO virtualMachineTemplatePO) {
        return virtualMachineTemplateMapper.insert(virtualMachineTemplatePO);
    }


    public List<VirtualMachineTemplatePO> selectByMachineSize(String machineSize) {
        QueryWrapper<VirtualMachineTemplatePO> wrapper = new QueryWrapper<>();
        wrapper.eq("machine_size",machineSize);
        return virtualMachineTemplateMapper.selectList(wrapper);
    }

    public List<VirtualMachineTemplatePO> selectByMachineTemplateTaskId(String taskId) {
        QueryWrapper<VirtualMachineTemplatePO> wrapper = new QueryWrapper<>();
        wrapper.eq("task_id",taskId);
        return virtualMachineTemplateMapper.selectList(wrapper);
    }

    public List<VirtualMachineTemplatePO> selectByMachineId(Integer id) {
        QueryWrapper<VirtualMachineTemplatePO> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return virtualMachineTemplateMapper.selectList(wrapper);
    }

}
