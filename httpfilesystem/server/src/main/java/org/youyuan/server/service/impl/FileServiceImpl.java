package org.youyuan.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youyuan.server.domain.FilePO;
import org.youyuan.server.mapper.FileMapper;
import org.youyuan.server.service.FileService;

import java.util.List;
import java.util.Optional;

/**
 * @Describe: 文件Service
 * @Author: youjiancheng
 * @date 2021/4/6 15:28
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public void insertFile(FilePO filePO) {
        fileMapper.insert(filePO);
    }

    @Override
    public FilePO selectByFileName(String fileName) {
        QueryWrapper<FilePO> wrapper = new QueryWrapper();
        wrapper.eq("new_name", fileName);
        List<FilePO> filePOS = fileMapper.selectList(wrapper);
        Optional.ofNullable(filePOS).orElseThrow(() -> new RuntimeException("无此文件"));
        return filePOS.get(0);
    }
}
