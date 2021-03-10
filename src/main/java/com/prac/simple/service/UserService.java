package com.prac.simple.service;

import java.util.List;

import com.prac.simple.entity.User;
import com.prac.simple.entity.req.EditPasswordReq;
import com.prac.simple.entity.req.UserReq;
import com.prac.simple.entity.resp.LoginResp;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;

public interface UserService {
	
	ServiceResult<LoginResp> login(String username,String password); 
	
	OperationResult logout(); 
	
	PageResult<List<User>> searchUser(UserReq req);
	
	ServiceResult<User> getUser(String id);
	
	OperationResult addUser(UserReq userReq);
	
	OperationResult editUser(UserReq userReq);
	
	OperationResult editPassword(EditPasswordReq req);
	
	OperationResult deleteUser(String id);
	
	OperationResult bacthDeleteUser(String ids);

}
