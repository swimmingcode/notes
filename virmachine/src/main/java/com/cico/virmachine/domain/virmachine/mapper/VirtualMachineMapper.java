package com.cico.virmachine.domain.virmachine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/16 14:34
 */
@Mapper
public interface VirtualMachineMapper extends BaseMapper<VirtualMachinePO> {

    @Override
    int insert(VirtualMachinePO virtualMachinePO);
}
