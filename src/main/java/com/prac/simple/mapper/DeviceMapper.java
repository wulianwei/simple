package com.prac.simple.mapper;

import java.util.List;

import com.prac.simple.entity.Device;
import com.prac.simple.entity.req.DeviceReq;

public interface DeviceMapper {
    int deleteByPrimaryKey(String mac);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String mac);
    
    List<Device> selectDevice(DeviceReq req);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}