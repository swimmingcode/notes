package org.youyuan.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.youyuan.vhr.bean.JobLevel;

import java.util.List;

@MapperScan
public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    List<JobLevel> selectAll();

    int updateByPrimaryKey(JobLevel record);

    /**
     * 批量删除JobLevels
     **/
    int deleteMultipleJobLevel(@Param("ids") Integer[] ids);
}