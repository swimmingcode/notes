package org.youyuan.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.jwt.utils.jwt.JwtUtils;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/1 14:27
 */
@RestController
public class LoginController {

    @GetMapping("/test")
    public void test() {
        String jwt = new JwtUtils().createJWT("123");
        System.out.println(new JwtUtils().parseJWT(jwt));
    }

}
