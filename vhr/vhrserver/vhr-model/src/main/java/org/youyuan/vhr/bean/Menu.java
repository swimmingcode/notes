package org.youyuan.vhr.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {

    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconcls;

    private Integer parentid;

    private Boolean enabled;

    private  Meta meta;

    /**
     * 子菜单
     */
    private List<Menu> children;

    /**
     * 访问菜单所对需的角色
     */
    private List<Role> roles;

}