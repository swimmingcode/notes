package com.itlike.web;

import com.itlike.domain.Permission;
import com.itlike.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionContrller {

    @Autowired
    private PermissionService permissionService;

    /*获取所有权限*/
    @RequestMapping("/permissionList")
    @ResponseBody
    public List<Permission> permissionList(){
        return  permissionService.permissionList();
    }
}
