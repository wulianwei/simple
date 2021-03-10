package com.prac.simple.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.simple.entity.Role;
import com.prac.simple.entity.req.EditRolePermissionReq;
import com.prac.simple.entity.req.EditUserRoleReq;
import com.prac.simple.entity.req.RoleReq;
import com.prac.simple.service.RoleService;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;

@RestController	
@RequestMapping("/role")
public class RoleController {
	
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	RoleService roleService;
	
	/**
	 * @Description 列出所有角色
	 * @Author wulianwei
	 * @Date 2020-05-23 15:16
	 * @Return  ServiceResult<List<Role>>
	 */
	@PostMapping("/listAllRole")
	public ServiceResult<List<Role>> listAllRole(){
		logger.info("listAllRole>>");
		return roleService.listRole();
	}
	
	/**
	 * @Description 查询角色
	 * @Author wulianwei
	 * @Date 2020-05-23 15:16
	 * @Return  ServiceResult<List<Role>>
	 */
	@PostMapping("/searchRole")
	public PageResult<List<Role>> searchRole(@RequestBody RoleReq req){
		logger.info("searchRole>>req:{}",req);
		return roleService.searchRole(req);
	}
	
	/**
	 * @Description 查询用户拥有的角色
	 * @Author wulianwei
	 * @Date 2020-05-23 15:50
	 * @Return  ServiceResult
	 */
	@PostMapping("/listRoleIdByUser")
	public ServiceResult<List<String>> listRoleIdByUser() {
		logger.info("listRoleIdByUser>>");
		return roleService.listRoleIdByUser();
	}
	
	/**
	 * @Description 角色详情
	 * @Author wulianwei
	 * @Date 2020-05-23 15:18
	 * @Return  OperationResult
	 */
	@PostMapping("/getRole")
	public ServiceResult<Role> getRole(@RequestBody RoleReq req) {
		logger.info("getRole>>{}", req);
		return roleService.getRole(req.getId());
	}
	
	/**
	 * @Description 添加角色
	 * @Author wulianwei
	 * @Date 2020-05-23 15:18
	 * @Return  OperationResult
	 */
	@PostMapping("/addRole")
	public OperationResult addRole(@RequestBody Role role) {
		logger.info("addRole>>{}", role);
		return roleService.addRole(role);
	}
	
	/**
	 * @Description 编辑角色
	 * @Author wulianwei
	 * @Date 2020-05-23 15:24
	 * @Return  OperationResult
	 */
	@PostMapping("/editRole")
	public OperationResult editRole(@RequestBody Role role) {
		logger.info("editRole>>{}", role);
		return roleService.editRole(role);
	}
	
	/**
	 * @Description 删除角色
	 * @Author wulianwei
	 * @Date 2020-05-23 15:24
	 * @Return  OperationResult
	 */
	@PostMapping("/deleteRole")
	public OperationResult deleteRole(@RequestBody Role role) {
		logger.info("deleteRole>>{}", role);
		return roleService.deleteRole(role);
	}
	
	/**
	 * @Description 用户赋权
	 * @Author wulianwei
	 * @Date 2020-05-23 16:32
	 * @Return  OperationResult
	 */
	@PostMapping("/editUserRole")
	public OperationResult editUserRole(@RequestBody EditUserRoleReq req) {
		logger.info("editUserRole>>{}", req);
		return roleService.editUserRole(req);
	}
	
	/**
	 * @Description 为角色赋权
	 * @Author wulianwei
	 * @Date 2020-05-23 16:55
	 * @Return  OperationResult
	 */
	@PostMapping("/authorPermission")
	public OperationResult authorPermission(@RequestBody EditRolePermissionReq req) {
		logger.info("authorPermission>>{}",req);
		return roleService.authorPermission(req);
	}

}
