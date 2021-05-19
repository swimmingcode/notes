package org.youyuan.vhr.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Salary implements Serializable {
    private Integer id;

    private Integer basicsalary;

    private Integer bonus;

    private Integer lunchsalary;

    private Integer trafficsalary;

    private Integer allsalary;

    private Integer pensionbase;

    private Float pensionper;

    @JsonFormat(pattern = "yyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createdate;

    private Integer medicalbase;

    private Float medicalper;

    private Integer accumulationfundbase;

    private Float accumulationfundper;

    private String name;


}