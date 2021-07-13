package org.youyuan.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.youyuan.vhr.bean.Salary;

import java.util.List;

public interface SalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Salary record);

    Salary selectByPrimaryKey(Integer id);

    List<Salary> selectAll();

    int updateByPrimaryKey(Salary record);

    /**
     * 删除员工原有的套账
     */
    void deleteSalaryWithEmployee(Integer eid);

    /**
     * 添加员工新的账套
     */
    int insertSalaryWithEmployee(@Param("eid") Integer eid, @Param("sid") Integer sid);
}