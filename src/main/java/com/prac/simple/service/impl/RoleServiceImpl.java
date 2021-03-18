package com.prac.simple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.prac.simple.constant.CodeMsg;
import com.prac.simple.entity.Role;
import com.prac.simple.entity.RolePermission;
import com.prac.simple.entity.User;
import com.prac.simple.entity.UserRole;
import com.prac.simple.entity.req.EditRolePermissionReq;
import com.prac.simple.entity.req.EditUserRoleReq;
import com.prac.simple.entity.req.RoleReq;
import com.prac.simple.mapper.RoleMapper;
import com.prac.simple.mapper.RolePermissionMapper;
import com.prac.simple.mapper.UserRoleMapper;
import com.prac.simple.service.RoleService;
import com.prac.simple.util.AccessTokenUtil;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public ServiceResult<List<Role>> listRole() {
		// TODO Auto-generated method stub
		return ServiceResult.newSuccess(roleMapper.selectAllRole());
	}
	
	@Override
	public ServiceResult<List<String>> listRoleIdByUser() {
		// TODO Auto-generated method stub
		User user = AccessTokenUtil.getUser();
		if(user == null) {
			return ServiceResult.newFailure(CodeMsg.LOGIN_PLEASE);
		}
		List<String> roleIds = userRoleMapper.selectRoleIdByUserId(user.getId());
		return ServiceResult.newSuccess(roleIds);
	}

	@Override
	public OperationResult addRole(Role role) {
		// TODO Auto-generated method stub
		Role existRole = roleMapper.selectByPrimaryKey(role.getId());
		if(existRole != null) {
			return OperationResult.newFailure(CodeMsg.ROLE_EXIST);
		}
		roleMapper.insert(role);
		return OperationResult.newSuccess();
	}

	@Override
	public OperationResult editRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.updateByPrimaryKey(role);
		return OperationResult.newSuccess();
	}

	@Override
	@Transactional
	public OperationResult deleteRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.deleteByPrimaryKey(role.getId());
		rolePermissionMapper.deleteRolePermissionByRoleId(role.getId());
		userRoleMapper.deleteUserRoleByRoleId(role.getId());
		return OperationResult.newSuccess();
	}

	@Override
	@Transactional
	public OperationResult editUserRole(EditUserRoleReq req) {
		// TODO Auto-generated method stub
		userRoleMapper.deleteUserRoleByUserId(req.getUserId());
		for(String roleId : req.getRoleIds()) {
			UserRole record = new UserRole();
			record.setUserId(req.getUserId());
			record.setRoleId(roleId);
			userRoleMapper.insert(record);
		}
		return OperationResult.newSuccess();
	}

	@Override
	public PageResult<List<Role>> searchRole(RoleReq req) {
		// TODO Auto-generated method stub
		Page<Object> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
		List<Role> roles = roleMapper.selectRole(req);
		PageResult<List<Role>> result = PageResult.newSuccess(roles); 
		result.setTotal((int) page.getTotal());
		return result;
	}

	@Override
	public ServiceResult<Role> getRole(String id) {
		// TODO Auto-generated method stub
		Role role = roleMapper.selectByPrimaryKey(id);
		return ServiceResult.newSuccess(role);
	}

	@Override
	@Transactional
	public OperationResult authorPermission(EditRolePermissionReq req) {
		// TODO Auto-generated method stub
		rolePermissionMapper.deleteRolePermissionByRoleId(req.getRoleId());
		for(String permissionId : req.getPermissionIds()) {
			RolePermission record = new RolePermission();
			record.setRoleId(req.getRoleId());
			record.setPermissionId(permissionId);
			rolePermissionMapper.insert(record);
		}
		return OperationResult.newSuccess();
	}

}
