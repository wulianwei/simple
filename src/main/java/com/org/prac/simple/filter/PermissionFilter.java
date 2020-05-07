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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.org.prac.simple.constant.CodeMsg;
import com.org.prac.simple.constant.CommonConstant;
import com.org.prac.simple.entity.User;
import com.org.prac.simple.init.DataInit;
import com.org.prac.simple.util.AccessTokenUtil;

@WebFilter(filterName = "permissionFilter",urlPatterns = "/*")
public class PermissionFilter implements Filter,Ordered{

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String uri  = ((HttpServletRequest)request).getRequestURI();
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
		String accessToken = AccessTokenUtil.getAccessToken();
		//非配置路径直接放行
		if(!DataInit.permissionMap.keySet().contains(uri)) {
			chain.doFilter(request, response);
			return;
		}
		if(StringUtils.isEmpty(accessToken)) {
			response.setCharacterEncoding("utf-8");
	        response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(CodeMsg.LOGIN_AGAIN));
			out.close();
			return;
		}
		//用户认证
		User user = (User) redisTemplate.opsForValue().get(accessToken);
		if(user == null) {
			response.setCharacterEncoding("utf-8");
	        response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(CodeMsg.LOGIN_AGAIN));
			out.close();
			return;
		}
		request.setAttribute("loginUser", user);
		// 刷新登陆有效时间
		redisTemplate.expire(accessToken, CommonConstant.LOGIN_EXPIRE, TimeUnit.SECONDS);
		//权限校验
		Set<String> roles = DataInit.permissionMap.get(uri);
		List<String> userRoles = user.getRoles();
		String role = userRoles.stream().filter(item->roles.contains(item)).findAny().orElse(null);
		if(StringUtils.isEmpty(role)) {
			response.setCharacterEncoding("utf-8");
	        response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(CodeMsg.NO_RIGHT));
		}else {
			chain.doFilter(request, response);
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
