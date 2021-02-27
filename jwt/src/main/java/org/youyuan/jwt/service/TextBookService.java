package org.youyuan.jwt.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/27 15:59
 */
public interface TextBookService {

    /**
     * 文件存储
     *
     * @param file
     */
    void upload(MultipartFile file);
}
