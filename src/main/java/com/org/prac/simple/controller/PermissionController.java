package com.org.prac.simple.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.prac.simple.entity.resp.MenuResp;
import com.org.prac.simple.service.PermissionService;
import com.org.prac.simple.util.ServiceResult;
/**
 * 
 * @Description:  权限管理
 * @author: wulianwei  
 * @date: 2020-05-15 17:09
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	PermissionService permissionService;

	@RequestMapping("/searchMenu")
	public ServiceResult<List<MenuResp>> searchMenu(){
		logger.info("searchMenu>>");
		return permissionService.searchMenu();
	}
}
