package com.itlike.web;


import com.itlike.domain.Ajax;
import com.itlike.domain.EmployeeListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;
import com.itlike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Permission;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*返回role.jsp*/
    @RequestMapping("/role")
    public String employee(){
        return "role";
    }

    /*获取所有角色列表*/
    @ResponseBody
    @RequestMapping("/roleList")
    public EmployeeListRes roleList(QueryVo vo){
        return roleService.getRoleList(vo);
    }

    /*保存角色*/
    @ResponseBody
    @RequestMapping("/saveRole")
    public Ajax saveRole(Role role){
        return roleService.saveRole(role);
    }

    /*根据rid显示该角色的所有权限*/
    @ResponseBody
    @RequestMapping("/getRolePermissionByRid")
    public List<Permission> getRolePermissionByRid(Long rid){
        return roleService.getRolePermissionByRid(rid);
    }

    /*更新角色*/
    @ResponseBody
    @RequestMapping("/updateRole")
    public Ajax updateRole(Role role){
        return roleService.updateRole(role);
    }

    /*删除角色*/
    @ResponseBody
    @RequestMapping("/deleteRoleByRid")
    public Ajax deleteRoleByRid(Long rid){
        return roleService.deleteRoleByRid(rid);
    }

    /*角色下拉框*/
    @ResponseBody
    @RequestMapping("/roleListCombox")
    public List<Role> roleListCombox(){
        return roleService.roleListCombox();
    }


}
