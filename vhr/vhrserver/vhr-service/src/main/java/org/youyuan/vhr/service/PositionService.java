package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.vhr.bean.Position;
import org.youyuan.vhr.mapper.PositionMapper;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PositionService {

    @Autowired
    PositionMapper positionMapper;


    /**
     * 获取所有职位
     **/
    public List<Position> getAllPositions() {
        return positionMapper.selectAll();
    }

    /**
     * 增加职位
     **/
    public int addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insert(position);
    }

    /**
     * 修改职位
     */
    public int updatePosition(Position position) {
        return positionMapper.updateByPrimaryKey(position);
    }


    /**
     * 删除职位
     */
    public int deletePosition(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }


    /**
     * 批量删除职位
     **/
    public int deletePositionByIds(Integer[] ids) {
        try {
            for (int i = 0; i < ids.length; i++) {
                positionMapper.deleteByPrimaryKey(ids[i]);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
