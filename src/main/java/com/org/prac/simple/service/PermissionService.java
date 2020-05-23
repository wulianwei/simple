package com.org.prac.simple.service;

import java.util.List;

import com.org.prac.simple.entity.Permission;
import com.org.prac.simple.entity.req.EditRolePermissionReq;
import com.org.prac.simple.entity.resp.MenuResp;
import com.org.prac.simple.util.OperationResult;
import com.org.prac.simple.util.ServiceResult;

public interface PermissionService {
	
	ServiceResult<List<MenuResp>> searchMenu();
	
	ServiceResult<Permission> permissionDetail(String id);
	
	ServiceResult<List<String>> listPermissionIdByRoleId(String roleId);
	
	OperationResult addPermission(Permission permission);
	
	OperationResult editPermission(Permission permission);
	
	OperationResult batchDeletePermission(List<String> ids);
	
	OperationResult editRolePermission(EditRolePermissionReq req);
}
