package com.prac.simple.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RolePermission {
	
	private String roleId;
	private String url;
	private String permissionId;

}
