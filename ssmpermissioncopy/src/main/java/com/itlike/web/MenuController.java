package com.itlike.web;

import com.itlike.domain.Ajax;
import com.itlike.domain.EmployeeListRes;
import com.itlike.domain.Menu;
import com.itlike.domain.QueryVo;
import com.itlike.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu")
    public String menu (){
        return "menu";
    }

    /*菜单列表*/
    @ResponseBody
    @RequestMapping("/menuList")
    public EmployeeListRes menuList(QueryVo vo){
        return menuService.menuList(vo);
    }


    /*加载目录结构*/
    @RequestMapping("/getTreeData")
    @ResponseBody
    public List<Menu> getTreeData(){
        return menuService.getTreeData();
    }

    /*添加菜单*/
    @RequestMapping("/saveMenu")
    @ResponseBody
    public Ajax saveMenu(Menu menu){
        return menuService.saveMenu(menu);
    }

    /*修改菜单*/
    @RequestMapping("/updateMenu")
    @ResponseBody
    public Ajax updateMenu(Menu menu){
        return menuService.updateMenu(menu);
    }

    /*删除菜单*/
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public Ajax deleteMenu(Long id){
        return menuService.deleteMenu(id);
    }

}
