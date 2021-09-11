package org.yoyuancode.upload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yoyuancode.upload.async.Task;
import org.yoyuancode.upload.bean.Car;
import org.yoyuancode.upload.bean.MyUser;

import java.net.CacheRequest;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/10 20:13
 */
@RestController
public class TestController {

    @Autowired
    Car car;

    @Autowired
    Task task;

    @Autowired
    private MyUser myUser;

    @GetMapping("/properties1")
    public String test1() {
        task.task();
        return this.myUser.toString();
    }

    @GetMapping("/car")
    public String getCar() {
        return car.toString();
    }


}
