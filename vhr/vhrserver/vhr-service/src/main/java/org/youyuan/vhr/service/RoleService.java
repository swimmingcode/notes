package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.vhr.bean.Role;
import org.youyuan.vhr.mapper.RoleMapper;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    /**
     * 加载角色
     **/
    public List<Role> getAllRoles() {
        return roleMapper.selectAll();
    }


    /**
     * 添加角色
     **/
    public int addRole(Role role) {
        //添加前缀ROLE_
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insert(role);
    }

    /**
     * 删除角色
     **/
    @Transactional
    public int deleteRoleByRid(Integer rid) {
        //删除角色菜单关系
        roleMapper.deleteRoleWithMenu(rid);
        return roleMapper.deleteByPrimaryKey(rid);
    }


    /**
     * 更新Hr的角色
     **/
    @Transactional
    public Boolean updateHrRoles(Integer hrid, Integer[] rids) {
        //根据hrid删除Hr的role
        roleMapper.deleteRolesByHrid(hrid);
        if (rids == null || rids.length == 0) {
            return true;
        }
        //添加Hr的role
        return roleMapper.addHrRoles(hrid, rids) == rids.length;
    }


}
