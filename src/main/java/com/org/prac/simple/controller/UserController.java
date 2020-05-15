package com.org.prac.simple.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.prac.simple.entity.User;
import com.org.prac.simple.entity.resp.LoginResp;
import com.org.prac.simple.service.UserService;
import com.org.prac.simple.util.OperationResult;
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
	 * @author: Administrator     
	 * @date:   2020-04-29 11:43  
	 * @return ServiceResult<LoginResp>
	 */
	@RequestMapping("/login")
	public ServiceResult<LoginResp> login(String username, String password){
		logger.info("login>>:",username+"|"+password);
		return userService.login(username, password);		
	}
	
	/**
	 * 
	 * @Description:    添加用户   
	 * @author: Administrator     
	 * @date:   2020-04-30 15:24  
	 * @return OperationResult
	 */
	@PostMapping("/addUser")
	public OperationResult addUser(User user) {
		logger.info("addUser>>:{}",user);
		return userService.addUser(user);
	}
	
	/**
	 * 
	 * @Description:    编辑用户   
	 * @author: Administrator     
	 * @date:   2020-04-30 15:24  
	 * @return OperationResult
	 */
	@PostMapping("/editUser")
	public OperationResult editUser(User user) {
		logger.info("addUser>>:{}",user);
		return null;
	}
}
