package com.org.prac.simple.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.org.prac.simple.constant.CodeMsg;
import com.org.prac.simple.constant.CommonConstant;
import com.org.prac.simple.entity.Role;
import com.org.prac.simple.entity.User;
import com.org.prac.simple.entity.req.UserReq;
import com.org.prac.simple.entity.resp.LoginResp;
import com.org.prac.simple.mapper.RoleMapper;
import com.org.prac.simple.mapper.UserMapper;
import com.org.prac.simple.service.UserService;
import com.org.prac.simple.util.MD5Util;
import com.org.prac.simple.util.OperationResult;
import com.org.prac.simple.util.PageResult;
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
		List<Role> roles = roleMapper.selectRoleByUserId(loginUser.getId());
		List<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
		loginUser.setRoles(roleIds);
		String token = UUIDUtil.getUUID();
		redisTemplate.opsForValue().set(token, loginUser,CommonConstant.LOGIN_EXPIRE,TimeUnit.SECONDS);
		//跟新登录时间
		User user = new User();
		user.setId(loginUser.getId());
		user.setLasttime(new Date());
		userMapper.updateByPrimaryKeySelective(user);
		//返回登录结果
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
	public PageResult<List<User>> searchUser(UserReq req) {
		// TODO Auto-generated method stub
		Page<Object> page = PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<User> users = userMapper.selectUser(req);
		PageResult<List<User>> userResult = PageResult.newSuccess(users);
		userResult.setTotal((int) page.getTotal());
		return userResult;
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
		user.setCreatetime(new Date());
		userMapper.insertSelective(user);
		return OperationResult.newSuccess();
	}

	@Override
	public OperationResult editUser(User user) {
		// TODO Auto-generated method stub
		User existUser = userMapper.selectByPrimaryKey(user.getId());
		if(existUser == null) {
			return OperationResult.newFailure(CodeMsg.USER_NO_EXIST);
		}
		if(!StringUtils.isEmpty(user.getPassword())){
			user.setPassword(MD5Util.encode32(user.getPassword()));
		}
		user.setUsername(null);//不允许修改用户名
		userMapper.updateByPrimaryKeySelective(user);
		return OperationResult.newSuccess();
	}
	
}
