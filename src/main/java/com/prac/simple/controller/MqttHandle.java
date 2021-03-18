package com.prac.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.prac.simple.constant.CommonConstant;
import com.prac.simple.service.DeviceService;

@Component
public class MqttHandle {
	
	@Autowired
	private DeviceService deviceService;
	
	public void handle(String topic,String message) {
		JSONObject json = JSONObject.parseObject(message);
		switch (topic) {
		case CommonConstant.MQTT_SUB_TOPIC_SIMPLE:
			deviceService.changeOpen(json.getString("mac"), json.getString("open"));
			break;
		default:
			break;
		}
	}

}