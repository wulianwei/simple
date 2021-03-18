package com.prac.simple.constant;

public final class CommonConstant {
	public static final Integer ZERO = 0;
	public static final long LOGIN_EXPIRE = 30*60L; // 登陆有效时间
	
	public static final String ROLE_MANAGER = "manager"; // 管理员角色
	
	public static final String PERMISSION_TYPE_MAINMENU = "01"; //主菜单
	public static final String PERMISSION_TYPE_SUBMENU = "02"; //子菜单
	public static final String PERMISSION_TYPE_BUTTON = "03"; //按钮
	
	public static final String DEVICE_ON= "1"; //设备开
	public static final String DEVICE_OFF = "0";//设备关
	
	public static final Integer MQTT_QOS0 = 0;
	public static final Integer MQTT_QOS1 = 1;
	public static final Integer MQTT_QOS2 = 2;
	public static final String MQTT_PUBLISH_PREFIX = "device/";
	public static final String MQTT_SUB_TOPIC_SIMPLE = "simple";
}
