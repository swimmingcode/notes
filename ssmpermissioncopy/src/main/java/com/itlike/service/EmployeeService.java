package com.itlike.service;


import com.itlike.domain.*;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {

    /*显示所有员工*/
    EmployeeListRes employeeList(QueryVo vo);

    /*/*显示下拉框部门列表*/
    List<Department> departmentList();

    /*添加员工*/
    Ajax saveEmployee(Employee employee);

    /*修改员工*/
    Ajax updateEmployee(Employee employee);

    /*修改员工状态为离职*/
    Ajax updateEmployeeState(Long empId);

    /*根据员工eid获取角色*/
    List<Long> getRoleByEmployeeEid(Long empId);

    /*根据员工姓名查询员工*/
    Employee getEmployeeByUsername(String username);

    /*根据员工id查询所有角色*/
    ArrayList<String> getAllRolesByEid(Long empId);

    /*根据员工id查询所有权限*/
    ArrayList<String> getAllPermissionsByEid(Long empId);
}
