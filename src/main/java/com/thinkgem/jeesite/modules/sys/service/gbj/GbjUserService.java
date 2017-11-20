/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUser;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserDao;

/**
 * 用户信息表Service
 * @author snnu
 * @version 2017-11-19
 */
@Service
@Transactional(readOnly = true)
public class GbjUserService extends CrudService<GbjUserDao, GbjUser> {

	public GbjUser get(String id) {
		return super.get(id);
	}
	
	public List<GbjUser> findList(GbjUser gbjUser) {
		return super.findList(gbjUser);
	}
	
	public Page<GbjUser> findPage(Page<GbjUser> page, GbjUser gbjUser) {
		return super.findPage(page, gbjUser);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjUser gbjUser) {
		super.save(gbjUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjUser gbjUser) {
		super.delete(gbjUser);
	}
	
}