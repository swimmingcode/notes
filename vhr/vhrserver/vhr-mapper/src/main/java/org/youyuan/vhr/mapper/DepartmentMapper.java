package org.youyuan.vhr.mapper;

import org.youyuan.vhr.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    Department selectByPrimaryKey(Integer id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);


    /**
     * 查询部门菜单与他的子菜单
     **/
    List<Department> getDepartmentWithChildren(Integer id);

    /**
     * 增加部门
     **/
    void addDepartment(Department department);


    /**
     * 删除部门
     **/
    void deleteDepartment(Department dep);
}