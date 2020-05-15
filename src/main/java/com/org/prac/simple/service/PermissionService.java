package com.org.prac.simple.service;

import java.util.List;

import com.org.prac.simple.entity.resp.MenuResp;
import com.org.prac.simple.util.ServiceResult;

public interface PermissionService {
	
	ServiceResult<List<MenuResp>> searchMenu();
}
