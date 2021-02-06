package com.prac.simple.service;

import java.util.List;

import com.prac.simple.entity.Permission;
import com.prac.simple.entity.req.EditRolePermissionReq;
import com.prac.simple.entity.resp.MenuResp;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.ServiceResult;

public interface PermissionService {
	
	ServiceResult<List<MenuResp>> searchMenu();
	
	ServiceResult<Permission> permissionDetail(String id);
	
	ServiceResult<List<String>> listPermissionIdByRoleId(String roleId);
	
	OperationResult addPermission(Permission permission);
	
	OperationResult editPermission(Permission permission);
	
	OperationResult batchDeletePermission(List<String> ids);
	
	OperationResult editRolePermission(EditRolePermissionReq req);
}
