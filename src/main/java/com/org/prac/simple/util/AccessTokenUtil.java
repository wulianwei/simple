package com.org.prac.simple.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.org.prac.simple.entity.User;

public class AccessTokenUtil {
	
	public static String getAccessToken() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String token = request.getHeader("accessToken");
		return token;
	}
	
	public static User getUser() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		User user = (User) request.getAttribute("loginUser");
		return user;
	}
}
