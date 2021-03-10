package com.prac.simple.service;

import java.util.List;

import com.prac.simple.entity.Permission;
import com.prac.simple.entity.req.EditRolePermissionReq;
import com.prac.simple.entity.req.PermissionReq;
import com.prac.simple.entity.resp.MenuResp;
import com.prac.simple.entity.resp.PermissionResp;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.ServiceResult;

public interface PermissionService {
	
	ServiceResult<List<MenuResp>> listUserMenu();
	
	ServiceResult<List<Permission>> listUserPermission();
	
	ServiceResult<List<Permission>> listAllPermission();
	
	ServiceResult<PermissionResp> permissionDetail(String id);
	
	ServiceResult<List<String>> listPermissionIdByRoleId(String roleId);
	
	ServiceResult<List<Permission>> listPermission(PermissionReq req);
	
	ServiceResult<List<Permission>> listAllMenu();
	
	OperationResult addPermission(PermissionReq req);
	
	OperationResult editPermission(Permission permission);
	
	OperationResult batchDeletePermission(String ids);
	
	OperationResult deletePermission(String id);
	
}
