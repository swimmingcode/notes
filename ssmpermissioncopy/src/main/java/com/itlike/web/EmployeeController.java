package com.itlike.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlike.domain.*;
import com.itlike.service.EmployeeService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequiresPermissions("employee:index")
    @RequestMapping("/employee")
    public String employee(){
        return "employee";
    }

    /*显示所有员工*/
    @ResponseBody
    @RequestMapping("/employeeList")
    public EmployeeListRes employeeList(QueryVo vo){
        return employeeService.employeeList(vo);
    }


    /*显示下拉框部门列表*/
    @RequestMapping("/department")
    @ResponseBody
    public List<Department> departmentList(){
        List<Department> departments = employeeService.departmentList();
        return departments;
    }

    /*添加员工*/
    @RequiresPermissions("employee:add")
    @ResponseBody
    @RequestMapping("/saveEmployee")
    public Ajax saveEmployee(Employee employee){
        return employeeService.saveEmployee(employee);
    }


    /*修改员工*/
    @RequiresPermissions("employee:edit")
    @ResponseBody
    @RequestMapping("/updateEmployee")
    public Ajax updateEmployee(Employee employee){
        return employeeService.updateEmployee(employee);
    }

    /*修改员工状态为离职*/
    @RequiresPermissions("employee:delete")
    @ResponseBody
    @RequestMapping("/updateEmployeeState")
    public Ajax updateEmployeeState(Long empId){
        return employeeService.updateEmployeeState(empId);
    }

    /*根据员工eid获取角色*/
    @RequestMapping("/getRoleByEmployeeEid")
    @ResponseBody
    public List<Long> getRoleByEmployeeEid(Long empId){
        return employeeService.getRoleByEmployeeEid(empId);
    }

    /*处理shiro异常*/
    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method, HttpServletResponse response) throws IOException {
        /*发生异常的方法*/
        /*跳转到一个界面 界面提示没有权限*/
        /*判断 当前请求是不是json请求 如果是 返回json给浏览器 让他自己来做跳转*/
        /*获取方法上的注解*/
        response.setCharacterEncoding("utf-8");
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation != null){
            Ajax ajaxRes = new Ajax(false, "你没有权限操作");
            String s = new ObjectMapper().writeValueAsString(ajaxRes);
            response.getWriter().print(s);
        }else {
            response.sendRedirect("nopermisssion.jsp");
        }
    }

    /*excel导出*/
    @ResponseBody
    @RequestMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response){
        QueryVo vo = new QueryVo();
        vo.setPage(1);
        vo.setRows(100);
        /*1.从数据库当中获取列表数据*/
        EmployeeListRes plr = employeeService.employeeList(vo);
        List<?> employees = plr.getRows();
        /*2.创建Excel 写到excel当中*/
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("员工数据");
        /*创建一行*/
        HSSFRow row = sheet.createRow(0);
        /*设置行的每一列的数据*/
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("用户名");
        row.createCell(2).setCellValue("入职日期");
        row.createCell(3).setCellValue("电话");
        row.createCell(4).setCellValue("邮件");
        row.createCell(5).setCellValue("状态");
        row.createCell(6).setCellValue("是否为管理员");
        row.createCell(7).setCellValue("部门");
        HSSFRow employeeRow = null;
        /*取出每一个员工来去设置数据*/
        for (int i = 0; i < employees.size(); i++) {
            Employee employee =(Employee) employees.get(i);
            employeeRow = sheet.createRow(i+1);
            employeeRow.createCell(0).setCellValue(employee.getEmpId());
            employeeRow.createCell(1).setCellValue(employee.getEmpUsername());
            /*入职时间格式化*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd");
            String format = simpleDateFormat.format(employee.getEmpInputtime());
            employeeRow.createCell(2).setCellValue(format);
            employeeRow.createCell(3).setCellValue(employee.getEmpTel());
            employeeRow.createCell(4).setCellValue(employee.getEmpEmail());
            /*状态*/
            if (employee.getEmpState()){
                employeeRow.createCell(5).setCellValue("在职");
            }else{
                employeeRow.createCell(5).setCellValue("离职");
            }
            /*是否为管理员*/
            if (employee.getEmpAdmin()){
                employeeRow.createCell(6).setCellValue("是");
            }else{
                employeeRow.createCell(6).setCellValue("否");
            }
            /*部门*/
            Integer empDepId = employee.getEmpDepId();
            if (empDepId == 1){
                employeeRow.createCell(7).setCellValue("技术部");
            } else if (empDepId == 2) {
                employeeRow.createCell(7).setCellValue("财务部");
            }else {
                employeeRow.createCell(7).setCellValue("人事部");
            }
        }

        /*响应给浏览*/
        try {
            String fileName = new String("员工数据.xls".getBytes("utf-8"), "iso8859-1");
            /*设置响应头*/
            response.setHeader("content-Disposition","attachment;filename="+fileName);
            wb.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

   /* 下载模板*/
    @ResponseBody
    @RequestMapping("/downloadExcelTpl")
    public void downloadExcelTpl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileInputStream inputStream = null;
        String fileName = null;
        try {
            fileName = new String("EmployeeTpl.xls".getBytes("utf-8"), "iso8859-1");
            response.setHeader("content-Disposition", "attachment;filename=" + fileName);
            /*获取文件路径*/
            String realPath = request.getSession().getServletContext().getRealPath("static/ExcelTml.xls");
            inputStream = new FileInputStream(realPath);
            IOUtils.copy(inputStream,response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                inputStream.close();
            }
        }

    }

    /*文件上传
    * 配置文件上传的解析器
    * mvc配置当中*/
    @ResponseBody
    @RequestMapping("/uploadExcelFile")
    public Ajax uploadExcelFile(MultipartFile excel){
        Ajax ajax = new Ajax();
        try {
            HSSFWorkbook wb = new HSSFWorkbook(excel.getInputStream());
            HSSFSheet sheet = wb.getSheetAt(0);
            /*获取最大的行号*/
            int lastRowNum = sheet.getLastRowNum();
            Row employeeRow = null;
            for (int i = 1; i <= lastRowNum; i++) {
                employeeRow = sheet.getRow(i);
                /*转化为对应的数据类型*/
                /*编号*/
                getCellValue(employeeRow.getCell(0));
                /*用户名*/
                String name = (String) getCellValue(employeeRow.getCell(1));
                /*入职日期*/
                String date = (String) getCellValue(employeeRow.getCell(2));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date parse = null;
                parse = sdf.parse(date);
                /*电话*/
                String tel = (String) getCellValue(employeeRow.getCell(3));
                /*邮件*/
                String email = (String) getCellValue(employeeRow.getCell(4));
                /*状态*/
                String state = (String) getCellValue(employeeRow.getCell(5));
                /*是否为管理员*/
                String admin = (String) getCellValue(employeeRow.getCell(6));
                /*部门*/
                String department = (String) getCellValue(employeeRow.getCell(7));

                Employee employee = new Employee();
                employee.setEmpUsername(name);
                employee.setEmpInputtime(parse);
                employee.setEmpTel(tel);
                employee.setEmpEmail(email);
                if (state.equals("在职")){
                    employee.setEmpState(true);
                }else {
                    employee.setEmpState(false);
                }
                if (admin.equals("是")){
                    employee.setEmpAdmin(true);
                }else {
                    employee.setEmpAdmin(false);
                }
                if (department.equals("技术部")){
                    employee.setEmpDepId(1);
                }else if(department.equals("财务部")){
                    employee.setEmpDepId(2);
                }else{
                    employee.setEmpDepId(3);
                }
                employeeService.saveEmployee(employee);
                ajax.setSuccess(true);
                ajax.setMessage("上传成功");
            }
        } catch (Exception e) {
            ajax.setSuccess(false);
            ajax.setMessage("上传失败");
            e.printStackTrace();
        }
        return ajax;
    }

    private Object getCellValue(Cell cell){
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN:

                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return cell;
    }
}
