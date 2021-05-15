package com.itlike.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private Long empId;

    private String empUsername;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date empInputtime;

    private String empTel;

    private String empEmail;

    private Boolean empState;

    private Boolean empAdmin;

    private Integer empDepId;

    /*部门*/
    private Department department;

    private String empPassword;

    /*所拥有的角色*/
    private List<Role> roles;
}