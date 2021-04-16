package org.youyuan.server.service;


import org.youyuan.server.domain.FilePO;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/6 15:28
 */
public interface FileService {
    /**
     * 添加问价信息
     *
     * @param filePO
     */
    void insertFile(FilePO filePO);

    /**
     * 通过名称查询文件信息
     *
     * @param fileName 文件名
     * @return
     */
    FilePO selectByFileName(String fileName);

}
