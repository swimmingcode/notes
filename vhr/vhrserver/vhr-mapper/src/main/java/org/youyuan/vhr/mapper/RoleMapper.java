package org.youyuan.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.youyuan.vhr.bean.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    /**
     * 根据rid删除role_menu中的角色
     **/
    int deleteByRid(Integer rid);

    /**
     * 添加角色菜单关系
     **/
    int insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mid);

    /**
     * 根据hrid删除Hr的role
     **/
    void deleteRolesByHrid(Integer hrid);

    /**
     * 添加Hr的role
     **/
    int addHrRoles(@Param("hrid") Integer hrid, @Param("rids") Integer[] rids);

    /**
     * 删除角色菜单关系
     */
    void deleteRoleWithMenu(Integer rid);
}