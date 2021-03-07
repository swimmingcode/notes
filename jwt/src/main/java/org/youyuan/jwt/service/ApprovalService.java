package org.youyuan.jwt.service;

import org.youyuan.jwt.utils.diyenum.ApproveStatus;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/7 16:52
 */
public interface ApprovalService {

    /**
     * 审批申请
     *
     * @param approveStatus
     * @param recordId
     */
    void approveApply(ApproveStatus approveStatus, Integer recordId);
}
