package com.prac.simple.entity.req;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeviceReq {
	
	private Integer pageSize;
	
	private Integer pageNum;
	
	private String sort;  //排序字段
	
	private String order; //排序,asc,desc
	
	private String mac;

    private String name;

    private String open;

    private String type;

    private String description;

    private Date createtime;

}
