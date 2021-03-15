package com.prac.simple.service;

import java.util.List;

import com.prac.simple.entity.Device;
import com.prac.simple.entity.req.DeviceReq;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;

public interface DeviceService {
	
	PageResult<List<Device>> searchDevice(DeviceReq req);
	
	OperationResult addDevice(Device record);
	
	OperationResult editDevice(Device record);
	
	OperationResult deleteDevice(String mac);
	
	ServiceResult<Device> getDevice(Device record);
	
	OperationResult switchDevice(DeviceReq req);
	
	OperationResult changeOpen(String mac,String open);

}
