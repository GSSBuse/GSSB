/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2014  , MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.thinkgem.jeesite.common.wx.plugin;

/**
 * 插件接口
 * @author 
 *
 */
public interface Plugin {
	public void run(Object... obj);

    public Boolean install();

    public Boolean uninstall();

    public Boolean upgrade();
}
