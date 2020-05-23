package com.org.prac.simple.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.prac.simple.entity.Permission;
import com.org.prac.simple.entity.req.EditRolePermissionReq;
import com.org.prac.simple.entity.req.PermissionReq;
import com.org.prac.simple.entity.resp.MenuResp;
import com.org.prac.simple.init.DataInit;
import com.org.prac.simple.service.PermissionService;
import com.org.prac.simple.util.OperationResult;
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
	
	@Autowired
	DataInit dataInit;
	
	/**
	 * @Description 更新资源缓存
	 * @Author wulianwei
	 * @Date 2020-05-22
	 */
	@RequestMapping("/refreshPermission")
	public OperationResult cachePermission(){
		logger.info("refreshPermission>>");
		dataInit.initPermission();
		return OperationResult.newSuccess();
	}

	/**
	 * @Description 查询菜单
	 * @Author wulianwei
	 * @Date 2020-05-22
	 */
	@RequestMapping("/searchMenu")
	public ServiceResult<List<MenuResp>> searchMenu(){
		logger.info("searchMenu>>");
		return permissionService.searchMenu();
	}
	
	/**
	 * @Description 资源详情
	 * @Author wulianwei
	 * @Date 2020-05-22 14:18
	 * @Return  ServiceResult<Permission>
	 */
	@RequestMapping("/permissionDetail")
	public ServiceResult<Permission> permissionDetail(@RequestBody PermissionReq req){
		logger.info("permissionDetail>>{}", req);
		return permissionService.permissionDetail(req.getId());
	}
	
	/**
	 * @Description 查询角色拥有的资源
	 * @Author wulianwei
	 * @Date 2020-05-22 14:18
	 * @Return  ServiceResult<Permission>
	 */
	@RequestMapping("/listPermissionIdByRoleId")
	public ServiceResult<List<String>> listPermissionIdByRoleId(@RequestBody PermissionReq req){
		logger.info("searchRolePermission>>:{}", req);
		return permissionService.listPermissionIdByRoleId(req.getRoleId());
	}
	
	/**
	 * @Description 添加资源
	 * @Author wulianwei
	 * @Date 2020-05-22 11:01
	 * @Return  OperationResult
	 */
	@PostMapping("/addPermission")
	public OperationResult addPermission(@RequestBody Permission permission) {
		logger.info("addPermission>>{}", permission);
		return permissionService.addPermission(permission);
	} 
	
	/**
	 * @Description 修改资源
	 * @Author wulianwei
	 * @Date 2020-05-22 11:01
	 * @Return  OperationResult
	 */
	@PostMapping("/editPermission")
	public OperationResult editPermission(@RequestBody Permission permission) {
		logger.info("editPermission>>{}", permission);
		return permissionService.editPermission(permission);
	} 
	
	/**
	 * @Description 删除资源
	 * @Author wulianwei
	 * @Date 2020-05-23 10:14
	 * @Return  OperationResult
	 */
	@RequestMapping("/batchDeletePermission")
	public OperationResult batchDeletePermission(@RequestBody PermissionReq req) {
		logger.info("batchDeletePermission>>{}",req);
		return permissionService.batchDeletePermission(req.getIds());
	}
	
	/**
	 * @Description 为角色赋权
	 * @Author wulianwei
	 * @Date 2020-05-23 16:55
	 * @Return  OperationResult
	 */
	@PostMapping("/editRolePermission")
	public OperationResult editRolePermission(@RequestBody EditRolePermissionReq req) {
		logger.info("editRolePermission>>{}",req);
		return permissionService.editRolePermission(req);
	}
}
