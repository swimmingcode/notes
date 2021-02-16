package org.youyuan.jwt.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.service.UserService;
import org.youyuan.jwt.utils.common.response.Response;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.common.response.ResponseFactory;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.utils.jwt.annotation.UnLogin;
import org.youyuan.jwt.vo.response.UserAccountVO;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

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
    RedisTemplate<String,Object> redisTemplate;

    @UnLogin
    @ApiResponses({
            @ApiResponse(code = 200,message = "成功")
    })
    @ApiOperation(value = "登录接口",notes = "返回Token")
    @PostMapping("/account/login")
    public Response<String> login(@ApiIgnore  @org.youyuan.jwt.utils.jwt.annotation.Token Token tokenDetail,
                                  @ApiParam(value = "用户名/密码") @RequestBody @Valid UserAccountVO userAccountVO,
                                  HttpServletResponse response) {
        UserPO userPO = userService.verifyUserNameAndPassword(userAccountVO.getUsername(), userAccountVO.getPassword());
        //生成Token
        String token = userService.login(Token.builder().name(userPO.getName()).id(userPO.getId()).build());
        //放入cookie当中
        tokenService.setCookie(token,response);
       return ResponseFactory.productResult(ResponseCode.OK,token);
    }

    @UnLogin
    @ApiResponse(code = 200,message = "成功")
    @ApiOperation(value = "鉴权Token接口",notes = "返回用户信息")
    @GetMapping("/verify/token")
    public Response<Token> tokenVerify(@ApiParam(value = "Token字符串") @Valid @NotEmpty @RequestParam(value = "token") String token) {
        Token tokenRes = tokenService.verifyToken(token);
        return ResponseFactory.productResult(ResponseCode.OK,tokenRes);
    }

    @ApiResponse(code = 200,message = "成功")
    @ApiOperation(value = "创建用户账号")
    @PostMapping("/create")
    public Response<UserAccountVO> createAccount() {
        UserAccountVO userAccount = userService.createAccount();
        return ResponseFactory.productResult(ResponseCode.OK,userAccount);
    }

    @ApiResponse(code = 200,message = "成功")
    @ApiOperation(value = "退出账号")
    @PostMapping("/account/logout")
    public Response<Void> logoutAccount(@ApiParam(value = "账户名称") @RequestParam(value = "username") String username) {
        userService.logoutAccount(username);
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }


    @ApiResponse(code = 200,message = "成功")
    @ApiOperation(value = "修改用户密码")
    @PutMapping("/update/account/password")
    public Response<Void> updateAccountPassword(@ApiParam(value = "账户名称") @RequestParam(value = "username") String username) {
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }

    @ApiResponse(code = 200,message = "成功")
    @ApiOperation(value = "修改用户名")
    @PutMapping("/update/account/username")
    public Response<Void> updateAccountUserName(@ApiParam(value = "账户名称") @RequestParam(value = "username") String username) {
        //名称唯一
        return ResponseFactory.<Void>productEmptyResult(ResponseCode.OK);
    }



//    @UnLogin
//    @GetMapping("/redis")
//    public Response<UserAccountVO> createAccount1() {
//        ArrayList<String> objects = new ArrayList<>();
//        objects.add("1");
//        objects.add("2");
//        redisTemplate.opsForValue().set("k",objects);
//        ArrayList k = (ArrayList) redisTemplate.opsForValue().get("k");
//        log.info("{}",k);
//        return ResponseFactory.productEmptyResult(ResponseCode.OK);
//    }



}
