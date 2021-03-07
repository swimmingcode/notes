package org.yoyuancode.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/2 23:09
 */
@Slf4j
@RestController
public class UploadController {

    private SimpleDateFormat simpleDateFormatImage = new SimpleDateFormat("/YYYY/MM/dd/");

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //文件名称
        String oldName = file.getOriginalFilename();
        //创建文件夹
        String path = request.getServletContext().getRealPath("/img");
        File folder = new File(path);
        if (!folder.exists()) {
            //递归 一层一层创建
            //mkdir 只有一级
            folder.mkdirs();
        }
        try {
            inputStream = file.getInputStream();
            byte[] bytes = new byte[1024];
            //文件路径
            String fileName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
            outputStream  = new FileOutputStream(new File(path , fileName));
            int len;
            while ((len = inputStream.read(bytes))!= -1) {
                outputStream.write(bytes);
            }
            log.info("文件路径为=" + path + fileName);
            // TODO: 2021/2/28 有问题
            String url= request.getScheme() + "://"+request.getServerName() +":" + request.getServerPort()+"/img/" + fileName;
            log.info(url);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
