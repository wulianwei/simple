package com.org.prac.simple.entity.req;

import lombok.Data;
import lombok.ToString;

@Data	
@ToString
public class UserReq {
	
	private Integer pageSize;
	
	private Integer pageNum;
	
	private String userName;

}
