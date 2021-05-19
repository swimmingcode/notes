package org.youyuan.vhr.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class Employee implements Serializable {
    private Integer id;

    private String name;

    private String gender;

    @JsonFormat(pattern = "yyy-MM-dd", timezone = "Asia/Shanghai")
    private Date birthday;

    private String idCard;

    private String wedlock;

    private Integer nationId;

    private String nativePlace;

    private Integer politicId;

    private String email;

    private String phone;

    private String address;

    private Integer departmentId;

    private Integer jobLevelId;

    private Integer posId;

    private String engageForm;

    private String tiptopDegree;

    private String specialty;

    private String school;

    @JsonFormat(pattern = "yyy-MM-dd", timezone = "Asia/Shanghai")
    private Date beginDate;

    private String workState;

    private String workID;

    private Double contractTerm;

    @JsonFormat(pattern = "yyy-MM-dd", timezone = "Asia/Shanghai")
    private Date conversionTime;

    @JsonFormat(pattern = "yyy-MM-dd", timezone = "Asia/Shanghai")
    private Date notWorkDate;

    @JsonFormat(pattern = "yyy-MM-dd", timezone = "Asia/Shanghai")
    private Date beginContract;

    @JsonFormat(pattern = "yyy-MM-dd", timezone = "Asia/Shanghai")
    private Date endContract;

    private Integer workAge;

    //民族
    private Nation nation;

    //政治面貌
    private PoliticsStatus politicsStatus;

    //部门
    private Department department;

    //工作职称
    private JobLevel jobLevel;

    //工作职位
    private Position position;

    //工资账套
    private Salary salary;
}