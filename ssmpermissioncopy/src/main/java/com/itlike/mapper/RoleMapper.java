package com.itlike.mapper;

import com.itlike.domain.Role;

import java.security.Permission;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    Role selectByPrimaryKey(Long rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    /*保存角色权限关系*/
    void insertRolePermissionRel(Long rid, Long pid);

    /*根据rid显示该角色的所有权限*/
    List<Permission>  getRolePermissionByRid(Long rid);

    /*打破角色与权限关系*/
    void deleteRolePermissionRel(Long rid);

    /*建立员工与角色关系*/
    void insertEmployeePermissionRel(Long empId, Long rid);

    /*删除员工角色关系 by Rid**/
    void deleteEmployeeRoleRel(Long rid);

    /*删除员工角色关系by Eid**/
    void deleteEmployeeRoleRelByEid(Long empId);
}