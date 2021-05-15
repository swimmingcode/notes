package com.itlike.mapper;

import com.itlike.domain.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    /*加载目录结构*/
    List<Menu> getALlTreeData();

    /*查询所有子菜单*/
    List<Menu> selectMenuChild();

    /*添加菜单*/
    void insertMenu(Menu menu);

    /*删除其子菜单*/
    void deleteChildrenMenu(Long id);

    /*取出自己的父菜单id*/
    Long getParentId(Long parentId);
}