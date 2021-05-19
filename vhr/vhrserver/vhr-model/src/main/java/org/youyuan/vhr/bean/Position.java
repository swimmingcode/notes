package org.youyuan.vhr.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Position implements Serializable {
    private Integer id;
    private String name;
    /*服务端传到客户端*/
    @JsonFormat(pattern = "yyy-MM-dd HH:MM:SS", timezone = "Asia/Shanghai")
    private Date createDate;
    private Boolean enabled;
}