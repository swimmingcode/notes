package com.cico.virmachine.domain.virmachine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachineTemplatePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/20 10:39
 */
@Mapper
public interface VirtualMachineTemplateMapper extends BaseMapper<VirtualMachineTemplatePO> {

    @Override
    int insert(VirtualMachineTemplatePO virtualMachineTemplatePO);

}
