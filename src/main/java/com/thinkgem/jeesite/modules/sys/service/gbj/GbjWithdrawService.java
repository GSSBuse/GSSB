/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjWithdraw;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjWithdrawDao;

/**
 * 提现记录表Service
 * @author snnu
 * @version 2018-02-03
 */
@Service
@Transactional(readOnly = true)
public class GbjWithdrawService extends CrudService<GbjWithdrawDao, GbjWithdraw> {

	public GbjWithdraw get(String id) {
		return super.get(id);
	}
	
	public List<GbjWithdraw> findList(GbjWithdraw gbjWithdraw) {
		return super.findList(gbjWithdraw);
	}
	
	public Page<GbjWithdraw> findPage(Page<GbjWithdraw> page, GbjWithdraw gbjWithdraw) {
		return super.findPage(page, gbjWithdraw);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjWithdraw gbjWithdraw) {
		super.save(gbjWithdraw);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjWithdraw gbjWithdraw) {
		super.delete(gbjWithdraw);
	}
	
}