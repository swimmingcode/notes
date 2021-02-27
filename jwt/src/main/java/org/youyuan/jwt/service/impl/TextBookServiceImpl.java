package org.youyuan.jwt.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.youyuan.jwt.service.TextBookService;
import org.youyuan.jwt.utils.common.DateUtils;
import org.youyuan.jwt.utils.common.web.GlobalHttpUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/27 15:59
 */
@Service
@Slf4j
public class TextBookServiceImpl implements TextBookService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void upload(MultipartFile file) {
        HttpServletRequest globalHttpRequest = GlobalHttpUtils.getGlobalHttpRequest();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //文件名称
        String format = DateUtils.dateImageFolder(new Date());
        String oldName = file.getOriginalFilename();

        //创建文件夹
        String path = System.getProperty("user.dir") + File.separator + "jwt" + File.separator + "image" + format;
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
            String fileName =  UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
            outputStream  = new FileOutputStream(new File(path , fileName));
            log.info(path + fileName);

//            redisTemplate.opsForValue().set("");

            int len;
            while ((len = inputStream.read(bytes))!= -1) {
                outputStream.write(bytes);
            }
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

    }
}
