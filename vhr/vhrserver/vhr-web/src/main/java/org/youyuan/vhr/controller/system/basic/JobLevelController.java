package org.youyuan.vhr.controller.system.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.youyuan.vhr.bean.JobLevel;
import org.youyuan.vhr.bean.RespBean;
import org.youyuan.vhr.service.JobLevelService;

import java.util.List;

@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Autowired
    JobLevelService jobLevelService;

    /**
     * 获取JobLevels
     **/
    @GetMapping("/")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    /**
     * 添加JobLevels
     **/
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.addJobLevel(jobLevel) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 更新JobLevels
     **/
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel) {
        if (jobLevelService.updateJobLevel(jobLevel) == 1) {
            return RespBean.ok("修改成功");
        }
        return RespBean.ok("修改失败");
    }

    /**
     * 删除JobLevels
     **/
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id) {
        if (jobLevelService.deleteJobLevel(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.ok("删除成功");
    }

    /**
     * 批量删除JobLevels
     **/
    @DeleteMapping("/")
    public RespBean deleteMultipleJobLevel(Integer[] ids) {
        if (jobLevelService.deleteMultipleJobLevel(ids) == ids.length) {
            return RespBean.ok("删除成功");
        }
        return RespBean.ok("删除成功");
    }

}
