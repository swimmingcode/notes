package com.itlike.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.domain.*;
import com.itlike.mapper.RoleMapper;
import com.itlike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Permission;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    /*获取所有角色列表*/
    @Override
    public EmployeeListRes getRoleList(QueryVo vo) {

        /*设置分页*/
        Page<Object> objects = PageHelper.startPage(vo.getPage(),vo.getRows());

        /*查询所有*/
        List<Role> roles = roleMapper.selectAll();
        /*返回给页面数据*/
        EmployeeListRes employeeListRes = new EmployeeListRes();
        employeeListRes.setTotal(objects.getTotal());
        employeeListRes.setRows(roles);
        return  employeeListRes;

    }

    /*保存角色*/
    @Override
    public Ajax saveRole(Role role) {
        try {
            /*保存角色*/
            roleMapper.insert(role);
            /*保存角色权限关系*/
            if (role.getPermissions()!=null) {
                for (int i = 0; i < role.getPermissions().size(); i++) {
                    roleMapper.insertRolePermissionRel(role.getRid(), role.getPermissions().get(i).getPid());
                }
            }
            Ajax ajax = new Ajax(true,"添加角色成功");
            return  ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false,"添加角色失败");
            e.printStackTrace();
            return  ajax;
        }
    }


    /*根据rid显示该角色的所有权限*/
    @Override
    public List<Permission> getRolePermissionByRid(Long rid) {
        try {
            return roleMapper.getRolePermissionByRid(rid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    /*更新角色*/
    @Override
    public Ajax updateRole(Role role) {
        try {
            /*打破角色权限关系*/
            roleMapper.deleteRolePermissionRel(role.getRid());

            /*重新添加角色权限关系*/
            if (role.getPermissions()!=null){
                for (int i = 0; i < role.getPermissions().size(); i++) {
                    roleMapper.insertRolePermissionRel(role.getRid(),role.getPermissions().get(i).getPid());
                }
            }

            /*更新角色*/
            roleMapper.updateByPrimaryKey(role);
            Ajax ajax = new Ajax(true,"修改角色成功");
            return ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false,"修改角色失败");
            e.printStackTrace();
            return  ajax;
        }
    }

    @Override
    public Ajax deleteRoleByRid(Long rid) {
        try {
            /*删除角色与权限的关系*/
            roleMapper.deleteRolePermissionRel(rid);

            /*删除员工角色关系**/
            roleMapper.deleteEmployeeRoleRel(rid);

            /*删除角色*/
            try {
                roleMapper.deleteByPrimaryKey(rid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Ajax ajax = new Ajax(true,"删除角色成功");
            return  ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false,"删除角色失败");
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<Role> roleListCombox() {
        return roleMapper.selectAll();
    }
}
