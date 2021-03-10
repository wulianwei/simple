package com.prac.simple.entity.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleReq {
	
	private String id;

    private String name;

    private String description;
    
    private Integer pageSize;
	
	private Integer pageNum;

}
