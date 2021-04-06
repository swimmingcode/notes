package org.youyuan.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.youyuan.server.domain.FilePO;
import org.youyuan.server.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/6 15:07
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyy/MM/dd/");

    @Autowired
    FileService fileService;


    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest httpRequest) {
        //将上传的文件放入tomcat的临时目录下
        String realPath = httpRequest.getServletContext().getRealPath("/") + simpleDateFormat.format(new Date());
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //原文件名
        String oldName = file.getOriginalFilename();
        //新文件名称
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            file.transferTo(new File(realPath, newName));
        } catch (IOException e) {
            log.info("上传文件失败");
            throw new RuntimeException("上传文件失败");
        }
        //文件地址
        String url = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort() + simpleDateFormat.format(new Date()) + newName;
        //文件大小
        long size = file.getSize();
        //文件类型
        String contentType = file.getContentType();

        FilePO filePO = FilePO.builder()
                .createTime(new Date())
                .fileAddress(url)
                .oldName(oldName)
                .newName(newName)
                .size(size)
                .type(contentType)
                .build();

        //异步处理入库操作
        new Thread(() -> {
            fileService.insertFile(filePO);
        }).start();
        return new ResponseEntity(newName, HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<String> fileDownload(@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        //获取文件信息
        FilePO filePO = fileService.selectByFileName(fileName);
        String path = request.getServletContext().getRealPath("/") + simpleDateFormat.format(filePO.getCreateTime());
        File file = new File(path, fileName);
        InputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            //设置文件的以二进制流的形式响应
            response.setContentType("application/octet-stream");
            //以附件的形式下载
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            outputStream = response.getOutputStream();
            int len = -1;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            log.info("文件下载失败");
            e.printStackTrace();
            //返回异常401
            return new ResponseEntity("401", HttpStatus.OK);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回空
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<FilePO> getFileInfo(@RequestParam("fileName") String fileName) {
        FilePO filePO = fileService.selectByFileName(fileName);
        return new ResponseEntity<>(filePO, HttpStatus.OK);
    }
}
