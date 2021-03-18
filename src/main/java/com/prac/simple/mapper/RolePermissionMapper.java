package com.prac.simple.mapper;

import java.util.List;

import com.prac.simple.entity.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
    
    List<String> selectPermissionIdByRoleId(String roleId);
    
    int batchDeleteRolePermissionByPermissionId(List<String> ids);
    
    int deleteRolePermissionByPermissionId(String id);
    
    int deleteRolePermissionByRoleId(String roleId);
    
    List<String> selectRoleIdByUrl(String url);
}