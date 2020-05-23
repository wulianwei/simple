package com.org.prac.simple.service;

import java.util.List;

import com.org.prac.simple.entity.Role;
import com.org.prac.simple.entity.req.EditUserRoleReq;
import com.org.prac.simple.util.OperationResult;
import com.org.prac.simple.util.ServiceResult;

public interface RoleService {
	
	ServiceResult<List<Role>> listRole();
	
	ServiceResult<List<String>> listRoleIdByUser();
	
	OperationResult addRole(Role role);
	
	OperationResult editRole(Role role);
	
	OperationResult deleteRole(Role role);
	
	OperationResult editUserRole(EditUserRoleReq req);
}
