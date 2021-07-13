package com.itlike.web.realm;

import com.itlike.domain.Employee;
import com.itlike.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;

public class EmployeeRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private EmployeeService employeeService;

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*获取此时登录的用户名*/
        String username = (String) token.getPrincipal();
        System.out.println(username);
        /*判断数据库中是否有该用户*/
        Employee employee = employeeService.getEmployeeByUsername(username);
        /*如果用户为空*/
        if ( employee == null) {
            return null;
        }

        /*参数：主体，正确的密码，盐，当前realm的名称*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                employee,
                employee.getEmpPassword(),
                /*盐*/
                ByteSource.Util.bytes(employee.getEmpUsername()),
                this.getName());
        return info;
    }

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        /*获取员工主体*/
        Employee employee = (Employee) token.getPrimaryPrincipal();
        /*声名角色集合*/
        ArrayList<String> roles = new ArrayList<>();
        /*声明权限集合*/
        ArrayList<String> permissions = new ArrayList<>();

        /*判断是否为管理员*/
        if (employee.getEmpAdmin()){
           permissions.add("*:*");
        }else {
            /*查询所有角色*/
            roles = employeeService.getAllRolesByEid(employee.getEmpId());
            /*查询所有权限*/
            permissions = employeeService.getAllPermissionsByEid(employee.getEmpId());
        }
        System.out.println(permissions);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }


}
