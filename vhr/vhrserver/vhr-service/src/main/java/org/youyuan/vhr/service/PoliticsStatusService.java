package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youyuan.vhr.bean.PoliticsStatus;
import org.youyuan.vhr.mapper.PoliticsStatusMapper;

import java.util.List;

@Service
public class PoliticsStatusService {
    @Autowired
    PoliticsStatusMapper politicsStatusMapper;

    /**
     * 返回所有政治面貌
     **/
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusMapper.selectAll();
    }
}
