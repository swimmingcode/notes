package com.itlike.service;

import com.itlike.domain.Ajax;
import com.itlike.domain.EmployeeListRes;
import com.itlike.domain.Menu;
import com.itlike.domain.QueryVo;

import java.util.List;

public interface MenuService {

    /*加载目录结构*/
    List<Menu> getTreeData();

    /*菜单列表*/
    EmployeeListRes menuList(QueryVo vo);

    /*添加菜单*/
    Ajax saveMenu(Menu menu);

    /*修改菜单*/
    Ajax updateMenu(Menu menu);

    /*删除菜单*/
    Ajax deleteMenu(Long id);
}
