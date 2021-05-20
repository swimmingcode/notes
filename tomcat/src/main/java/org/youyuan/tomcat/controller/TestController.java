package org.youyuan.tomcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/19 17:52
 */
@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("msg","code");
        return "success";
    }
}
