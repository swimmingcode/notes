package org.youyuan.jwt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.youyuan.jwt.service.ApprovalService;
import org.youyuan.jwt.utils.common.response.Response;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.common.response.ResponseFactory;
import org.youyuan.jwt.utils.diyenum.ApproveStatus;
import org.youyuan.jwt.utils.jwt.annotation.AccessPermission;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/7 16:43
 */
@RestController
@Slf4j
@Api(tags = "审批领域")
@RequestMapping("/approval")
public class ApprovalApi {

    @Autowired
    ApprovalService approvalService;

    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "审批申请")
    @PatchMapping("/apply/{recordId}/{approveStatus}")
    public Response<Void> approveApply(@ApiParam(value = "审批结果")  @PathVariable ApproveStatus approveStatus,
                                  @ApiParam(value = "审批单ID") @PathVariable Integer recordId) {
        approvalService.approveApply(approveStatus,recordId);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }
}
