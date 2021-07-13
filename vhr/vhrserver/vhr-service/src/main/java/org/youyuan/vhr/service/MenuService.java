package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.vhr.bean.Menu;
import org.youyuan.vhr.mapper.MenuMapper;
import org.youyuan.vhr.mapper.RoleMapper;
import org.youyuan.vhr.utils.HrUtils;

import java.util.List;

@Transactional
@Service
@CacheConfig(cacheNames = "youyuan")
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RoleMapper roleMapper;

    /**
     * 根据Hr的Id加载menu
     */
    //@CachePut(key = "#root.methodName")
    public List<Menu> getMenuByHrId() {
        Integer id = HrUtils.getHr().getId();
        return menuMapper.getMenuByHrId(id);
    }

    /**
     * 获取整个mune菜单与访问所需的角色roles
     * redis缓存
     **/
    @Cacheable(key = "#root.methodName")
    public List<Menu> getMenuWithRoles() {
        return menuMapper.getMenuWithRoles();
    }

    /**
     * 加载菜单权限
     **/
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    /**
     * 根据角色rid获取菜单id
     **/
    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    /**
     * 更新角色的Menu菜单
     **/
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        roleMapper.deleteByRid(rid);
        if (mids == null || mids.length == 0) {
            return true;
        }
        int record = roleMapper.insertRecord(rid, mids);
        return record == mids.length;
    }

}
