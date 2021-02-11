package org.youyuan.jwt.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youyuan.jwt.service.UserService;
import org.youyuan.jwt.utils.common.response.Response;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.common.response.ResponseFactory;
import org.youyuan.jwt.utils.encryption.Md5Utils;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.utils.jwt.annotation.UnLogin;
import org.youyuan.jwt.vo.response.UserAccountVO;

/**
 * @Describe: 用户领域
 * @Author: youjiancheng
 * @Date: 2021/2/1 14:27
 */
@RestController
@Api(tags = "用户领域")
@RequestMapping("/api/v1.0/user")
public class UserApi {

    @Autowired
    UserService userService;


    @UnLogin
    @ApiResponse(code = 200,message = "成功")
    @ApiOperation(value = "登录接口",notes = "返回Token")
    @PostMapping("/login")
    public ResponseEntity<String> login(@ApiParam(value = "用户名/密码") @RequestBody @Validated UserAccountVO userAccountVO) {
        //验证用户名密码
        // TODO: 2021/2/9 对账号/密码进行valid
        userService.verifyUserNameAndPassword(userAccountVO.getUsername(),userAccountVO.getPassword());
        //生成Token
        String token = userService.login(Token.builder().name("zs").id(1).build());
        return new ResponseEntity<String>(token,HttpStatus.OK);
    }

    @ApiResponse(code = 200,message = "成功")
    @ApiOperation(value = "创建用户账号")
    @PostMapping("/create")
    public Response<UserAccountVO> createAccount() {
        UserAccountVO userAccount = userService.createAccount();
        return ResponseFactory.productResult(ResponseCode.OK,userAccount);
    }








}
