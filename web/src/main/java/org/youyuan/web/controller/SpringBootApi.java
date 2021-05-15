package org.youyuan.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.web.bean.MyPerson;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/9 20:46
 */
@RestController
public class SpringBootApi {

    @Autowired
    private MyPerson myPerson;



    @GetMapping("/properties")
    public String test() {
        return this.myPerson.toString();
    }


}
