package com.org.prac.simple.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Permission {
    private String id;

    private String pid;

    private String title;

    private String type;

    private String url;

    private String description;

    private Integer orders;

    
}