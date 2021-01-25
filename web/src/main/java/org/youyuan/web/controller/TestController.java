package org.youyuan.web.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("{}",data);

    }
}
