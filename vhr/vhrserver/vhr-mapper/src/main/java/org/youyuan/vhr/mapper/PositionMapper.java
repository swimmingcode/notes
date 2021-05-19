package org.youyuan.vhr.mapper;

import java.util.List;

import org.youyuan.vhr.bean.Position;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    Position selectByPrimaryKey(Integer id);

    List<Position> selectAll();

    int updateByPrimaryKey(Position record);
}