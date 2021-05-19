package org.youyuan.vhr.mapper;

import org.youyuan.vhr.bean.PoliticsStatus;

import java.util.List;

public interface PoliticsStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliticsStatus record);

    PoliticsStatus selectByPrimaryKey(Integer id);

    List<PoliticsStatus> selectAll();

    int updateByPrimaryKey(PoliticsStatus record);
}