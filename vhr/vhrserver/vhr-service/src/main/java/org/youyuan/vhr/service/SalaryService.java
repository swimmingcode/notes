package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.vhr.bean.Salary;
import org.youyuan.vhr.mapper.SalaryMapper;

import java.util.Date;
import java.util.List;

/**
 * @Author you猿
 * @Date 2020/7/15 10:35
 */
@Service
@Transactional
public class SalaryService {

    @Autowired
    public SalaryMapper salaryMapper;

    /**
     * 查询所有工资套账
     */
    public List<Salary> getAllSalary() {
        return salaryMapper.selectAll();
    }

    /**
     * 添加工资套账
     */
    public int addSalary(Salary salary) {
        salary.setCreatedate(new Date());
        return salaryMapper.insert(salary);
    }

    /**
     * 修改工资套账
     */
    public int updateSalary(Salary salary) {
        salary.setCreatedate(new Date());
        return salaryMapper.updateByPrimaryKey(salary);
    }

    /**
     * 删除工资套账
     */
    public int deleteSalaryById(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改员工账套
     *
     * @param eid
     * @param sid
     */
    public int updateEmployeeSalary(Integer eid, Integer sid) {
        //删除员工原有的套账
        salaryMapper.deleteSalaryWithEmployee(eid);
        if (sid == null)
            return 1;
        //添加员工新的账套
        return salaryMapper.insertSalaryWithEmployee(eid, sid);
    }
}
