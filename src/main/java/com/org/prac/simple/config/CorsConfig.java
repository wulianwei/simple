package com.org.prac.simple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 
 * @Description:  跨域配置   
 * @author: Administrator     
 * @date:   2020-04-29 11:43
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
					.allowCredentials(true)
					.maxAge(3600)
					.allowedHeaders("*");
	}
}
