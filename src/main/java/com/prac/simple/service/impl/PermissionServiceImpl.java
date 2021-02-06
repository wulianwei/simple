package com.prac.simple.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prac.simple.constant.CodeMsg;
import com.prac.simple.constant.CommonConstant;
import com.prac.simple.entity.Permission;
import com.prac.simple.entity.RolePermission;
import com.prac.simple.entity.User;
import com.prac.simple.entity.req.EditRolePermissionReq;
import com.prac.simple.entity.resp.MenuResp;
import com.prac.simple.init.DataInit;
import com.prac.simple.mapper.PermissionMapper;
import com.prac.simple.mapper.RolePermissionMapper;
import com.prac.simple.service.PermissionService;
import com.prac.simple.util.AccessTokenUtil;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.ServiceResult;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	private DataInit dataInit;
		
	@Override
	public ServiceResult<List<MenuResp>> searchMenu() {
		// TODO Auto-generated method stub
		User user = AccessTokenUtil.getUser();
		if(user == null) {
			return ServiceResult.newFailure(CodeMsg.LOGIN_PLEASE);
		}
		List<Permission> permissions=permissionMapper.selectPermissionByUserId(user.getId());
		MenuResp topMenu = new MenuResp();
		topMenu.setId("0");
		formatMenu(permissions,topMenu);
		return ServiceResult.newSuccess(topMenu.getChildren());
	}
	
	
	/**
	 * @Description 封装菜单
	 * @author wulianwei
	 * @Date 2020年5月15日
	 */
	private void formatMenu(List<Permission> permissions,MenuResp menu) {
		for(Permission p : permissions) {
			if(p.getPid().equals(menu.getId())) {
				MenuResp child = new MenuResp();
				BeanUtils.copyProperties(p, child);
				menu.getChildren().add(child);
			}
		}
		if(menu.getChildren().size() == 0) {
			return;
		}
		
		menu.setChildren(menu.getChildren().stream().sorted(Comparator.comparing(MenuResp::getOrders)).collect(Collectors.toList()));
		for(MenuResp child : menu.getChildren()) {
			formatMenu(permissions,child);
		}
	}


	@Override
	public ServiceResult<Permission> permissionDetail(String id) {
		// TODO Auto-generated method stub
		return ServiceResult.newSuccess(permissionMapper.selectByPrimaryKey(id));
	}
	
	@Override
	public ServiceResult<List<String>> listPermissionIdByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return ServiceResult.newSuccess(rolePermissionMapper.selectPermissionIdByRoleId(roleId));
	}
	
	@Override
	@Transactional
	public OperationResult addPermission(Permission permission) {
		// TODO Auto-generated method stub
		Permission existPermission = permissionMapper.selectPermissionByUrl(permission.getUrl());
		if(existPermission != null) {
			return OperationResult.newFailure(CodeMsg.PERMISSION_EXIST);
		}
		permissionMapper.insertSelective(permission);
		existPermission = permissionMapper.selectPermissionByUrl(permission.getUrl());
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleId(CommonConstant.ROLE_MANAGER);
		rolePermission.setPermissionId(existPermission.getId());
		rolePermissionMapper.insert(rolePermission);
		dataInit.initPermission();
		return OperationResult.newSuccess();
	}


	@Override
	public OperationResult editPermission(Permission permission) {
		// TODO Auto-generated method stub
		Permission existPermission = permissionMapper.selectExistPermissionByUrl(permission.getUrl(),permission.getId());
		if(existPermission != null) {
			return OperationResult.newFailure(CodeMsg.PERMISSION_EXIST);
		}
		permissionMapper.updateByPrimaryKeySelective(permission);
		dataInit.initPermission();
		return OperationResult.newSuccess();
	}


	@Override
	@Transactional
	public OperationResult batchDeletePermission(List<String> ids) {
		// TODO Auto-generated method stub
//		permissionMapper.deleteByPrimaryKey(id)
		for(String id : ids) {
			List<Permission> children = permissionMapper.selectChildrenPermission(id);
			if(children.size() > 0) {
				Permission permission = permissionMapper.selectByPrimaryKey(id);
				return OperationResult.newFailure(permission.getTitle()+"包含子功能,无法删除");
			}
		}
		permissionMapper.batchDeletePermission(ids);
		rolePermissionMapper.batchDeleteRolePermissionByPermissionId(ids);
		dataInit.initPermission();
		return OperationResult.newSuccess();
	}


	@Override
	@Transactional
	public OperationResult editRolePermission(EditRolePermissionReq req) {
		// TODO Auto-generated method stub
		rolePermissionMapper.deleteRolePermissionByRoleId(req.getRoleId());
		for(String permissionId : req.getPermissionIds()) {
			RolePermission record = new RolePermission();
			record.setRoleId(req.getRoleId());
			record.setPermissionId(permissionId);
			rolePermissionMapper.insert(record);
		}
		dataInit.initPermission();
		return OperationResult.newSuccess();
	}
	
}
