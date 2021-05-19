package org.youyuan.vhr.controller.system.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.youyuan.vhr.bean.Menu;
import org.youyuan.vhr.bean.RespBean;
import org.youyuan.vhr.bean.Role;
import org.youyuan.vhr.service.MenuService;
import org.youyuan.vhr.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    /**
     * 加载角色
     **/
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 加载三级树状菜单
     **/
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }


    /**
     * 根据角色rid获取菜单id
     **/
    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid) {
        return menuService.getMidsByRid(rid);
    }

    /**
     * 更新角色的菜单Menu
     **/
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        if (menuService.updateMenuRole(rid, mids)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 添加角色
     **/
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 删除角色
     **/
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleByRid(@PathVariable Integer rid) {
        if (roleService.deleteRoleByRid(rid) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.ok("删除失败");
    }
}
