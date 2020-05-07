package com.org.prac.simple.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.org.prac.simple.constant.CodeMsg;
import com.org.prac.simple.constant.CommonConstant;
import com.org.prac.simple.entity.Role;
import com.org.prac.simple.entity.User;
import com.org.prac.simple.entity.resp.LoginResp;
import com.org.prac.simple.mapper.RoleMapper;
import com.org.prac.simple.mapper.UserMapper;
import com.org.prac.simple.service.UserService;
import com.org.prac.simple.util.MD5Util;
import com.org.prac.simple.util.OperationResult;
import com.org.prac.simple.util.ServiceResult;
import com.org.prac.simple.util.UUIDUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public ServiceResult<LoginResp> login(String username,String password) {
		// TODO Auto-generated method stub
		User loginUser = userMapper.selectUserByUsername(username);
		if(loginUser == null) {
			return ServiceResult.newFailure(CodeMsg.USERNAME_WRONG);
		}
		if(!MD5Util.encode32(password).equals(loginUser.getPassword())) {
			return ServiceResult.newFailure(CodeMsg.PASSWORD_WRONG);
		}
		List<Role> roles = roleMapper.selectRolesByUserId(loginUser.getId());
		List<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
		loginUser.setRoles(roleIds);
		String token = UUIDUtil.getUUID();
		redisTemplate.opsForValue().set(token, loginUser,CommonConstant.LOGIN_EXPIRE,TimeUnit.SECONDS);
		LoginResp resp = new LoginResp();
		resp.setId(loginUser.getId());
		resp.setUsername(username);
		resp.setRoles(roleIds);
		resp.setToken(token);
		ServiceResult<LoginResp> result = ServiceResult.newSuccess(resp);
		result.setMsg(CodeMsg.LOGINSUCCESS.getMessage());
		return result;
	}

	@Override
	public OperationResult addUser(User user) {
		// TODO Auto-generated method stub
		User existUser = userMapper.selectUserByUsername(user.getUsername());
		if(existUser != null) {
			return OperationResult.newFailure(CodeMsg.USER_EXIST);
		}
		user.setId(UUIDUtil.get32UUID());
		user.setPassword(MD5Util.encode32(user.getPassword()));
		userMapper.insert(user);
		return OperationResult.newSuccess();
	}

	@Override
	public OperationResult editUser(User user) {
		// TODO Auto-generated method stub
		User existUser = userMapper.selectByPrimaryKey(user.getId());
		if(existUser == null) {
			return OperationResult.newFailure(CodeMsg.USER_NO_EXIST);
		}
		userMapper.updateByPrimaryKeySelective(user);
		return OperationResult.newSuccess();
	}
	
}
