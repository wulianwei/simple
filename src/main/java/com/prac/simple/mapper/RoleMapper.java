package com.prac.simple.mapper;

import java.util.List;

import com.prac.simple.entity.Role;
import com.prac.simple.entity.req.RoleReq;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectRoleByUserId(String userId);
    
    List<Role> selectAllRole();
    
    List<Role> selectRole(RoleReq req);
}