package com.prac.simple.entity.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleReq {
	
	private Integer pageSize;
	
	private Integer pageNum;
	
	private String sort;  //排序字段
	
	private String order; //排序,asc,desc
	
	private String id;

    private String name;

    private String description;
    
}
