package com.prac.simple.entity.req;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EditUserRoleReq {
	
	private String userId;
	
	private List<String> roleIds;

}
