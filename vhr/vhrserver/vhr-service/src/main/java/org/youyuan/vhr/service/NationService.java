package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youyuan.vhr.bean.Nation;
import org.youyuan.vhr.mapper.NationMapper;

import java.util.List;

@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;

    /**
     * 返回所有民族
     **/
    public List<Nation> getAllNations() {
        return nationMapper.selectAll();
    }
}
