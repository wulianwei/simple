package com.org.prac.simple.mapper;

import java.util.List;

import com.org.prac.simple.entity.User;
import com.org.prac.simple.entity.req.UserReq;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectUserByUsername(String userName);
    
    List<User> selectUser(UserReq req);
}