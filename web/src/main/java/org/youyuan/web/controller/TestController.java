package org.youyuan.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.web.bean.Person;
import org.youyuan.web.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping("/test2")
    public void test2() {
        log.info(System.getProperty("user.dir"));
    }

    @GetMapping("/test/encode")
    public void testEncode(HttpServletResponse response, HttpServletRequest request) {
        ArrayList<Person> people = new ArrayList<>();
        Person zs = new Person(1,"zs");
        Person ls = new Person(2,"ls");
        people.add(zs);
        people.add(ls);
        String msg = JSONObject.toJSONString(people);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data",msg);

        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(hashMap));

        List<Person> data = JSONObject.parseArray(jsonObject.getJSONArray("data").toString(), Person.class);
        System.out.println(data);

    }

//    private HashMap<Integer, Integer> hashMap = new HashMap<>();

    @GetMapping("/test/hashmap")
    public void testHash() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            int finalI1 = i;
            new Thread(()->{
                hashMap.put(finalI, finalI1);
            }).start();
        }

    }


}
