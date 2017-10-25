package com.thinkgem.jeesite.common.bean;

import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

/**
 * Ajax调用结果
 * The Class AjaxResult.<br>
 * 
 * @author FNST)WeiCL
 * @version 1.0
 */
public class AjaxResult {
	
	public final static String SUCCESS = "success";
	
	public static AjaxResult makeSuccess(String msg) {
		return new AjaxResult().setType("success").setMsg(msg);
	}
	
	public static AjaxResult makeWarn(String msg) {
		return new AjaxResult().setType("warn").setMsg(msg);
	}
	
	public static AjaxResult makeError(String msg) {
		return new AjaxResult().setType("error").setMsg(msg);
	}
	
	/**
	 * 返回类型，没错的时候为success,警告 warn,错误 error
	 */
	private String type="success";
	/**
	 * 消息，如果页面上不显示，可以忽略
	 */
	private String msg="";
	/**
	 * 数据信息
	 */
	private Map<String, Object> data = new HashMap<String, Object>();

	
	/**
	 * 返回类型的取得
	 * @return 错误代码，没错的时候为ok,警告 warn,错误 error
	 */
	public String getType() {
	    return type;
	}
	/**
	 * 返回类型的设定
	 * @param type 错误代码，没错的时候为ok,警告 warn,错误 error
	 */
	public AjaxResult setType(String type) {
	    this.type = type;
	    return this;
	}
	/**
	 * 消息的取得
	 * @return 消息，如果页面上不显示，可以忽略
	 */
	public String getMsg() {
	    return msg;
	}
	/**
	 * 消息的设定
	 * @param msg 消息，如果页面上不显示，可以忽略
	 */
	public AjaxResult setMsg(String msg) {
	    this.msg = msg;
	    return this;
	}
	/**
	 * 数据信息的取得
	 * @return 数据信息
	 */
	public Map<String,Object> getData() {
	    return data;
	}
	/**
	 * 数据信息的设定
	 * @param data 数据信息
	 */
	public AjaxResult setData(Map<String,Object> data) {
	    this.data = data;
	    return this;
	}
	
	@Override
	public String toString() {
		return Json.toJson(this);
	}
}
