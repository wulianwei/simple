package com.org.prac.simple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.prac.simple.entity.Permission;
import com.org.prac.simple.entity.resp.MenuResp;
import com.org.prac.simple.mapper.PermissionMapper;
import com.org.prac.simple.service.PermissionService;
import com.org.prac.simple.util.ServiceResult;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public ServiceResult<MenuResp> searchMenu(String userId) {
		// TODO Auto-generated method stub
		List<Permission> permissions=permissionMapper.selectPermissionsByUserId(userId);
		
		return null;
	}
	
}
