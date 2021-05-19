package org.youyuan.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.youyuan.vhr.bean.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    /**
     * 分页查询
     **/
    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    /**
     * 查询总记录数
     *
     * @param beginDateScope
     **/
    Long getTotal(@Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);

    /**
     * 获取最大工号
     **/
    int getMaxWorkID();

    /**
     * 获取员工账套
     */
    List<Employee> getEmployeeByPageWithSalary(@Param("page") Integer page, @Param("size") Integer size);

    /**
     * 查询发送邮件员工的信息
     * */
    Employee getEmployeeById(Integer id);
}