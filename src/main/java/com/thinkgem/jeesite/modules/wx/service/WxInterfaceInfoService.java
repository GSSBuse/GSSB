/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wx.entity.WxInterfaceInfo;
import com.thinkgem.jeesite.modules.wx.dao.WxInterfaceInfoDao;

/**
 * 接口验证信息Service
 * @author shenzb.fnst
 * @version 2015-07-27
 */
@Service
@Transactional(readOnly = true)
public class WxInterfaceInfoService extends CrudService<WxInterfaceInfoDao, WxInterfaceInfo> {

	@Autowired
	WxInterfaceInfoDao wxInterfaceInfoDao;
	
	public WxInterfaceInfo get(String id) {
		return super.get(id);
	}
	
	public List<WxInterfaceInfo> findList(WxInterfaceInfo wxInterfaceInfo) {
		return super.findList(wxInterfaceInfo);
	}
	
	public Page<WxInterfaceInfo> findPage(Page<WxInterfaceInfo> page, WxInterfaceInfo wxInterfaceInfo) {
		return super.findPage(page, wxInterfaceInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(WxInterfaceInfo wxInterfaceInfo) {
		super.save(wxInterfaceInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxInterfaceInfo wxInterfaceInfo) {
		super.delete(wxInterfaceInfo);
	}
	
	public WxInterfaceInfo getWxInterfaceInfoByCompanyId(String officeId) {
		return wxInterfaceInfoDao.getWxInterfaceInfoByCompanyId(officeId);
	}
	
}