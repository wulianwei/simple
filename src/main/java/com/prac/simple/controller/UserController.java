package com.prac.simple.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.simple.entity.User;
import com.prac.simple.entity.req.EditPasswordReq;
import com.prac.simple.entity.req.UserReq;
import com.prac.simple.entity.resp.LoginResp;
import com.prac.simple.service.UserService;
import com.prac.simple.util.AccessTokenUtil;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;
/**
 * 
 * @Description:  用户管理
 * @author: wulianwei  
 * @date: 2020-05-15 17:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	/**
	 * @Description:    登陆   
	 * @Author: wulianwei     
	 * @Date:   2020-04-29 11:43  
	 * @Return ServiceResult<LoginResp>
	 */
	@PostMapping("/login")
	public ServiceResult<LoginResp> login(@RequestBody User user){
		logger.info("login>>user:{}",user);
		return userService.login(user.getUsername(), user.getPassword());		
	}
	/**
	 * @Description:    登陆   
	 * @Author: wulianwei     
	 * @Date:   2020-04-29 11:43  
	 * @Return ServiceResult<LoginResp>
	 */
	@PostMapping("/logout")
	public OperationResult logout(){
		logger.info("logout>>");
		return userService.logout();		
	}
	
	/**
	 * @Description 查询人员 
	 * @Author wulianwei
	 * @Date 2020-05-23 12:32
	 * @Return  PageResult<List<User>>
	 */
	@PostMapping("/searchUser")
	public PageResult<List<User>> searchUser(@RequestBody UserReq req){
		logger.info("searchUser>>:{}", req);
		return userService.searchUser(req);
	}
	
	/**
	 * @Description 查询人员 
	 * @Author wulianwei
	 * @Date 2020-05-23 12:32
	 * @Return  PageResult<List<User>>
	 */
	@PostMapping("/getUser")
	public ServiceResult<User> getUser(@RequestBody UserReq req){
		logger.info("getUser>>:{}", req);
		return userService.getUser(req.getId());
	}
	
	/**
	 * 
	 * @Description:    添加用户   
	 * @Author: wulianwei     
	 * @Date:   2020-04-30 15:24  
	 * @Return OperationResult
	 */
	@PostMapping("/addUser")
	public OperationResult addUser(@RequestBody UserReq userReq) {
		logger.info("addUser>>:{}", userReq);
		return userService.addUser(userReq);
	}
	
	/**
	 * @Description: 编辑用户   
	 * @Author: wulianwei     
	 * @Date:   2020-04-30 15:24  
	 * @Return OperationResult
	 */
	@PostMapping("/editUser")
	public OperationResult editUser(@RequestBody UserReq userReq) {
		logger.info("editUser>>:{}",userReq);
		return userService.editUser(userReq);
	}
	/**
	 * @Description: 修改密码   
	 * @Author: wulianwei     
	 * @Date:   2020-04-30 15:24  
	 * @Return OperationResult
	 */
	@PostMapping("/editPassword")
	public OperationResult editPassword(@RequestBody EditPasswordReq req) {
		logger.info("editPassword>>:{}",req);
		return userService.editPassword(req);
	}
	
	/**
	 * 
	 * @Description:    批量删除用户   
	 * @Author: wulianwei     
	 * @Date:   2020-04-30 15:24  
	 * @Return OperationResult
	 */
	@PostMapping("/batchDeleteUser")
	public OperationResult batchDeleteUser(@RequestBody UserReq req) {
		logger.info("batchDeleteUser>>:{}",req);
		return userService.bacthDeleteUser(req.getId());
	}
	
	/**
	 * 
	 * @Description:    删除用户   
	 * @Author: wulianwei     
	 * @Date:   2020-04-30 15:24  
	 * @Return OperationResult
	 */
	@PostMapping("/deleteUser")
	public OperationResult deleteUser(@RequestBody UserReq req) {
		logger.info("deleteUser>>:{}",req);
		return userService.deleteUser(req.getId());
	}
	
	/**
	 * 
	 * @Description:    登录用户简介  
	 * @Author: wulianwei     
	 * @Date:   2020-04-30 15:24  
	 * @Return OperationResult
	 */
	@PostMapping("/loginBrief")
	public ServiceResult<User> brief() {
		logger.info("loginBrief>>");
		User user = AccessTokenUtil.getUser();
		return ServiceResult.newSuccess(user);
	}
}
