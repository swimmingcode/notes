package org.youyuan.vhr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @CrossOrigin("*")
    @GetMapping("/login")
    public String login() {
        return "index";
    }
}
