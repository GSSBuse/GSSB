/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbSold;
import com.thinkgem.jeesite.modules.sys.dao.gb.GbSoldDao;

/**
 * 卖标信息管理Service
 * @author 管理员
 * @version 2017-12-17
 */
@Service
@Transactional(readOnly = true)
public class GbSoldService extends CrudService<GbSoldDao, GbSold> {

	public GbSold get(String id) {
		return super.get(id);
	}
	
	public List<GbSold> findList(GbSold gbSold) {
		return super.findList(gbSold);
	}
	
	public Page<GbSold> findPage(Page<GbSold> page, GbSold gbSold) {
		return super.findPage(page, gbSold);
	}
	
	@Transactional(readOnly = false)
	public void save(GbSold gbSold) {
		super.save(gbSold);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbSold gbSold) {
		super.delete(gbSold);
	}
	
}