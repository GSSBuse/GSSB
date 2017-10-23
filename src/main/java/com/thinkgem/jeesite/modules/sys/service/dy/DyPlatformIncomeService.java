/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformIncome;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyPlatformIncomeDao;

/**
 * 平台收入管理Service
 * @author quanyf.fnst
 * @version 2015-12-15
 */
@Service
@Transactional(readOnly = true)
public class DyPlatformIncomeService extends CrudService<DyPlatformIncomeDao, DyPlatformIncome> {

	public DyPlatformIncome get(String id) {
		return super.get(id);
	}
	
	public List<DyPlatformIncome> findList(DyPlatformIncome dyPlatformIncome) {
		return super.findList(dyPlatformIncome);
	}
	
	public Page<DyPlatformIncome> findPage(Page<DyPlatformIncome> page, DyPlatformIncome dyPlatformIncome) {
		return super.findPage(page, dyPlatformIncome);
	}
	
	@Transactional(readOnly = false)
	public void save(DyPlatformIncome dyPlatformIncome) {
		super.save(dyPlatformIncome);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyPlatformIncome dyPlatformIncome) {
		super.delete(dyPlatformIncome);
	}
	
}