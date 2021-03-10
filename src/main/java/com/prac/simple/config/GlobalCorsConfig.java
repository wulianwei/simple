package com.prac.simple.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @Description:  跨域配置   
 * @author: Administrator     
 * @date:   2020-04-29 11:43
 */
@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
    //添加到容器中管理
	private CorsConfiguration buildConfig() {  
        CorsConfiguration corsConfiguration = new CorsConfiguration();  
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等） 
        return corsConfiguration;  
    }  

    @Bean  
    public FilterRegistrationBean<CorsFilter> corsFilter() {  
    	
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
        source.registerCorsConfiguration("/**", buildConfig()); // 4  
        
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
        corsBean.setName("crossOriginFilter");
        corsBean.setOrder(0);//这个顺序也有可能会有影响，尽量设置在拦截器前面
        return corsBean;  
    }  
}
