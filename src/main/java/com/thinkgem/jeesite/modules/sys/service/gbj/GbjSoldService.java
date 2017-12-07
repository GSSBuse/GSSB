/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjSold;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjBuyDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjSoldDao;

/**
 * 卖标信息管理Service
 * @author snnu
 * @version 2017-11-19
 */
@Service
@Transactional(readOnly = true)
public class GbjSoldService extends CrudService<GbjSoldDao, GbjSold> {
	@Autowired
	GbjSoldDao gbjSolddao;

	public GbjSold get(String id) {
		return super.get(id);
	}
	
	public List<GbjSold> findList(GbjSold gbjSold) {
		return super.findList(gbjSold);
	}
	
	public Page<GbjSold> findPage(Page<GbjSold> page, GbjSold gbjSold) {
		return super.findPage(page, gbjSold);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjSold gbjSold) {
		super.save(gbjSold);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjSold gbjSold) {
		super.delete(gbjSold);
	}
	public List<GbjSold> findDomainSoldList(@Param(value="counts") String count) {
		return gbjSolddao.findDomainSoldList(Integer.parseInt(count));
	}
	
}