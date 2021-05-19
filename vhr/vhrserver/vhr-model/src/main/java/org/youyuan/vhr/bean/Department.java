package org.youyuan.vhr.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Department implements Serializable {

    //存储过程返回值
    private Integer id;

    private String name;

    private Integer parentId;

    private String depPath;

    private Boolean enabled;

    private Boolean isParent;

    List<Department> children = new ArrayList<>();

    //存储过程返回值
    Integer result;
}