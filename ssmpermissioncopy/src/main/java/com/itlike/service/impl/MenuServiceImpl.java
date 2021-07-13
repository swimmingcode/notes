package com.itlike.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.domain.*;
import com.itlike.mapper.MenuMapper;
import com.itlike.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    /*加载目录结构*/
    public List<Menu> getTreeData() {
        List<Menu> aLlTreeData = menuMapper.getALlTreeData();
        /*获取当前主体*/
        Employee principal = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (!principal.getEmpAdmin()){
            /*过滤菜单，如何不是其所拥有的权限就去除掉*/
            checkPermission(aLlTreeData);
        }
        return aLlTreeData;
    }

    private void checkPermission(List<Menu> aLlTreeData) {
        /*获取当前主体*/
        Subject subject = SecurityUtils.getSubject();
        Iterator<Menu> iterator = aLlTreeData.iterator();
        /*遍历所有的菜单及子菜单*/
        while (iterator.hasNext()){
            Menu menu = iterator.next();
            if (menu.getPermission() != null){
                /*判断当前menu是否有权限对象 ，如果没有，就从集合中删除*/
                String presource = menu.getPermission().getPresource();
                if (!subject.isPermitted(presource)){
                    //移除
                    iterator.remove();
                    continue;
                }
            }
            if (menu.getChildren().size()>0){
                checkPermission(menu.getChildren());
            }
        }
    }

    /*菜单列表*/
    @Override
    public  EmployeeListRes menuList(QueryVo vo) {
        /*设置分页*/
        Page<Object> objects = PageHelper.startPage(vo.getPage(),vo.getRows());
        /*查询所有*/
        List<Menu> menus = menuMapper.selectAll();
        /*返回给页面数据*/
        EmployeeListRes employeeListRes = new EmployeeListRes();
        employeeListRes.setTotal(objects.getTotal());
        employeeListRes.setRows(menus);
        return  employeeListRes;

    }

    /*添加菜单*/
    @Override
    public Ajax saveMenu(Menu menu) {
        try {
            try {
                menuMapper.insertMenu(menu);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Ajax ajax = new Ajax(true, "添加菜单成功");
            return ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false, "添加菜单失败");
            e.printStackTrace();
            return ajax;
        }
    }

    @Override
    public Ajax updateMenu(Menu menu) {
        /*
        *  判断 不能设置自己的子菜单为自己的父菜单
        * */
        Long parentId = menu.getParent().getId();
        /*取出自己的父菜单id*/
        Long id = menuMapper.getParentId(parentId);
        /*判断*/
        if (menu.getId() == id){
            Ajax ajax = new Ajax(false, "不能设置自己的子菜单为自己的父菜单");
            return ajax;
        }
        try {
            menuMapper.updateByPrimaryKey(menu);
            Ajax ajax = new Ajax(true, "修改菜单成功");
            return ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false, "修改菜单失败");
            e.printStackTrace();
            return ajax;
        }
    }

    /*删除菜单*/
    @Override
    public Ajax deleteMenu(Long id) {
        try {
            /*删除其子菜单*/
            menuMapper.deleteChildrenMenu(id);
            menuMapper.deleteByPrimaryKey(id);
            Ajax ajax = new Ajax(true, "删除菜单成功");
            return ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false, "删除菜单失败");
            e.printStackTrace();
            return ajax;
        }
    }
}
