package com.org.prac.simple.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.prac.simple.entity.User;
import com.org.prac.simple.entity.req.UserReq;
import com.org.prac.simple.entity.resp.LoginResp;
import com.org.prac.simple.service.UserService;
import com.org.prac.simple.util.OperationResult;
import com.org.prac.simple.util.PageResult;
import com.org.prac.simple.util.ServiceResult;
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
	 * 
	 * @Description:    登陆   
	 * @Author: Administrator     
	 * @Date:   2020-04-29 11:43  
	 * @Return ServiceResult<LoginResp>
	 */
	@RequestMapping("/login")
	public ServiceResult<LoginResp> login(String username, String password){
		logger.info("login>>:",username+"|"+password);
		return userService.login(username, password);		
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
	 * 
	 * @Description:    添加用户   
	 * @Author: Administrator     
	 * @Date:   2020-04-30 15:24  
	 * @Return OperationResult
	 */
	@PostMapping("/addUser")
	public OperationResult addUser(@RequestBody User user) {
		logger.info("addUser>>:{}", user);
		return userService.addUser(user);
	}
	
	/**
	 * 
	 * @Description:    编辑用户   
	 * @Author: Administrator     
	 * @Date:   2020-04-30 15:24  
	 * @Return OperationResult
	 */
	@PostMapping("/editUser")
	public OperationResult editUser(@RequestBody User user) {
		logger.info("editUser>>:{}",user);
		return userService.editUser(user);
	}
}
