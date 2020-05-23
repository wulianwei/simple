package com.org.prac.simple.mapper;

import java.util.List;

import com.org.prac.simple.entity.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
    
    int deleteUserRoleByRoleId(String roleId);
    
    int deleteUserRoleByUserId(String userId);
    
    List<String> selectRoleIdByUserId(String userId);
}