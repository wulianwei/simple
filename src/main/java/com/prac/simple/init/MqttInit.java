package com.prac.simple.init;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.prac.simple.config.Callback;
import com.prac.simple.constant.CommonConstant;

@Component
public class MqttInit  implements ApplicationRunner,Ordered{
	
	private static Logger logger = LoggerFactory.getLogger(MqttInit.class);

	@Autowired
	private MqttClient mqttClient;
	
	@Autowired
	private Callback callback;
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("mqtt初始化>>>>");
		mqttClient.setCallback(callback);
		mqttClient.subscribe(CommonConstant.MQTT_SUB_TOPIC_SIMPLE);
	}

}
