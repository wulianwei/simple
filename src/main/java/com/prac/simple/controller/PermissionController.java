package com.prac.simple.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.prac.simple.entity.Permission;
import com.prac.simple.entity.req.EditRolePermissionReq;
import com.prac.simple.entity.req.PermissionReq;
import com.prac.simple.entity.resp.MenuResp;
import com.prac.simple.entity.resp.PermissionResp;
import com.prac.simple.init.DataInit;
import com.prac.simple.service.PermissionService;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.ServiceResult;
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
	@PostMapping("/refreshPermission")
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
	@PostMapping("/listUserMenu")
	public ServiceResult<List<MenuResp>> listUserMenu(){
		logger.info("listUserMenu>>");
		return permissionService.listUserMenu();
	}
	
	/**
	 * @Description 查询菜单
	 * @Author wulianwei
	 * @Date 2020-05-22
	 */
	@PostMapping("/listUserPermission")
	public ServiceResult<List<Permission>> listUserPermission(){
		logger.info("listUserPermission>>");
		return permissionService.listUserPermission();
	}
	
	/**
	 * @Description 查询菜单
	 * @Author wulianwei
	 * @Date 2020-05-22
	 */
	@PostMapping("/listAllPermission")
	public ServiceResult<List<Permission>> listAllPermission(){
		logger.info("listAllPermission>>");
		return permissionService.listAllPermission();
	}
	
	/**
	 * @Description 资源详情
	 * @Author wulianwei
	 * @Date 2020-05-22 14:18
	 * @Return  ServiceResult<Permission>
	 */
	@PostMapping("/permissionDetail")
	public ServiceResult<PermissionResp> permissionDetail(@RequestBody PermissionReq req){
		logger.info("permissionDetail>>{}", req);
		return permissionService.permissionDetail(req.getId());
	}
	
	/**
	 * @Description 查询角色拥有的资源
	 * @Author wulianwei
	 * @Date 2020-05-22 14:18
	 * @Return  ServiceResult<Permission>
	 */
	@PostMapping("/listPermissionIdByRoleId")
	public ServiceResult<List<String>> listPermissionIdByRoleId(@RequestBody PermissionReq req){
		logger.info("listPermissionIdByRoleId>>:{}", req);
		return permissionService.listPermissionIdByRoleId(req.getRoleId());
	}
	
	/**
	 * @Description 查看资源
	 * @Author wulianwei
	 * @Date 2020-05-22 14:18
	 * @Return  ServiceResult<Permission>
	 */
	@PostMapping("/listAllMenu")
	public ServiceResult<List<Permission>> listAllMenu(){
		logger.info("listAllMenu>>");
		return permissionService.listAllMenu();
	}
	/**
	 * @Description 添加资源
	 * @Author wulianwei
	 * @Date 2020-05-22 11:01
	 * @Return  OperationResult
	 */
	@PostMapping("/addPermission")
	public OperationResult addPermission(@RequestBody PermissionReq req) {
		logger.info("addPermission>>{}", req);
		return permissionService.addPermission(req);
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
	@PostMapping("/batchDeletePermission")
	public OperationResult batchDeletePermission(@RequestBody PermissionReq req) {
		logger.info("batchDeletePermission>>{}",req);
		return permissionService.batchDeletePermission(req.getId());
	}
	
	/**
	 * @Description 删除资源
	 * @Author wulianwei
	 * @Date 2020-05-23 10:14
	 * @Return  OperationResult
	 */
	@PostMapping("/deletePermission")
	public OperationResult deletePermission(@RequestBody PermissionReq req) {
		logger.info("deletePermission>>{}",req);
		return permissionService.deletePermission(req.getId());
	}
	

}
