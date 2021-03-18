package com.prac.simple.config;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prac.simple.controller.MqttHandle;

@Component
public class Callback implements MqttCallback {
	 
	private static Logger logger = LoggerFactory.getLogger(Callback.class);
	
	@Autowired
	private MqttHandle mqttHandle;
	
	@Autowired
	private MqttClient mqttClient;
	
    /**
     * MQTT 断开连接会执行此方法
     */
    @Override
    public void connectionLost(Throwable throwable) {
    	logger.info("断开了MQTT连接 ：{}", throwable.getMessage());
    	while(true) {
    		try {
    			Thread.sleep(3000);
				mqttClient.reconnect();
				logger.info("MQTT重新连接成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
 
    /**
     * publish发布成功后会执行到这里
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    	logger.info("发布消息成功");
    }
 
    /**
     * subscribe订阅后得到的消息会执行到这里
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        //  TODO    此处可以将订阅得到的消息进行业务处理、数据存储
    	logger.info("收到来自 " + topic + " 的消息：{}", new String(message.getPayload()));
    	try {
    		mqttHandle.handle(topic,new String(message.getPayload()));
    	}catch (Exception e) {
			// TODO: handle exception
    		logger.error("处理消息失败",e);
		}
    	
    }
}