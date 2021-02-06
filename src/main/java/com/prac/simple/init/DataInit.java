package com.prac.simple.init;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.prac.simple.entity.RolePermission;
import com.prac.simple.mapper.PermissionMapper;
/**
 * @Description:   初始数据
 * @author: Administrator     
 * @date:   2020-04-29 11:44
 */
@Component
public class DataInit implements ApplicationRunner,Ordered{
	
	private static Logger logger = LoggerFactory.getLogger(DataInit.class);
	public static Map<String,Set<String>> permissionMap = new HashMap<String,Set<String>>(16);  //角色资源关系数据
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		 logger.info("初始化数据开始>>>>>>");
		 initPermission();
		 logger.info("初始化数据结束>>>>>>");
	}
	
	/**
	 * @Description:    初始化角色资源关系   
	 * @author: Administrator     
	 * @date:   2020-04-29 11:45  
	 * @return void
	 */
	public void initPermission() {
		List<RolePermission> rolePermissions = permissionMapper.selectRolePermissions();
		permissionMap = rolePermissions.stream().collect(Collectors.toMap(RolePermission::getUrl, item->{
			Set<String> roles = new HashSet<String>(16);
			roles.add(item.getRoleId());
			return roles;
		},
		(Set<String> value1,Set<String> value2)->{
			value1.addAll(value2);
			return value1;
		}
		));
	}
}
