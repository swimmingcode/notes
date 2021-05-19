package org.youyuan.vhr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.youyuan.vhr.bean.Menu;
import org.youyuan.vhr.bean.Role;
import org.youyuan.vhr.service.MenuService;

import java.util.Collection;
import java.util.List;

/**
 * 这个类的功能，根据用户传来的请求地址，分析出请求需要的角色
 **/
@Component
public class CustomFilterInvocationSecurityMetadatabase implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        /**
         * 取整个mune菜单与访问所需的角色roles
         * 配置redis 缓存
         * */
        List<Menu> menuWithRoles = menuService.getMenuWithRoles();
        for (Menu menu : menuWithRoles) {
            //如果请求的地址与menu的url匹配上，获取请求url所需的角色
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> needRoles = menu.getRoles();
                String[] role = new String[needRoles.size()];
                for (int i = 0; i < role.length; i++) {
                    role[i] = needRoles.get(i).getName();
                }
                return SecurityConfig.createList(role);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
