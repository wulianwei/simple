package com.prac.simple.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.prac.simple.constant.CodeMsg;
import com.prac.simple.constant.CommonConstant;
import com.prac.simple.entity.User;
import com.prac.simple.mapper.PermissionMapper;
import com.prac.simple.mapper.RolePermissionMapper;
import com.prac.simple.util.AccessTokenUtil;
import com.prac.simple.util.OperationResult;

@WebFilter(filterName = "permissionFilter",urlPatterns = "/*")
public class PermissionFilter implements Filter,Ordered{
	
	private Logger logger = LoggerFactory.getLogger(PermissionFilter.class);
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
//	@Autowired
//	private DataInit dataInit;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String uri  = ((HttpServletRequest)request).getRequestURI();
		if(uri.startsWith("/")) {
			uri = uri.substring(1);
		}
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
		String accessToken = AccessTokenUtil.getAccessToken();
		User user = null;
		
		if(StringUtils.isNoneEmpty(accessToken)) {// 刷新登陆有效时间
			user = (User) redisTemplate.opsForValue().get(accessToken);
			if(user != null) {
				redisTemplate.expire(accessToken, CommonConstant.LOGIN_EXPIRE, TimeUnit.SECONDS);
			}
		}
		//非配置路径直接放行
		List<String> permissionUrlList = permissionMapper.selectAllPermissionUrl();
		if(!permissionUrlList.contains(uri)) {
			try {
				chain.doFilter(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("请求异常", e);
				PrintWriter out = response.getWriter();
				out.print(JSON.toJSONString(OperationResult.newFailure(CodeMsg.FAILURE)));
				out.close();
			}
			return;
		}
		//未登录用户
		if(StringUtils.isEmpty(accessToken)) {
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(OperationResult.newFailure(CodeMsg.LOGIN_PLEASE)));
			out.close();
			return;
		}
		//用户认证
		if(user == null) {
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(OperationResult.newFailure(CodeMsg.RELOGIN)));
			out.close();
			return;
		}
		//权限校验
		List<String> roles = rolePermissionMapper.selectRoleIdByUrl(uri);
		List<String> userRoles = user.getRoles();
		boolean rightFlag = false;
		if(roles!=null && roles.size()>0) {
			for(String role : roles) {
				if(userRoles.contains(role)) {
					rightFlag = true;
					break;
				}
			}
		}
		if(!rightFlag) {
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(OperationResult.newFailure(CodeMsg.NO_RIGHT)));
			out.close();
		}else {
			try {
				chain.doFilter(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("请求异常", e);
				PrintWriter out = response.getWriter();
				out.print(JSON.toJSONString(OperationResult.newFailure(CodeMsg.FAILURE)));
				out.close();
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
