package org.youyuan.vhr.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youyuan.vhr.bean.Menu;
import org.youyuan.vhr.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    /**
     * 根据Hr的Id加载menu
     */
    @GetMapping("/menu")
    public List<Menu> getMenuByHrId() {
        return menuService.getMenuByHrId();
    }
}
