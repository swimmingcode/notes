package org.youyuan.vhr.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.youyuan.vhr.bean.Hr;
import org.youyuan.vhr.bean.RespBean;
import org.youyuan.vhr.bean.Role;
import org.youyuan.vhr.service.HrService;
import org.youyuan.vhr.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    HrService hrService;

    @Autowired
    RoleService roleService;

    /**
     * 通过Hid获取Hr
     **/
    @GetMapping("/")
    public List<Hr> getHrByHid(String keywords) {
        return hrService.getHrByHid(keywords);
    }

    /**
     * 修改Hr状态
     **/
    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    /**
     * 查询所有角色
     **/
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 更新Hr的角色
     **/
    @PutMapping("/role")
    public RespBean updateHrRoles(Integer hrid, Integer[] rids) {
        if (roleService.updateHrRoles(hrid, rids)) {
            return RespBean.ok("更新HR角色成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 删除Hr
     **/
    @DeleteMapping("/{hrid}")
    public RespBean deleteHrByRid(@PathVariable Integer hrid) {
        if (hrService.deleteHrByRid(hrid) == 1) {
            return RespBean.ok("删除HR成功");
        }
        return RespBean.error("删除失败");
    }
}
