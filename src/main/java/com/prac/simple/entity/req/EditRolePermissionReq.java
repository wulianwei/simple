package com.prac.simple.entity.req;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EditRolePermissionReq {
	
	private String roleId;
	
	private List<String> permissionIds;

}
