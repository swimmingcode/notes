package com.itlike.mapper;

import com.itlike.domain.Department;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer depId);

    int insert(Department record);

    Department selectByPrimaryKey(Integer depId);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
}