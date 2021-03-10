package com.prac.simple.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.prac.simple.constant.CodeMsg;
import com.prac.simple.constant.CommonConstant;
import com.prac.simple.entity.Role;
import com.prac.simple.entity.User;
import com.prac.simple.entity.UserRole;
import com.prac.simple.entity.req.EditPasswordReq;
import com.prac.simple.entity.req.UserReq;
import com.prac.simple.entity.resp.LoginResp;
import com.prac.simple.mapper.RoleMapper;
import com.prac.simple.mapper.UserMapper;
import com.prac.simple.mapper.UserRoleMapper;
import com.prac.simple.service.UserService;
import com.prac.simple.util.AccessTokenUtil;
import com.prac.simple.util.MD5Util;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;
import com.prac.simple.util.UUIDUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	UserRoleMapper userRoleMapper;
	
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
	public OperationResult logout() {
		String token = AccessTokenUtil.getAccessToken();
		if(StringUtils.isNoneEmpty(token)) {
			redisTemplate.delete(token);
		}
		return OperationResult.newSuccess();
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
	@Transactional
	public OperationResult addUser(UserReq userReq) {
		// TODO Auto-generated method stub
		User existUser = userMapper.selectUserByUsername(userReq.getUsername());
		if(existUser != null) {
			return OperationResult.newFailure(CodeMsg.USER_EXIST);
		}
		User user = new User();
		BeanUtils.copyProperties(userReq, user);
		user.setId(UUIDUtil.get32UUID());
		user.setPassword(MD5Util.encode32(user.getPassword()));
		user.setCreatetime(new Date());
		userMapper.insertSelective(user);
		if(StringUtils.isNotEmpty(userReq.getRoleIds())) {
			UserRole ur = new UserRole();
			for(String roleId : userReq.getRoleIds().split(",")) {
				ur.setUserId(user.getId());
				ur.setRoleId(roleId);
				userRoleMapper.insert(ur);
			}
		}
		return OperationResult.newSuccess();
	}

	@Override
	public OperationResult editUser(UserReq userReq) {
		// TODO Auto-generated method stub
		User existUser = userMapper.selectByPrimaryKey(userReq.getId());
		if(existUser == null) {
			return OperationResult.newFailure(CodeMsg.USER_NO_EXIST);
		}
		if(!StringUtils.isEmpty(userReq.getPassword())){
			userReq.setPassword(MD5Util.encode32(userReq.getPassword()));
		}
		userReq.setUsername(null);//不允许修改用户名
		User user = new User();
		BeanUtils.copyProperties(userReq, user);
		userMapper.updateByPrimaryKeySelective(user);
		if(StringUtils.isNotEmpty(userReq.getRoleIds())) {
			userRoleMapper.deleteUserRoleByUserId(user.getId());
			UserRole ur = new UserRole();
			for(String roleId : userReq.getRoleIds().split(",")) {
				ur.setUserId(user.getId());
				ur.setRoleId(roleId);
				userRoleMapper.insert(ur);
			}
		}
		return OperationResult.newSuccess();
	}

	@Override
	public OperationResult bacthDeleteUser(String ids) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(ids)) {
			List<String> idList = Arrays.asList(ids.split(","));
			userMapper.batchDelete(idList);
			for(String id : idList) {
				userRoleMapper.deleteUserRoleByUserId(id);
			}
		}
		return OperationResult.newSuccess();
	}

	@Override
	@Transactional
	public OperationResult deleteUser(String id) {
		// TODO Auto-generated method stub
		userMapper.deleteByPrimaryKey(id);
		userRoleMapper.deleteUserRoleByUserId(id);
		return OperationResult.newSuccess();
	}

	@Override
	public ServiceResult<User> getUser(String id) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(id);
		List<String> roles = userRoleMapper.selectRoleIdByUserId(id);
		user.setRoles(roles);
		return ServiceResult.newSuccess(user);
	}

	@Override
	public OperationResult editPassword(EditPasswordReq req) {
		// TODO Auto-generated method stub
		User user = AccessTokenUtil.getUser();
		if(user == null) {
			return OperationResult.newFailure(CodeMsg.LOGIN_PLEASE);
		}
		if(!user.getPassword().equals(MD5Util.encode32(req.getOldPassword()))) {
			return OperationResult.newFailure(CodeMsg.OLDPASSWORD_WRONG);
		}
		User editUser = new User();
		editUser.setId(user.getId());
		editUser.setPassword(MD5Util.encode32(req.getNewPassword()));
		user.setPassword(MD5Util.encode32(req.getNewPassword()));
		userMapper.updateByPrimaryKeySelective(editUser);
		return OperationResult.newSuccess();
	}
	
}
