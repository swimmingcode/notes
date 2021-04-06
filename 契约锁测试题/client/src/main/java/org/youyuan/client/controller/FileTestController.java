package org.youyuan.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/6 18:06
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class FileTestController {

    public static final String uploadUrl = "http://localhost:8080/file/upload";

    public static final String downloadUrl = "http://localhost:8080/file/download";

    public static final String infoUrl = "http://localhost:8080/file/info";

    @Qualifier("restTemplate")
    @Autowired
    RestTemplate restTemplate;

    /**
     * 上传文件测试
     */
    @GetMapping("/upload")
    public void testUpload() {
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        //设置请求体
        FileSystemResource fileSystemResource = new FileSystemResource("E:\\Java\\图片\\图片"+"/"+ "1.png");
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        form.add("filename","1.png");
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        String res = restTemplate.postForObject(uploadUrl , files, String.class);
        log.info(res);
    }

    /**
     * 下载测试
     *
     * @param fileName
     */
    @GetMapping("/download")
    public void testFileDownload(@RequestParam("fileName") String fileName) {
        String res = restTemplate.getForObject(downloadUrl + "?fileName=" + fileName, String.class);
        log.info(res);
    }

    /**
     * 文件元数据信息
     * @param fileName
     */
    @GetMapping("/info")
    public void selectInfoByFileName(@RequestParam("fileName") String fileName) {
        String res = restTemplate.getForObject(infoUrl + "?fileName=" + fileName, String.class);
        log.info(res);
    }


}
