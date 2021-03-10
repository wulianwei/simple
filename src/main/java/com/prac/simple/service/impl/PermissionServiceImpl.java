package com.prac.simple.service.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import com.prac.simple.entity.req.PermissionReq;
import com.prac.simple.entity.resp.MenuResp;
import com.prac.simple.entity.resp.PermissionResp;
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
	public ServiceResult<List<MenuResp>> listUserMenu() {
		// TODO Auto-generated method stub
		User user = AccessTokenUtil.getUser();
		if(user == null) {
			return ServiceResult.newFailure(CodeMsg.LOGIN_PLEASE);
		}
		List<Permission> permissions=permissionMapper.selectPermissionByUserId(user.getId());
		MenuResp topMenu = new MenuResp();
		topMenu.setId("0");
		topMenu.setOrders(0);
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
	public ServiceResult<List<Permission>> listUserPermission(){
		User user = AccessTokenUtil.getUser();
		if(user == null) {
			return ServiceResult.newFailure(CodeMsg.LOGIN_PLEASE);
		}
		List<Permission> permissions=permissionMapper.selectPermissionByUserId(user.getId());
		return ServiceResult.newSuccess(permissions);
	}
	@Override
	public ServiceResult<PermissionResp> permissionDetail(String id) {
		// TODO Auto-generated method stub
		Permission permission = permissionMapper.selectByPrimaryKey(id);
		PermissionResp resp = new PermissionResp();
		BeanUtils.copyProperties(permission, resp);
		if(CommonConstant.PERMISSION_TYPE_SUBMENU.equals(permission.getType())) {
			permission = permissionMapper.selectByPrimaryKey(permission.getPid());
			resp.setMainMenu(permission.getPid());
			resp.setMainMenuName(permission.getTitle());
		}else if(CommonConstant.PERMISSION_TYPE_BUTTON.equals(permission.getType())) {
			permission = permissionMapper.selectByPrimaryKey(permission.getPid());
			resp.setSubMenu(permission.getPid());
			resp.setSubMenuName(permission.getTitle());
			
			permission = permissionMapper.selectByPrimaryKey(permission.getPid());
			resp.setMainMenu(permission.getPid());
			resp.setMainMenuName(permission.getTitle());
		}
		return ServiceResult.newSuccess(resp);
	}
	
	@Override
	public ServiceResult<List<String>> listPermissionIdByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return ServiceResult.newSuccess(rolePermissionMapper.selectPermissionIdByRoleId(roleId));
	}
	
	@Override
	@Transactional
	public OperationResult addPermission(PermissionReq req) {
		// TODO Auto-generated method stub
		if(!CommonConstant.PERMISSION_TYPE_MAINMENU.equals(req.getType())) {
			Permission existPermission = permissionMapper.selectPermissionByUrl(req.getUrl());
			if(existPermission != null) {
				return OperationResult.newFailure(CodeMsg.PERMISSION_EXIST);
			}
		}
		if(!StringUtils.isEmpty(req.getTitle())) {
			Permission exist = permissionMapper.selectPermissionByTitle(req.getTitle());
			if(exist != null &&!exist.getId().equals(req.getId()) ) {
				return  OperationResult.newFailure(CodeMsg.PERMISSION_EXIST);
			}
		}
		switch (req.getType()) {
		case CommonConstant.PERMISSION_TYPE_MAINMENU:
			req.setPid(CommonConstant.ZERO.toString());
			break;
		case CommonConstant.PERMISSION_TYPE_SUBMENU:
			req.setPid(req.getMainMenu());
			break;
		case CommonConstant.PERMISSION_TYPE_BUTTON:
			req.setPid(req.getSubMenu());
			break;
		}
		Permission permission = new Permission();
		BeanUtils.copyProperties(req, permission);
		permissionMapper.insertSelective(permission);
//		existPermission = permissionMapper.selectPermissionByUrl(permission.getUrl());
//		RolePermission rolePermission = new RolePermission();
//		rolePermission.setRoleId(CommonConstant.ROLE_MANAGER);
//		rolePermission.setPermissionId(existPermission.getId());
//		rolePermissionMapper.insert(rolePermission);
//		dataInit.initPermission();
		return OperationResult.newSuccess();
	}


	@Override
	public OperationResult editPermission(Permission req) {
		// TODO Auto-generated method stub
		if(!StringUtils.isEmpty(req.getUrl())) {
			Permission exist = permissionMapper.selectPermissionByUrl(req.getUrl());
			if(exist != null &&!exist.getId().equals(req.getId()) ) {
				return  OperationResult.newFailure(CodeMsg.PERMISSION_EXIST);
			}
		}
		if(!StringUtils.isEmpty(req.getTitle())) {
			Permission exist = permissionMapper.selectPermissionByTitle(req.getTitle());
			if(exist != null &&!exist.getId().equals(req.getId()) ) {
				return  OperationResult.newFailure(CodeMsg.PERMISSION_EXIST);
			}
		}
		
		permissionMapper.updateByPrimaryKeySelective(req);
		dataInit.initPermission();
		return OperationResult.newSuccess();
	}


	@Override
	@Transactional
	public OperationResult batchDeletePermission(String ids) {
		// TODO Auto-generated method stub
//		permissionMapper.deleteByPrimaryKey(id)
		List<String> idList =  Arrays.asList(ids.split(","));
		for(String id : idList) {
			List<Permission> children = permissionMapper.selectChildrenPermission(id);
			if(children.size() > 0) {
				Permission permission = permissionMapper.selectByPrimaryKey(id);
				return OperationResult.newFailure(permission.getTitle()+"包含子功能,无法删除");
			}
		}
		permissionMapper.batchDeletePermission(idList);
		rolePermissionMapper.batchDeleteRolePermissionByPermissionId(idList);
		dataInit.initPermission();
		return OperationResult.newSuccess();
	}
	
	@Override
	@Transactional
	public OperationResult deletePermission(String id) {
		// TODO Auto-generated method stub
		List<Permission> children = permissionMapper.selectChildrenPermission(id);
		if(children.size() > 0) {
			Permission permission = permissionMapper.selectByPrimaryKey(id);
			return OperationResult.newFailure(CodeMsg.CHILDREN_PERMISSION_EXIST);
		}
		permissionMapper.deleteByPrimaryKey(id);
		rolePermissionMapper.deleteRolePermissionByPermissionId(id);
		dataInit.initPermission();
		return OperationResult.newSuccess();
	}




	@Override
	public ServiceResult<List<Permission>> listAllPermission() {
		// TODO Auto-generated method stub
		List<Permission> permission = permissionMapper.selectPermission(null);
		return ServiceResult.newSuccess(permission);
	}


	@Override
	public ServiceResult<List<Permission>> listPermission(PermissionReq req) {
		// TODO Auto-generated method stub
		List<Permission> persmissons = permissionMapper.selectPermission(req);
		return ServiceResult.newSuccess(persmissons);
	}


	@Override
	public ServiceResult<List<Permission>> listAllMenu() {
		// TODO Auto-generated method stub
		List<Permission> persmissons = permissionMapper.selectAllMenu();
		return ServiceResult.newSuccess(persmissons);
	}


	
	
}
