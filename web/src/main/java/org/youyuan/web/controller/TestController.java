package org.youyuan.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.web.bean.Person;
import org.youyuan.web.bean.User;

/**
 * @Describe
 * @Author youjiancheng
 * @Date 2021/1/25 14:33
 */
@RestController
@Slf4j
@RequestMapping("/json")
public class TestController {

    @GetMapping("/test")
    public void test() {
        String json = "{\"code\": 200,\"data\": {\"name\": \"zs\",\"age\": 20" + "}}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        Object data = jsonObject.get("data");
        User user = JSONObject.parseObject(data.toString(),new TypeReference<User>(){});
        log.info("{}",user);
        log.info("{}",data);

    }

    @GetMapping("/test1")
    public void test1() {
        User user = new User();
        Person person = new Person();

        //向上转型
        Person person1 = user;

        //向下转型
        User user1 = (User) person;
    }
}
