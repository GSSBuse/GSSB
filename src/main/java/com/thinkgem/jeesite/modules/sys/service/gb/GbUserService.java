/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbUser;
import com.thinkgem.jeesite.modules.sys.dao.gb.GbUserDao;

/**
 * 用户信息管理Service
 * @author 管理员
 * @version 2017-11-06
 */
@Service
@Transactional(readOnly = true)
public class GbUserService extends CrudService<GbUserDao, GbUser> {

	public GbUser get(String id) {
		return super.get(id);
	}
	
	public List<GbUser> findList(GbUser gbUser) {
		return super.findList(gbUser);
	}
	
	public Page<GbUser> findPage(Page<GbUser> page, GbUser gbUser) {
		return super.findPage(page, gbUser);
	}
	
	@Transactional(readOnly = false)
	public void save(GbUser gbUser) {
		super.save(gbUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbUser gbUser) {
		super.delete(gbUser);
	}
	
}