package com.org.prac.simple.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.prac.simple.constant.CodeMsg;
import com.org.prac.simple.entity.Permission;
import com.org.prac.simple.entity.User;
import com.org.prac.simple.entity.resp.MenuResp;
import com.org.prac.simple.mapper.PermissionMapper;
import com.org.prac.simple.service.PermissionService;
import com.org.prac.simple.util.AccessTokenUtil;
import com.org.prac.simple.util.ServiceResult;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
		
	@Override
	public ServiceResult<List<MenuResp>> searchMenu() {
		// TODO Auto-generated method stub
		User user = AccessTokenUtil.getUser();
		if(user == null) {
			return ServiceResult.newFailure(CodeMsg.LOGIN_PLEASE);
		}
		List<Permission> permissions=permissionMapper.selectPermissionsByUserId(user.getId());
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
	public void formatMenu(List<Permission> permissions,MenuResp menu) {
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
	
}
