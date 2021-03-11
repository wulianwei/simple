package com.prac.simple.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.prac.simple.constant.CodeMsg;
import com.prac.simple.constant.CommonConstant;
import com.prac.simple.entity.Device;
import com.prac.simple.entity.req.DeviceReq;
import com.prac.simple.mapper.DeviceMapper;
import com.prac.simple.service.DeviceService;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceMapper deviceMapper;
	
	@Override
	public PageResult<List<Device>> searchDevice(DeviceReq req) {
		// TODO Auto-generated method stub
		Page<Object> page = PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<Device> list = deviceMapper.selectDevice(req);
		PageResult<List<Device>> result = PageResult.newSuccess(list);
		result.setTotal((int) page.getTotal());
		return result;
	}

	@Override
	public OperationResult addDevice(Device record) {
		// TODO Auto-generated method stub
		Device existD = deviceMapper.selectByPrimaryKey(record.getMac());
		if(existD != null) {
			return OperationResult.newFailure(CodeMsg.DEVICE_EXIST);
		}
		record.setOpen(CommonConstant.DEVICE_OFF);
		Date now = new Date();
		record.setCreatetime(now);
		record.setModifytime(now);
		deviceMapper.insertSelective(record);
		return OperationResult.newSuccess();
	}

	@Override
	public OperationResult editDevice(Device record) {
		// TODO Auto-generated method stub
		record.setModifytime(new Date());
		deviceMapper.updateByPrimaryKeySelective(record);
		return OperationResult.newSuccess();
	}

	@Override
	public OperationResult deleteDevice(String mac) {
		// TODO Auto-generated method stub
		deviceMapper.deleteByPrimaryKey(mac);
		return OperationResult.newSuccess();
	}
	
	@Override
	public ServiceResult<Device> getDevice(Device record) {
		// TODO Auto-generated method stub
		Device device = deviceMapper.selectByPrimaryKey(record.getMac());
		return ServiceResult.newSuccess(device);
	}

	@Override
	public OperationResult switchDevice(DeviceReq req) {
		// TODO Auto-generated method stub
		Device device = new Device();
		device.setMac(req.getMac());
		device.setOpen(req.getOpen());
		deviceMapper.updateByPrimaryKeySelective(device);
		return OperationResult.newSuccess();
	}

	

}
