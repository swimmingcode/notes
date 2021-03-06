package org.youyuan.jwt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.jwt.service.RecordService;
import org.youyuan.jwt.utils.jwt.annotation.Token;
import org.youyuan.jwt.utils.common.response.Response;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.common.response.ResponseFactory;
import org.youyuan.jwt.utils.jwt.annotation.AccessPermission;
import org.youyuan.jwt.vo.request.ReserveBookVO;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Describe: 记录领域
 * @Author: youjiancheng
 * @date 2021/3/6 15:19
 */
@RestController
@RequestMapping("/api/v1.0/record")
@Api(tags = "记录领域")
public class RecordApi {

    @Qualifier("taskExecutor")
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    RecordService recordService;

    @AccessPermission(roleName = "user")
    @ApiOperation(value = "教材预定")
    @PostMapping("/reserve")
    public Response<Void> textBookReserve(@ApiIgnore @Token org.youyuan.jwt.utils.jwt.Token token,
                                          @ApiParam(value = "预定教材实体类") @RequestBody  ReserveBookVO reserveBookVO) {
        //使用redis做分布式锁
        // TODO: 2021/3/6 使用线程模拟
        for (int i = 0; i < 5; i++) {
            threadPoolTaskExecutor.submit(()->{
                recordService.textBookReserve(reserveBookVO, token);
            });
        }
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }
}
