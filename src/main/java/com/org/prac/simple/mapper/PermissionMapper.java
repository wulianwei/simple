package com.org.prac.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.org.prac.simple.entity.Permission;
import com.org.prac.simple.entity.RolePermission;


public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> selectPermission();
    
    List<RolePermission> selectRolePermissions();
    
    List<Permission> selectPermissionByUserId(String userId);
    
    List<Permission> selectPermissionByRoleId(String roleId);
    
    Permission selectPermissionByUrl(String url);
    
    Permission selectExistPermissionByUrl(@Param("url") String url, @Param("id") String id);
    
    List<Permission> selectChildrenPermission(String id);
    
    int batchDeletePermission(List<String> ids);
}