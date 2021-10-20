package com.prac.simple.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "mqtt.switch", havingValue = "on")
public class MqttConfig{
	    
	@Value("${mqtt.hostUrl}")
    private String hostUrl;
	
	@Value("${mqtt.clientId}")
    private String clientId;
	
	@Value("${mqtt.username}")
    private String username;
	
	@Value("${mqtt.password}")
    private String password;
	
	@Value("${mqtt.timeout}")
    private Integer timeout;
	
	@Value("${mqtt.keepalive}")
    private Integer keepalive;
	
        
 
    /**
     * 客户端mqttClient
     **/
    @Bean
    public MqttClient mqttClient(@Qualifier("connectOptions") MqttConnectOptions connectOptions) throws MqttException {
    	 MqttClient mqttClient = new MqttClient(hostUrl, clientId, new MemoryPersistence());
         mqttClient.connect(connectOptions);
         return mqttClient;
    }
    
    @Bean
    public MqttConnectOptions connectOptions() {
    	MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(timeout);///默认：30
        options.setAutomaticReconnect(true);//默认：false
        options.setCleanSession(false);//默认：true
        options.setKeepAliveInterval(keepalive);//默认：60
        return options;
    }

}


