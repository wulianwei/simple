package com.org.prac.simple.service;

import com.org.prac.simple.entity.User;
import com.org.prac.simple.entity.resp.LoginResp;
import com.org.prac.simple.util.OperationResult;
import com.org.prac.simple.util.ServiceResult;

public interface UserService {
	
	ServiceResult<LoginResp> login(String username,String password); 
	
	OperationResult addUser(User user);
	
	OperationResult editUser(User user);

}
