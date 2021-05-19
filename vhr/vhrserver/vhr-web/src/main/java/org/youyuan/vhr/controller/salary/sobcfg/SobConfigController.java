package org.youyuan.vhr.controller.salary.sobcfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.youyuan.vhr.bean.RespBean;
import org.youyuan.vhr.bean.RespPageBean;
import org.youyuan.vhr.bean.Salary;
import org.youyuan.vhr.service.EmployeeService;
import org.youyuan.vhr.service.SalaryService;

import java.util.List;

/**
 * @Author you猿
 * @Date 2020/7/16 11:29
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    public SalaryService salaryService;

    /**
     * 获取员工账套
     */
    @GetMapping("/")
    public RespPageBean getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeByPageWithSalary(page, size);
    }

    /**
     * 获取所有账套
     */
    @GetMapping("/salaries")
    public List<Salary> getAllSalary() {
        return salaryService.getAllSalary();
    }

    /**
     * 修改员工账套
     */
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid) {
        if (salaryService.updateEmployeeSalary(eid, sid) == 1) {
            return RespBean.ok("修改员工账套成功");
        }
        return RespBean.error("修改员工账套失败");
    }
}
