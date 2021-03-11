package com.prac.simple.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prac.simple.entity.Device;
import com.prac.simple.entity.req.DeviceReq;
import com.prac.simple.service.DeviceService;
import com.prac.simple.util.OperationResult;
import com.prac.simple.util.PageResult;
import com.prac.simple.util.ServiceResult;

@RestController
@RequestMapping("/device")
public class DeviceController {
	
	private static Logger logger = LoggerFactory.getLogger(DeviceController.class);
	
	@Autowired
	private DeviceService deviceService;
	
	/**
	 * @Description 查询设备
	 * @Author wulianwei
	 * @Date 2021-03-11 10:22
	 * @Return  PageResult<List<Device>>
	 */
	@PostMapping("/searchDevice")
	public PageResult<List<Device>> searchDevice(@RequestBody DeviceReq req){
		logger.info("searchDevice>>:{}",req);
		return deviceService.searchDevice(req);
	}
	
	/**
	 * @Description 添加设备
	 * @Author wulianwei
	 * @Date 2021-03-11 10:22
	 * @Return  OperationResult
	 */
	@PostMapping("/addDevice")
	public OperationResult addDevice(@RequestBody Device record){
		logger.info("addDevice>>:{}",record);
		return deviceService.addDevice(record);
	}
	
	/**
	 * @Description 修改设备
	 * @Author wulianwei
	 * @Date 2021-03-11 10:22
	 * @Return  OperationResult
	 */
	@PostMapping("/editDevice")
	public OperationResult editDevice(@RequestBody Device record){
		logger.info("editDevice>>:{}",record);
		return deviceService.editDevice(record);
	}
	
	
	/**
	 * @Description 删除设备
	 * @Author wulianwei
	 * @Date 2021-03-11 10:22
	 * @Return  OperationResult
	 */
	@PostMapping("/deleteDevice")
	public OperationResult deleteDevice(@RequestBody Device record){
		logger.info("deleteDevice>>:{}",record);
		return deviceService.deleteDevice(record.getMac());
	}
	
	/**
	 * @Description 设备详情
	 * @Author wulianwei
	 * @Date 2021-03-11 10:22
	 * @Return  OperationResult
	 */
	@PostMapping("/getDevice")
	public ServiceResult<Device> getDevice(@RequestBody Device record){
		logger.info("getDevice>>:{}",record);
		return deviceService.getDevice(record);
	}
	
	/**
	 * @Description 开关设备
	 * @Author wulianwei
	 * @Date 2021-03-11 10:22
	 * @Return  OperationResult
	 */
	@PostMapping("/switchDevice")
	public OperationResult switchDevice(@RequestBody DeviceReq req){
		logger.info("switchDevice>>:{}",req);
		return deviceService.switchDevice(req);
	}

}
