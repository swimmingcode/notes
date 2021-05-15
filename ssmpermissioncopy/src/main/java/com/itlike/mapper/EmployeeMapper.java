package com.itlike.mapper;

import com.itlike.domain.Employee;
import com.itlike.domain.QueryVo;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeMapper {

    int deleteByPrimaryKey(Long empId);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long empId);

    List<Employee> selectAll(QueryVo vo);

    int updateByPrimaryKey(Employee record);

    /*修改员工状态为离职*/
    int updateEmployeeState(Long empId);

    /*保存员工角色关系*/
    void insertEmployeeRoleRel(Long empId, Long rid);

    /*根据员工eid获取角色*/
    List<Long> getRoleByEmployeeEid(Long empId);

    /*根据员工姓名查询员工*/
    Employee getEmployeeByUsername(String username);

    /*根据员工id查询所有角色*/
    ArrayList<String> getAllRolesByEid(Long empId);

    /*根据员工id查询所有权限*/
    ArrayList<String> getAllPermissionsByEid(Long empId);
}