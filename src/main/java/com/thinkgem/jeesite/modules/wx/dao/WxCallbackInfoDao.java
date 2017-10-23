/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wx.entity.WxCallbackInfo;

/**
 * 回调验证信息DAO接口
 * @author shenzb.fnst
 * @version 2015-08-04
 */
@MyBatisDao
public interface WxCallbackInfoDao extends CrudDao<WxCallbackInfo> {
	
}