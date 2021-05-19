package org.youyuan.startertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.demo.CodeService;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/19 13:21
 */
@RestController
public class TestController {

    @Autowired
    CodeService codeService;

    @GetMapping("/test")
    public String test() {
        return codeService.hello();
    }
}
