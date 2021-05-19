package org.youyuan.vhr.controller.salary.sob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.youyuan.vhr.bean.RespBean;
import org.youyuan.vhr.bean.Salary;
import org.youyuan.vhr.service.SalaryService;

import java.util.List;

/**
 * @Author you猿
 * @Date 2020/7/15 10:31
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    public SalaryService salaryService;

    /**
     * 查询所有工资套账
     */
    @GetMapping("/")
    public List<Salary> getAllSalary() {
        return salaryService.getAllSalary();
    }

    /**
     * 添加工资套账
     */
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary) {
        if (salaryService.addSalary(salary) == 1) {
            return RespBean.ok("添加工资账套成功");
        }
        return RespBean.error("添加工资账套失败");

    }

    /**
     * 修改工资套账
     */
    @PutMapping("/")
    public RespBean updateSalaryById(@RequestBody Salary salary) {
        if (salaryService.updateSalary(salary) == 1) {
            return RespBean.ok("修改工资账套成功");
        }
        return RespBean.error("修改工资账套失败");
    }

    /**
     * 删除工资套账
     */
    @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable Integer id) {
        if (salaryService.deleteSalaryById(id) == 1) {
            return RespBean.ok("删除工资账套成功");
        }
        return RespBean.error("删除工资账套失败");
    }
}
