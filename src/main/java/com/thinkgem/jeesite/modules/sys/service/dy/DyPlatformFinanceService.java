/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformFinance;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyPlatformFinanceDao;

/**
 * 平台总账户管理Service
 * @author quanyf.fnst
 * @version 2015-12-15
 */
@Service
@Transactional(readOnly = true)
public class DyPlatformFinanceService extends CrudService<DyPlatformFinanceDao, DyPlatformFinance> {

	public DyPlatformFinance get(String id) {
		return super.get(id);
	}
	
	public List<DyPlatformFinance> findList(DyPlatformFinance dyPlatformFinance) {
		return super.findList(dyPlatformFinance);
	}
	
	public Page<DyPlatformFinance> findPage(Page<DyPlatformFinance> page, DyPlatformFinance dyPlatformFinance) {
		return super.findPage(page, dyPlatformFinance);
	}
	
	@Transactional(readOnly = false)
	public void save(DyPlatformFinance dyPlatformFinance) {
		super.save(dyPlatformFinance);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyPlatformFinance dyPlatformFinance) {
		super.delete(dyPlatformFinance);
	}
	
}