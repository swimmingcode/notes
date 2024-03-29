package com.itlike.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Menu {
    private Long id;
    private String text;
    private String url;
    private Long parentId;
    private Menu parent;
    private Permission permission;
    /**
     * Menu应该抽象成接口
     * 组合模式
     */
    private List<Menu> children = new ArrayList<>();

}