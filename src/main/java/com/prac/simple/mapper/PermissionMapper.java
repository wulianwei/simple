package com.prac.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.prac.simple.entity.Permission;
import com.prac.simple.entity.RolePermission;
import com.prac.simple.entity.req.PermissionReq;


public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> selectPermission(PermissionReq record);
    
    List<Permission> selectAllMenu();
    
    List<RolePermission> selectRolePermissions();
    
    List<Permission> selectPermissionByUserId(String userId);
    
    List<Permission> selectPermissionByRoleId(String roleId);
    
    List<String> selectAllPermissionUrl();
    
    Permission selectPermissionByUrl(String url);
    
    Permission selectPermissionByTitle(String title);
    
    Permission selectExistPermissionByUrl(@Param("url") String url, @Param("id") String id);
    
    List<Permission> selectChildrenPermission(String id);
    
    int batchDeletePermission(List<String> ids);
}