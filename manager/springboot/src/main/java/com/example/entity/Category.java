package com.example.entity;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * 博客分类
 */
public class Category {
    /** ID */
    private Integer id;
    /**
     * 名称
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
