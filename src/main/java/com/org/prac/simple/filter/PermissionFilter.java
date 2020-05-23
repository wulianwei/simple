package com.org.prac.simple.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
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
import com.org.prac.simple.constant.CodeMsg;
import com.org.prac.simple.constant.CommonConstant;
import com.org.prac.simple.entity.User;
import com.org.prac.simple.init.DataInit;
import com.org.prac.simple.util.AccessTokenUtil;
import com.org.prac.simple.util.OperationResult;

@WebFilter(filterName = "permissionFilter",urlPatterns = "/*")
public class PermissionFilter implements Filter,Ordered{
	
	private Logger logger = LoggerFactory.getLogger(PermissionFilter.class);

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
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
		//dataInit.initPermission();   //开启即刻权限校验
		String uri  = ((HttpServletRequest)request).getRequestURI();
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
		String accessToken = AccessTokenUtil.getAccessToken();
		//非配置路径直接放行
		if(!DataInit.permissionMap.keySet().contains(uri)) {
			try {
				chain.doFilter(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("请求异常", e);
				PrintWriter out = response.getWriter();
				out.print(JSON.toJSONString(OperationResult.newFailure(CodeMsg.FAILURE)));
			}
			return;
		}
		if(StringUtils.isEmpty(accessToken)) {
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(OperationResult.newFailure(CodeMsg.LOGIN_PLEASE)));
			out.close();
			return;
		}
		//用户认证
		User user = (User) redisTemplate.opsForValue().get(accessToken);
		if(user == null) {
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(OperationResult.newFailure(CodeMsg.RELOGIN)));
			out.close();
			return;
		}
		// 刷新登陆有效时间
		redisTemplate.expire(accessToken, CommonConstant.LOGIN_EXPIRE, TimeUnit.SECONDS);
		//权限校验
		Set<String> roles = DataInit.permissionMap.get(uri);
		List<String> userRoles = user.getRoles();
		String role = userRoles.stream().filter(item->roles.contains(item)).findAny().orElse(null);
		if(StringUtils.isEmpty(role)) {
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
