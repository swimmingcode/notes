package org.youyuan.vhr.controller.system.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.youyuan.vhr.bean.Position;
import org.youyuan.vhr.bean.RespBean;
import org.youyuan.vhr.service.PositionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    PositionService positionService;

    /**
     * 获取所有职位
     **/
    @GetMapping("/")
    public List<Position> getAllPositions() {
        List<Position> allPositions = positionService.getAllPositions();
        return allPositions;
    }

    /**
     * 增加职位
     **/
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        if (positionService.addPosition(position) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 修改职位
     **/
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updatePosition(position) == 1) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    /**
     * 删除职位
     **/
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if (positionService.deletePosition(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 批量删除职位
     **/
    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids) {
        if (positionService.deletePositionByIds(ids) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");

    }

}
