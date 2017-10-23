/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.wx.dao.WxInterfaceInfoDao;
import com.thinkgem.jeesite.modules.wx.entity.WxInterfaceInfo;

/**
 * 微信同步Service
 * @author shenzb.fnst
 * @version 2015-07-27
 */
@Service
@Transactional(readOnly = true)
public class SyncInfoService extends CrudService<WxInterfaceInfoDao, WxInterfaceInfo> {

	@Autowired
	private OfficeDao officeDao;

	/**
	 * 根据当前用户公司编号获取机构列表
	 * @param companyId
	 * @return
	 */
	public List<Office> getOfficeListByCompanyId(String companyId) {
		return officeDao.getOfficeListByCompanyId(companyId);
	}

}