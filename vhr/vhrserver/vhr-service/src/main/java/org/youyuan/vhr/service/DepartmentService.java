package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.vhr.bean.Department;
import org.youyuan.vhr.mapper.DepartmentMapper;

import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 查询部门菜单与他的子菜单
     **/
    public List<Department> getDepartmentWithChildren(Integer i) {
        return departmentMapper.getDepartmentWithChildren(i);
    }

    /**
     * 增加部门
     **/
    public void addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
    }

    /**
     * 删除部门
     **/
    public void deleteDepartment(Department dep) {
        departmentMapper.deleteDepartment(dep);
    }

    /**
     * 获取所有的部门
     */
    public List<Department> getAllDepartments() {
        return departmentMapper.selectAll();
    }
}
