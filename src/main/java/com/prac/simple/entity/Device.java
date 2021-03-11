package com.prac.simple.entity;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Device {
    private String mac;

    private String name;

    private String open;

    private String type;

    private String description;

    private Date createtime;

    private Date modifytime;

}