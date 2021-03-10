package com.prac.simple.service;

import java.util.List;

import com.prac.simple.entity.Role;
import com.prac.simple.entity.req.EditRolePermissionReq;
import com.prac.simple.entity.req.EditUserRoleReq;
import com.prac.simple.entity.req.RoleReq;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;

public interface RoleService {
	
	ServiceResult<List<Role>> listRole();
	
	PageResult<List<Role>> searchRole(RoleReq req);
	
	ServiceResult<List<String>> listRoleIdByUser();
	
	ServiceResult<Role> getRole(String id);
	
	OperationResult addRole(Role role);
	
	OperationResult editRole(Role role);
	
	OperationResult deleteRole(Role role);
	
	OperationResult editUserRole(EditUserRoleReq req);
	
	OperationResult authorPermission(EditRolePermissionReq req);
}
