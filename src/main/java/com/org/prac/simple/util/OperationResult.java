package com.org.prac.simple.util;

import java.io.Serializable;

import com.org.prac.simple.constant.CodeMsg;

/**
 * @date 2018/07/16 操作类返回参数
 */
public class OperationResult  implements Serializable{

    /**  */
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;

    /**
     * 
     */
    public OperationResult() {
        super();
    }

    public OperationResult(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getRetCode();
        this.msg = cm.getMessage();
    }

    /**
     * 成功时候的调用
     */
    public static  OperationResult newSuccess() {
        OperationResult rlt = new OperationResult(CodeMsg.SUCCESS);
        return rlt;
    }

    /**
     * 失败时候的调用
     */
    public static  OperationResult newFailure(CodeMsg cm) {
        return new OperationResult(cm);
    }
    
    public static  OperationResult newFailure(String msg){
    	CodeMsg  cm=new CodeMsg(CodeMsg.FAILED_CODE, msg);
    	return newFailure(cm);
    }

    /**
     * 失败时候的调用,扩展消息参数
     */
    public static  OperationResult newFailure(CodeMsg cm, String msg) {
        cm.setMessage(cm.getMessage() + "--" + msg);
        return new OperationResult(cm);
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
     * 判断返回结果是否成功
     */
    public boolean success() {
        return CodeMsg.SUCCESS_CODE == code;
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
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OperationResult [code=" + code + ", msg=" + msg + "]";
    }

    /**
     * @see java.lang.Object#toString()
     */

}
