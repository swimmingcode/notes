package org.youyuan.vhr.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class JobLevel implements Serializable {
    private Integer id;

    private String name;

    private String titleLevel;

    @JsonFormat(pattern = "yyy-MM-dd HH:MM:SS", timezone = "Asia/Shanghai")
    private Date createDate;

    private Boolean enabled;


}