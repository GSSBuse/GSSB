/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbBuy;
import com.thinkgem.jeesite.modules.sys.dao.gb.GbBuyDao;

/**
 * 买标信息管理Service
 * @author 管理员
 * @version 2017-11-02
 */
@Service
@Transactional(readOnly = true)
public class GbBuyService extends CrudService<GbBuyDao, GbBuy> {

	public GbBuy get(String id) {
		return super.get(id);
	}
	
	public List<GbBuy> findList(GbBuy gbBuy) {
		return super.findList(gbBuy);
	}
	
	public Page<GbBuy> findPage(Page<GbBuy> page, GbBuy gbBuy) {
		return super.findPage(page, gbBuy);
	}
	
	@Transactional(readOnly = false)
	public void save(GbBuy gbBuy) {
		super.save(gbBuy);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbBuy gbBuy) {
		super.delete(gbBuy);
	}
	
}