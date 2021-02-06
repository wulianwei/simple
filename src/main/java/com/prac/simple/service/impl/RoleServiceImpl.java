package com.prac.simple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prac.simple.constant.CodeMsg;
import com.prac.simple.entity.Role;
import com.prac.simple.entity.User;
import com.prac.simple.entity.UserRole;
import com.prac.simple.entity.req.EditUserRoleReq;
import com.prac.simple.mapper.RoleMapper;
import com.prac.simple.mapper.RolePermissionMapper;
import com.prac.simple.mapper.UserRoleMapper;
import com.prac.simple.service.RoleService;
import com.prac.simple.util.AccessTokenUtil;
import com.prac.simple.util.OperationResult;
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

}
