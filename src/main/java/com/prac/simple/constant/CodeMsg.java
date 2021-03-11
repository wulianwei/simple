package com.prac.simple.constant;

/**
 * @Description:    提示信息
 * @author: Administrator     
 * @date:   2020年4月29日 上午11:17:55
 */
public class CodeMsg {

	private Integer retCode;
	private String message;

	// 成功
	public static final Integer SUCCESS_CODE = 0;
	public static final String SUCCESS_DESC = "SUCCESS";

	// 失败
	public static final Integer FAILED_CODE = -1;

	// 按照模块定义CodeMsg
	// 通用异常
	public static CodeMsg SUCCESS = new CodeMsg(SUCCESS_CODE, SUCCESS_DESC);
	public static CodeMsg FAILURE = new CodeMsg(FAILED_CODE, "系统繁忙");
	public static CodeMsg LOGINSUCCESS = new CodeMsg(SUCCESS_CODE, "登录成功");
	public static CodeMsg LOGINFAILED = new CodeMsg(FAILED_CODE, "登陆失败");
	public static CodeMsg SERVER_EXCEPTION = new CodeMsg(10001, "系统繁忙，请稍后再试");
	public static CodeMsg RELOGIN = new CodeMsg(10002, "请重新登陆");
	public static CodeMsg REQUEST_DENY = new CodeMsg(10003, "无权访问");
	public static CodeMsg PARAMETER_ISNULL = new CodeMsg(10004, "输入参数为空");
	public static CodeMsg USERNAME_WRONG = new CodeMsg(10005, "用户名错误");
	public static CodeMsg PASSWORD_WRONG = new CodeMsg(10006, "用户名错误");
	public static CodeMsg LOGIN_PLEASE= new CodeMsg(10007, "请登陆");
	public static CodeMsg NO_RIGHT= new CodeMsg(10008, "无权访问");
	public static CodeMsg USER_EXIST= new CodeMsg(10009, "用户已存在");
	public static CodeMsg USER_NO_EXIST= new CodeMsg(10010, "用户不存在");
	public static CodeMsg PERMISSION_EXIST= new CodeMsg(10011, "资源已存在");
	public static CodeMsg ROLE_EXIST= new CodeMsg(10012, "角色已存在");
	public static CodeMsg CHILDREN_PERMISSION_EXIST= new CodeMsg(10013, "存在子功能");
	public static CodeMsg OLDPASSWORD_WRONG= new CodeMsg(10014, "旧密码不正确");
	public static CodeMsg DEVICE_EXIST= new CodeMsg(10015, "设备已存在");
	
	

	public CodeMsg(int retCode, String message) {
		this.retCode = retCode;
		this.message = message;
	}

	public int getRetCode() {
		return retCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CodeMsg [retCode=" + retCode + ", message=" + message + "]";
	}
}
