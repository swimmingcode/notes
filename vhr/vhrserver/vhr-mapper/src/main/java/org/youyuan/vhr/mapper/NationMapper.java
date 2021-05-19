package org.youyuan.vhr.mapper;

import java.util.List;

import org.youyuan.vhr.bean.Nation;

public interface NationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nation record);

    Nation selectByPrimaryKey(Integer id);

    List<Nation> selectAll();

    int updateByPrimaryKey(Nation record);
}