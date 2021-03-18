package com.prac.simple.util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MqttUtil.class);
	
	@Autowired
	private MqttClient mqttClient;
	
	/**
     * 向某个主题发布消息 默认qos：1
     *
     * @param topic:发布的主题
     * @param msg：发布的消息
     */
    public void pub(String topic, String msg) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(msg.getBytes());
        try {
        	MqttTopic mqttTopic = mqttClient.getTopic(topic);
            MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
            token.waitForCompletion();
        }catch (MqttException e) {
			// TODO: handle exception
        	logger.error("发布mqtt消息异常 topic:"+topic+";payload:"+msg, e);
		}
    }
    
    /**
     * 向某个主题发布消息
     *
     * @param topic: 发布的主题
     * @param msg:   发布的消息
     * @param qos:   消息质量    Qos：0、1、2
     */
    public void pub(String topic, String msg, int qos) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setPayload(msg.getBytes());
        try {
        	MqttTopic mqttTopic = mqttClient.getTopic(topic);
            MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
            token.waitForCompletion();
        }catch (MqttException e) {
			// TODO: handle exception
        	logger.error("发布mqtt消息异常 topic:"+topic+";payload:"+msg, e);
		}
    }
    
    /**
     * 订阅某一个主题 ，此方法默认的的Qos等级为：1
     *
     * @param topic 主题
     */
    public void sub(String topic) {
        try {
			mqttClient.subscribe(topic);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			logger.error("订阅主题消息异常 topic:"+topic, e);
		}
    }
 
    /**
     * 订阅某一个主题，可携带Qos
     *
     * @param topic 所要订阅的主题
     * @param qos   消息质量：0、1、2
     */
    public void sub(String topic, int qos) {
        try {
			mqttClient.subscribe(topic, qos);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			logger.error("订阅主题消息异常 topic:"+topic, e);
		}
    }

}
