package org.youyuan.jwt.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.service.UserService;
import org.youyuan.jwt.utils.common.PageResponse;
import org.youyuan.jwt.utils.common.response.Response;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.common.response.ResponseFactory;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.utils.jwt.annotation.AccessPermission;
import org.youyuan.jwt.utils.jwt.annotation.UnLogin;
import org.youyuan.jwt.vo.request.*;
import org.youyuan.jwt.vo.response.UserAccountVO;
import org.youyuan.jwt.vo.response.UserInfo;
import org.youyuan.jwt.vo.response.UserVO;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: 用户领域
 * @Author: youjiancheng
 * @Date: 2021/2/1 14:27
 */
@RestController
@Api(tags = "用户领域")
@RequestMapping("/api/v1.0/user")
@Slf4j
public class UserApi {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Qualifier(value = "redisTemplate")
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @UnLogin
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    @ApiOperation(value = "登录接口", notes = "返回Token")
    @PostMapping("/account/login")
    public Response<String> login(@ApiIgnore @org.youyuan.jwt.utils.jwt.annotation.Token Token tokenDetail,
                                  @ApiParam(value = "用户名/密码") @RequestBody @Validated UserAccountVO userAccountVO,
                                  HttpServletResponse response) {
        UserPO userPO = userService.verifyUserNameAndPassword(userAccountVO.getUsername(), userAccountVO.getPassword());
        //生成Token
        String token = userService.login(Token.builder().name(userPO.getName()).id(userPO.getId()).build());
        //只允许一个设备登录 redis存入此账号登录的次数
        redisTemplate.opsForValue().set(userAccountVO.getUsername(), 1L, 24L, TimeUnit.HOURS);
        //放入cookie当中
        tokenService.setCookie(token, response);
        return ResponseFactory.productResult(ResponseCode.OK, token);
    }


    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "鉴权Token接口", notes = "返回用户信息")
    @GetMapping("/verify/token")
    public Response<Token> tokenVerify(@ApiParam(value = "Token字符串") @Validated @NotEmpty @RequestParam(value = "token") String token) {
        Token tokenRes = tokenService.verifyToken(token);
        return ResponseFactory.<Token>productResult(ResponseCode.OK, tokenRes);
    }

    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "创建用户账号")
    @PostMapping("/account/create")
    public Response<UserAccountVO> createAccount(@ApiIgnore @org.youyuan.jwt.utils.jwt.annotation.Token Token token) {
        UserAccountVO userAccount = userService.createAccount();
        return ResponseFactory.<UserAccountVO>productResult(ResponseCode.OK, userAccount);
    }

    @AccessPermission(roleName = "user")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "退出账号")
    @PostMapping("/account/logout")
    public Response<Void> logoutAccount(@ApiParam(value = "账户名称") @RequestParam(value = "username") String username) {
        userService.logoutAccount(username);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }


    @AccessPermission(roleName = "user")
    @ApiOperation(value = "修改用户名")
    @PutMapping("/update/account/username")
    public Response<Void> updateAccountUserName(@ApiParam(value = "新/旧账户名称") @Validated @RequestBody UpdateAccountNameVO updateAccountNameVO) {
        //名称唯一
        userService.updateAccountUserName(updateAccountNameVO.getUsernameOld(), updateAccountNameVO.getUsernameNew());
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }


    @AccessPermission(roleName = "user")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "获取邮箱Code码")
    @PostMapping("/email/code")
    public Response<Void> getEmailCode(@ApiParam(value = "获取邮箱Code码") @RequestBody @Validated EmailCodeVO emailCodeVO) {
        userService.getEmailCode(emailCodeVO);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @AccessPermission(roleName = "user")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "绑定邮箱")
    @PostMapping("/bind/email")
    public Response<Void> bindEmail(@ApiParam(value = "获取邮箱Code码") @RequestBody @Validated BindEmailVO bindEmailVO) {
        userService.bindEmail(bindEmailVO);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @AccessPermission(roleName = "user")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "修改用户密码", notes = "使用旧密码验证")
    @PutMapping("/update/password/by/oldPwd")
    public Response<Void> updateAccountPasswordByOldPwd(@ApiParam(value = "使用旧密码修改密码") @RequestBody @Validated UpdatePasswordByOldPwdVO updatePasswordByOldPwdVO) {
        userService.updateAccountPasswordByOldPwd(updatePasswordByOldPwdVO);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @AccessPermission(roleName = "user")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "修改用户密码", notes = "使用邮箱验证码进行验证")
    @PutMapping("/update/account/by/code")
    public Response<Void> updateAccountPasswordByCode(@ApiParam(value = "使用邮箱修改密码") @RequestBody @Validated UpdatePasswordByCodeVO updatePasswordByCodeVO) {
        //获取验证码【通用接口】
        //验证
        userService.updateAccountPasswordByCode(updatePasswordByCodeVO);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }


    @AccessPermission(roleName = "admin")
    @ApiResponse(code = 200, message = "成功")
    @ApiOperation(value = "用户列表")
    @GetMapping("/user/list")
    public Response<PageResponse<UserVO>> getUserList(@ApiParam("页数") @RequestParam(value = "page",defaultValue = "1",required = false) Integer page,
                                                        @ApiParam("大小") @RequestParam(value = "size",defaultValue = "10",required = false) Integer size,
                                                        @ApiParam("查询参数") @RequestParam(value = "search" ,required = false) String search) {
        List<UserVO> userInfoList = userService.getUserList(page,size,search);
        return ResponseFactory.productResult(ResponseCode.OK, PageResponse.<UserVO>builder().total(userInfoList.size()).list(userInfoList).build());
    }


}
