package org.youyuan.vhr.controller.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.youyuan.vhr.bean.*;
import org.youyuan.vhr.service.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    NationService nationService;

    @Autowired
    PoliticsStatusService politicsStatusService;

    @Autowired
    JobLevelService jobLevelService;

    @Autowired
    PositionService positionService;

    @Autowired
    DepartmentService departmentService;

    /**
     * 返回员工
     **/
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                          Employee employee, Date[] beginDateScope, String name) {
        return employeeService.getEmployeeByPage(page, size, employee, beginDateScope);
    }

    /**
     * json格式
     * {"name": "youyuan", "gender": "男","birthday": "1990-01-01", "idCard": "610122199001011256", "wedlock": "已婚",
     * "nationId": 1, "nativePlace": "陕西", "politicId": 13,"email": "laowang@qq.com","phone": "18565558897",
     * "address": "深圳市南山区", "departmentId": 5, "jobLevelId": 9, "posId": 29, "engageForm": "劳务合同",
     * "tiptopDegree": "本科", "specialty": "信息管理与信息系统", "school": "深圳大学", "beginDate": "2018-01-01",
     * "workState": "在职", "workID": "00000057","contractTerm": 2.0, "conversionTime": "2018-04-01",
     * "notWorkDate": null, "beginContract": "2018-01-01","endContract": "2020-01-01", "workAge": null,"nationId":1,
     * "politicId":13,"departmentId":5,"jobLevelId":9,"posId":29}
     **/

    /**
     * 添加员工
     **/
    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee) {
        if (employeeService.addEmployee(employee) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 返回所有民族
     **/
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    /**
     * 返回所有政治面貌
     **/
    @GetMapping("/politicsstatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.getAllPoliticsStatus();
    }

    /**
     * 返回所有职称
     **/
    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    /**
     * 获取所有职位
     **/
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    /**
     * 获取最大工号
     **/
    @GetMapping("/maxWorkID")
    public RespBean getMaxWorkID() {
        //输出结果格式化
        return RespBean.build().setStatus(200).setObj(String.format("%08d", employeeService.getMaxWorkID() + 1));
    }

    /**
     * 查询部门菜单与他的子菜单
     **/
    @GetMapping("/deps")
    public List<Department> getAllDepartments() {
        return departmentService.getDepartmentWithChildren(-1);
    }

    /**
     * 删除员工
     **/
    @DeleteMapping("/{id}")
    public RespBean deleteEmployee(@PathVariable Integer id) {
        if (employeeService.deleteEmployee(id) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 更新员工
     **/
    @PutMapping("/")
    public RespBean updateEmployee(@RequestBody Employee employee) {
        if (employeeService.updateEmployee(employee) == 1) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

}
