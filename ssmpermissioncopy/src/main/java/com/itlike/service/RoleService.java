package com.itlike.service;

import com.itlike.domain.Ajax;
import com.itlike.domain.EmployeeListRes;
import com.itlike.domain.QueryVo;
import com.itlike.domain.Role;

import java.security.Permission;
import java.util.List;

public interface RoleService {

    /*获取所有角色列表*/
    EmployeeListRes getRoleList(QueryVo vo);

    /*保存角色*/
    Ajax saveRole(Role role);

    /*根据rid显示该角色的所有权限*/
    List<Permission> getRolePermissionByRid(Long rid);

    /*更新角色*/
    Ajax updateRole(Role role);

    /*删除角色*/
    Ajax deleteRoleByRid(Long rid);

    List<Role> roleListCombox();
}
