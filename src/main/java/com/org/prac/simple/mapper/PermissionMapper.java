package com.org.prac.simple.mapper;

import java.util.List;

import com.org.prac.simple.entity.Permission;
import com.org.prac.simple.entity.RolePermission;


public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<RolePermission> selectRolePermissions();
    
    List<Permission> selectPermissionsByUserId(String userId);
}