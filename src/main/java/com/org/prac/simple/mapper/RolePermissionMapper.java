package com.org.prac.simple.mapper;

import java.util.List;

import com.org.prac.simple.entity.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
    
    List<String> selectPermissionIdByRoleId(String roleId);
    
    int batchDeleteRolePermissionByPermissionId(List<String> ids);
    
    int deleteRolePermissionByRoleId(String roleId);
}