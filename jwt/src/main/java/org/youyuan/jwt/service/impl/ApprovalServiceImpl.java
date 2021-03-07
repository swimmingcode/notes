package org.youyuan.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youyuan.jwt.domain.RecordPO;
import org.youyuan.jwt.mapper.RecordMapper;
import org.youyuan.jwt.service.ApprovalService;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.diyenum.ApproveStatus;
import org.youyuan.jwt.utils.exception.ExceptionFactory;

import java.util.Optional;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/7 16:52
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    RecordMapper recordMapper;

    @Override
    public void approveApply(ApproveStatus approveStatus, Integer recordId) {
        RecordPO recordPO = recordMapper.selectById(recordId);
        Optional.ofNullable(recordPO).orElseThrow(()->new ExceptionFactory(ResponseCode.BOOK_NOT_EXISTS));
        recordPO.setApproveStatus(approveStatus);
        recordMapper.updateById(recordPO);
    }
}
