package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.vhr.bean.JobLevel;
import org.youyuan.vhr.mapper.JobLevelMapper;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    /**
     * 获取JobLevels
     **/
    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.selectAll();
    }

    /**
     * 添加JobLevels
     **/
    public int addJobLevel(JobLevel jobLevel) {
        jobLevel.setCreateDate(new Date());
        //默认开启
        jobLevel.setEnabled(true);
        return jobLevelMapper.insert(jobLevel);
    }

    /**
     * 更新JobLevels
     **/
    public int updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKey(jobLevel);
    }

    /**
     * 删除JobLevels
     **/
    public int deleteJobLevel(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除JobLevels
     **/
    public int deleteMultipleJobLevel(Integer[] ids) {
        return jobLevelMapper.deleteMultipleJobLevel(ids);
    }
}
