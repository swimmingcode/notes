package com.cico.virmachine.domain.virmachine.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import com.cico.virmachine.domain.virmachine.mapper.VirtualMachineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/16 14:16
 */
@Service
public class VirtualMachineDomainService {

    @Autowired
    private VirtualMachineMapper virtualMachineMapper;

    /**
     * 插入数据
     *
     * @param virtualMachinePO
     * @return
     */
    public int insert(VirtualMachinePO virtualMachinePO) {
        return virtualMachineMapper.insert(virtualMachinePO);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(Integer id) {
        return virtualMachineMapper.deleteById(id);
    }

    /**
     * 更新
     *
     * @param virtualMachinePO
     * @return
     */
    public int update(VirtualMachinePO virtualMachinePO) {
        return virtualMachineMapper.updateById(virtualMachinePO);
    }

    public VirtualMachinePO selectById(Integer id) {
        return virtualMachineMapper.selectById(id);
    }

    public List<VirtualMachinePO> selectByTaskId(String taskId) {
        QueryWrapper<VirtualMachinePO> wrapper = new QueryWrapper<>();
        wrapper.eq("task_id",taskId);
        return virtualMachineMapper.selectList(wrapper);
    }

    public List<VirtualMachinePO> selectByQueryWarp(Wrapper<VirtualMachinePO> wrapper) {
        return virtualMachineMapper.selectList(wrapper);
    }

    public Integer selectCountByQueryWarp(Wrapper<VirtualMachinePO> wrapper) {
        return virtualMachineMapper.selectCount(wrapper);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    public IPage<VirtualMachinePO> selectByPage(IPage<VirtualMachinePO> page,Wrapper<VirtualMachinePO> wrapper) {
        return virtualMachineMapper.selectPage(page,wrapper);
    }



}
