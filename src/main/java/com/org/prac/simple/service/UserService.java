package com.org.prac.simple.service;

import java.util.List;

import com.org.prac.simple.entity.User;
import com.org.prac.simple.entity.req.UserReq;
import com.org.prac.simple.entity.resp.LoginResp;
import com.org.prac.simple.util.OperationResult;
import com.org.prac.simple.util.PageResult;
import com.org.prac.simple.util.ServiceResult;

public interface UserService {
	
	ServiceResult<LoginResp> login(String username,String password); 
	
	PageResult<List<User>> searchUser(UserReq req);
	
	OperationResult addUser(User user);
	
	OperationResult editUser(User user);

}
