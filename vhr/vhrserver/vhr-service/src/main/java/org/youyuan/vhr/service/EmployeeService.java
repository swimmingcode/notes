package org.youyuan.vhr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.vhr.bean.Employee;
import org.youyuan.vhr.bean.RespPageBean;
import org.youyuan.vhr.mapper.EmployeeMapper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 返回员工
     **/
    public RespPageBean getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, size, employee, beginDateScope);
        //查询员工的总记录数
        Long total = employeeMapper.getTotal(employee, beginDateScope);
        RespPageBean pageBean = new RespPageBean();
        pageBean.setTotal(total);
        pageBean.setData(employeeByPage);
        return pageBean;
    }

    /**
     * 添加员工
     * 计算工作年限
     **/
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    //发送消息队列
    @Autowired
    RabbitTemplate rabbitTemplate;

    //日志
    public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public int addEmployee(Employee employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double time = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 +
                (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(time / 12)));
        int result = employeeMapper.insert(employee);
        if (result == 1){
            //查询发送邮件员工的信息
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            logger.info(emp.toString());
            rabbitTemplate.convertAndSend("youyuan", emp);
        }
        return result;
    }

    /**
     * 获取最大工号
     **/
    public int getMaxWorkID() {
        return employeeMapper.getMaxWorkID();
    }


    /**
     * 删除员工
     **/
    public int deleteEmployee(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新员工
     **/
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateByPrimaryKey(employee);
    }

    /**
     * 获取员工账套
     */
    public RespPageBean getEmployeeByPageWithSalary(Integer page, Integer size) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> employees = employeeMapper.getEmployeeByPageWithSalary(page, size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(employees);
        respPageBean.setTotal(employeeMapper.getTotal(null, null));
        return respPageBean;
    }
}
