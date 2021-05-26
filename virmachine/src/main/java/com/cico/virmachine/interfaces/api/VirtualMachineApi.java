package com.cico.virmachine.interfaces.api;

import com.cico.virmachine.domain.virmachine.entity.dto.*;
import com.cico.virmachine.domain.virmachine.entity.po.VirtualMachinePO;
import com.cico.virmachine.infrastructure.common.PageResponse;
import com.cico.virmachine.infrastructure.common.response.Response;
import com.cico.virmachine.infrastructure.common.response.ResponseCode;
import com.cico.virmachine.infrastructure.common.response.ResponseFactory;
import com.cico.virmachine.application.service.VirtualMachineApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Describe: 虚拟机申请Api
 * @Author: youjiancheng
 * @date 2021/4/16 14:11
 */
@Api(tags = "虚拟机申请Api")
@Slf4j
@RestController
@RequestMapping("/virMachine")
public class VirtualMachineApi {

    @Autowired
    VirtualMachineApplicationService virtualMachineService;

    @ApiOperation(value = "获取虚拟机配置种类")
    @GetMapping("/config/types")
    public Response<List<VirtualMachineTypesDTO>> getVirtualMachineTypes() {
        //返回的应该一个类，而不是一个字段
        List<VirtualMachineTypesDTO> virtualMachineTypesDTOS = virtualMachineService.getVirtualMachineTypes();
        return ResponseFactory.<List<VirtualMachineTypesDTO>>productResult(ResponseCode.OK,virtualMachineTypesDTOS);
    }

    @ApiOperation(value = "申请创建虚拟机")
    @PostMapping("/create")
    public Response<Void> createVirtualMachine(@ApiParam(value = "申请创建虚拟机实体类") @RequestBody  @Validated CreateMachineDTO createMachineDTO) {
        virtualMachineService.createVirtualMachine(createMachineDTO);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @ApiOperation(value = "编辑虚拟机")
    @PutMapping("/update/{id}/{name}")
    public Response<Void> updateVirtualMachine(@ApiParam(value = "虚拟机ID") @PathVariable Integer id,
                                               @ApiParam(value = "虚拟机名称") @PathVariable String name) {
        virtualMachineService.updateVirtualMachine(id,name);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @ApiOperation(value = "删除虚拟机")
    @DeleteMapping("/delete/{id}")
    public Response<Void> deleteVirtualMachine(@ApiParam(value = "虚拟机ID") @PathVariable Integer id) {
        virtualMachineService.deleteVirtualMachine(id);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @ApiOperation(value = "获取虚拟机详情")
    @GetMapping("/detail/{id}")
    public Response<VirtualMachinePO> getVirtualMachineDetail(@ApiParam(value = "虚拟机ID") @PathVariable Integer id) {
        //VirtualMachinePO 换成DTO
        VirtualMachinePO virtualMachinePO = virtualMachineService.getVirtualMachineDetail(id);
        return ResponseFactory.<VirtualMachinePO>productResult(ResponseCode.OK,virtualMachinePO);
    }


    @ApiOperation(value = "分页获取虚拟机列表")
    @GetMapping("/list")
    public Response<PageResponse<VirtualMachineListDTO>> listVirtualMachine(@ApiParam(value = "当前页数") @RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
                                                                               @ApiParam(value = "每页大小") @RequestParam(value = "sizeNum",defaultValue = "10",required = false)Integer sizeNum ) {
        Pair<Long,List<VirtualMachineListDTO>> pageResponse = virtualMachineService.listVirtualMachine(pageNum,sizeNum);
        PageResponse<VirtualMachineListDTO> response = PageResponse.<VirtualMachineListDTO>builder()
                .total(pageResponse.getLeft())
                .list(pageResponse.getRight())
                .build();
        return ResponseFactory.<PageResponse<VirtualMachineListDTO>>productResult(ResponseCode.OK,response);
    }

    @ApiOperation(value = "虚拟机模板列表")
    @GetMapping("/template/list")
    public Response<List<VirtualMachineTemplateDTO>> listVirtualMachineTemplate() {
        List<VirtualMachineTemplateDTO> virtualMachineTemplateDTOS = virtualMachineService.listVirtualMachineTemplate();
        return ResponseFactory.<List<VirtualMachineTemplateDTO>>productResult(ResponseCode.OK, virtualMachineTemplateDTOS);
    }

    @ApiOperation(value = "新增虚拟机模板")
    @PostMapping("/add/template")
    public Response<Void> addVirtualMachineTemplate(@ApiParam(value = "添加虚拟机模板实体类") @RequestBody AddVirtualMachineTemplateDTO addVirtualMachineTemplateDTO) {
        virtualMachineService.addVirtualMachineTemplate(addVirtualMachineTemplateDTO);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @ApiOperation(value = "获取主机列表")
    @GetMapping("/host/list")
    public Response<List<HostListDTO>> getHostList() {
        List<HostListDTO> hostListDTOS = virtualMachineService.listHost();
        return ResponseFactory.<List<HostListDTO>>productResult(ResponseCode.OK,hostListDTOS);
    }

    @ApiOperation(value = "虚机基础信息")
    @GetMapping("/host/{id}/basic/information")
    public Response<HostBasicInformationDTO> getHostBasicInformation(@ApiParam(value = "主机id") @PathVariable(value = "id") Integer id) {
        // TODO: 2021/4/21 接口获取
        return ResponseFactory.<HostBasicInformationDTO>productResult(ResponseCode.OK,null);
    }

    @ApiOperation(value = "预警消息列表")
    @GetMapping("/host/{id}/warning/message")
    public Response<List<WarningMessageListDTO>> getHostWarningMessage(@ApiParam(value = "主机id") @PathVariable(value = "id") Integer id) {
        // TODO: 2021/4/21 接口获取
        return ResponseFactory.<List<WarningMessageListDTO>>productResult(ResponseCode.OK,null);
    }

}
