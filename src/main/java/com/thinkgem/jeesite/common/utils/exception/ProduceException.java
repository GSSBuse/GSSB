package com.thinkgem.jeesite.common.utils.exception;

/**
 * 生成错误异常
 * The Class ProduceException.<br>
 * 
 * @author "FNST)WeiCL"
 * @version 0.1
 */
public class ProduceException extends Exception {
	public ProduceException(String message) {
		super(message);
	}
	
	public ProduceException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public ProduceException(Throwable cause) {
        super(cause);
    }
}
