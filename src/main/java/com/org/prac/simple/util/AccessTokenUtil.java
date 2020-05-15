package com.org.prac.simple.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.org.prac.simple.entity.User;

@Component
public class AccessTokenUtil {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	public static String getAccessToken() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String token = request.getHeader("accessToken");
		return token;
	}
	
	public static User getUser() {
		@SuppressWarnings("unchecked")
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");
		User user = (User) redisTemplate.opsForValue().get(getAccessToken());
		return user;
	}
}
