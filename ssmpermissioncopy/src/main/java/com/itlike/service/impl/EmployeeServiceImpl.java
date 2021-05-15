package com.itlike.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.domain.*;
import com.itlike.mapper.DepartmentMapper;
import com.itlike.mapper.EmployeeMapper;
import com.itlike.mapper.RoleMapper;
import com.itlike.service.EmployeeService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RoleMapper roleMapper;

    /*显示所有员工*/
    public EmployeeListRes employeeList(QueryVo vo) {
        /*设置分页*/
        Page<Object> objects = PageHelper.startPage(vo.getPage(),vo.getRows());
        /*查询所有*/
        List<Employee> employees = employeeMapper.selectAll(vo);
        /*返回给页面数据*/
        EmployeeListRes employeeListRes = new EmployeeListRes();
        employeeListRes.setTotal(objects.getTotal());
        employeeListRes.setRows(employees);
        return  employeeListRes;
    }

    /*/*显示下拉框部门列表*/
    @Override
    public List<Department> departmentList() {
        return departmentMapper.selectAll();
    }

    /*添加员工*/
    @Override
    public Ajax saveEmployee(Employee employee) {
        /* 密码加密操作,用户名当做盐，散列两次*/
        if (employee.getEmpPassword()!=null) {
            String password = employee.getEmpPassword();
            employee.setEmpPassword(new Md5Hash(password, employee.getEmpUsername(), 2).toString());
        }
        /*设置在职*/
        employee.setEmpState(true);
        /*设置部门*/
        if (employee.getDepartment()!=null) {
            employee.setEmpDepId(employee.getDepartment().getDepId());
        }
        try {
            employeeMapper.insert(employee);
            /*保存员工角色关系*/
            if (employee.getRoles()!=null) {
                for (int i = 0; i < employee.getRoles().size(); i++) {
                    employeeMapper.insertEmployeeRoleRel(employee.getEmpId(), employee.getRoles().get(i).getRid());
                }
            }
            Ajax ajax = new Ajax(true,"添加成功");
            return  ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false,"添加失败");
            e.printStackTrace();
            return  ajax;
        }
    }

    /*修改员工*/
    @Override
    public Ajax updateEmployee(Employee employee) {
        try {

            /*删除员工与角色关系*/
            roleMapper.deleteEmployeeRoleRelByEid(employee.getEmpId());
            /*建立员工与角色关系*/
            if (employee.getRoles() != null ){
                for (Role role :employee.getRoles() ) {
                    roleMapper.insertEmployeePermissionRel(employee.getEmpId(),role.getRid());
                }
            }

            employeeMapper.updateByPrimaryKey(employee);
            Ajax ajax = new Ajax(true,"添加成功");
            return  ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false,"添加失败");
            e.printStackTrace();
            return  ajax;
        }
    }

    /*修改员工状态为离职*/
    @Override
    public Ajax updateEmployeeState(Long empId) {
        try {
            Ajax ajax = new Ajax(true, "设置离职成功");
            employeeMapper.updateEmployeeState(empId);
            return ajax;
        } catch (Exception e) {
            Ajax ajax = new Ajax(false, "设置离职失败");
            e.printStackTrace();
            return ajax;
        }
    }

    /*根据员工eid获取角色*/
    @Override
    public List<Long> getRoleByEmployeeEid(Long empId) {
        try {
            return employeeMapper.getRoleByEmployeeEid(empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*根据员工姓名查询员工*/
    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeMapper.getEmployeeByUsername(username);
    }

    /*根据员工id查询所有角色*/
    @Override
    public ArrayList<String> getAllRolesByEid(Long empId) {
        return employeeMapper.getAllRolesByEid(empId);
    }

    /*根据员工id查询所有权限*/
    @Override
    public ArrayList<String> getAllPermissionsByEid(Long empId) {
        return employeeMapper.getAllPermissionsByEid(empId);
    }
}
