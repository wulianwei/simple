package com.org.prac.simple.service;

import com.org.prac.simple.entity.resp.MenuResp;
import com.org.prac.simple.util.ServiceResult;

public interface PermissionService {
	
	ServiceResult<MenuResp> searchMenu(String userId);
}
