package org.youyuan.whitelisttest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.youyuan.whitelisttest.bean.Cdrom;
import org.youyuan.whitelisttest.bean.CifsDto;
import org.youyuan.whitelisttest.bean.VirtualMachineBackup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/19 15:50
 */
@Slf4j
@RestController
public class VirtualMachineHttpTest {

    @PostMapping("/vmbackups")
    public Map test1(@RequestBody VirtualMachineBackup virtualMachineBackup) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("taskId", UUID.randomUUID().toString().replace("_", "").replace("-", "").substring(0, 10));
        return hashMap;
    }

    @DeleteMapping(value = "/vmbackups/{id}")
    public Map test2(@PathVariable String id) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("taskId", "159368");
        return hashMap;
    }

    @GetMapping("/schedulers/{id}")
    public Map test3(@PathVariable String id) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("state", "已完成");
        hashMap.put("type", "ONCE");
        hashMap.put("aa", "111");
        return hashMap;
    }

    @GetMapping("/vms/{id}")
    public Map test4() {
        HashMap hashMap = new HashMap();
        hashMap.put("hostIp", "127.0.0.1");
        Cdrom value = new Cdrom();
        CifsDto cifsDto = new CifsDto();
        cifsDto.setPassword("123");
        cifsDto.setUserName("root");
        value.setCifsDto(cifsDto);
        hashMap.put("cdrom", value);
        return hashMap;
    }

    @PutMapping("/vms/{id}")
    public Map test5(@PathVariable String id) {
        log.info("{}", id);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", UUID.randomUUID().toString().replace("_", "").replace("-", "").substring(0, 10));
        return hashMap;
    }

    @PostMapping("/vmtemplates")
    public Map test6(@RequestParam(value = "vmId") String vmId,
                     @RequestParam(value = "templateName") String templateName,
                     @RequestParam(value = "templateDesc") String templateDesc) {
        System.out.println(vmId);
        System.out.println(templateName);
        System.out.println(templateDesc);
        HashMap hashMap = new HashMap();
        hashMap.put("taskId", UUID.randomUUID().toString().replace("_", "").replace("-", "").substring(0, 10));
        return hashMap;
    }
}
