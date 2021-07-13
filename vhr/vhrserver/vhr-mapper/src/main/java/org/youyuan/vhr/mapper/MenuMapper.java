package org.youyuan.vhr.mapper;

import org.youyuan.vhr.bean.Menu;

import java.util.List;

public interface MenuMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    /**
     * 根据Hr的Id加载menu,(父菜单与子菜单)
     */
    List<Menu> getMenuByHrId(Integer id);

    /**
     * 获取整个mune菜单与访问所需的角色roles
     * redis 缓存
     **/
    List<Menu> getMenuWithRoles();

    /**
     * 加载菜单权限
     **/
    List<Menu> getAllMenus();


    /**
     * 根据角色rid获取菜单id
     **/
    List<Integer> getMidsByRid(Integer rid);

}