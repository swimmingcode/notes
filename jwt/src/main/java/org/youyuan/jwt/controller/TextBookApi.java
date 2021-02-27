package org.youyuan.jwt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.youyuan.jwt.service.TextBookService;
import org.youyuan.jwt.utils.common.response.Response;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.common.response.ResponseFactory;
import org.youyuan.jwt.utils.jwt.annotation.AccessPermission;

/**
 * @Describe: 教材共享领域
 * @Author: youjiancheng
 * @date 2021/2/27 15:41
 */
@RestController
@RequestMapping("/api/v1.0/textBook")
@Slf4j
@Api(tags= "教材领域")
public class TextBookApi {

    @Autowired
    TextBookService textBookService;

    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public Response<Void> upload(@RequestParam("file") MultipartFile file) {
        //无文件系统 直接将文件存在项目中
        textBookService.upload(file);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }


}
