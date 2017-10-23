/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wx.entity.WxCallbackInfo;
import com.thinkgem.jeesite.modules.wx.dao.WxCallbackInfoDao;

/**
 * 回调验证信息Service
 * @author shenzb.fnst
 * @version 2015-08-04
 */
@Service
@Transactional(readOnly = true)
public class WxCallbackInfoService extends CrudService<WxCallbackInfoDao, WxCallbackInfo> {

	public WxCallbackInfo get(String id) {
		return super.get(id);
	}
	
	public List<WxCallbackInfo> findList(WxCallbackInfo wxCallbackInfo) {
		return super.findList(wxCallbackInfo);
	}
	
	public Page<WxCallbackInfo> findPage(Page<WxCallbackInfo> page, WxCallbackInfo wxCallbackInfo) {
		return super.findPage(page, wxCallbackInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(WxCallbackInfo wxCallbackInfo) {
		super.save(wxCallbackInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxCallbackInfo wxCallbackInfo) {
		super.delete(wxCallbackInfo);
	}
	
}