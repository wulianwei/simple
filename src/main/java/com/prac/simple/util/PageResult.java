package com.prac.simple.util;

import java.io.Serializable;

import com.prac.simple.constant.CodeMsg;

public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer code;
	private String msg;
	private Integer total;
	private T data;

	public PageResult() {
		super();
	}

	public PageResult(CodeMsg cm) {
		if (cm == null) {
			return;
		}
		this.code = cm.getRetCode();
		this.msg = cm.getMessage();
	}

	private PageResult(T data) {
		this.code = CodeMsg.SUCCESS_CODE;
		this.msg = CodeMsg.SUCCESS_DESC;
		this.data = data;
	}

	/**
	 * 成功时候的调用
	 */
	public static <T> PageResult<T> newSuccess(T data) {
		return new PageResult<T>(data);
	}

	/**
	 * 成功，传入成功提示
	 */
	public static <T> PageResult<T> newSuccess(CodeMsg cm) {
		return new PageResult<T>(cm);
	}
	
	/**
	 * 成功，不需要传入参数
	 */
	@SuppressWarnings("unchecked")
	public static <T> PageResult<T> newSuccess() {
		return (PageResult<T>) newSuccess("");
	}

	/**
	 * 失败时候的调用
	 */
	public static <T> PageResult<T> newFailure(CodeMsg cm) {
		return new PageResult<T>(cm);
	}

	/**
	 * 失败时候的调用,扩展消息参数
	 */
	public static <T> PageResult<T> newFailure(CodeMsg cm, String msg) {
		cm.setMessage(cm.getMessage() + "--" + msg);
		return new PageResult<T>(cm);
	}

	/**
	 * 失败时候的调用,扩展消息参数
	 */
	public static <T> PageResult<T> newFailure(String msg) {
		CodeMsg cm = new CodeMsg(CodeMsg.FAILED_CODE, msg);
		return new PageResult<T>(cm);
	}

	/**
	 * 判断返回结果是否成功
	 */
	public boolean success() {
		return CodeMsg.SUCCESS_CODE == code;
	}

	/**
	 * Getter method for property <tt>code</tt>.
	 * 
	 * @return property value of code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Setter method for property <tt>code</tt>.
	 * 
	 * @param code value to be assigned to property code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Getter method for property <tt>msg</tt>.
	 * 
	 * @return property value of msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Setter method for property <tt>msg</tt>.
	 * 
	 * @param msg value to be assigned to property msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Getter method for property <tt>data</tt>.
	 * 
	 * @return property value of data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Setter method for property <tt>data</tt>.
	 * 
	 * @param data value to be assigned to property data
	 */
	public void setData(T data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PageResult [code=" + code + ", msg=" + msg + ", total=" + total + ", data=" + data + "]";
	}

}
