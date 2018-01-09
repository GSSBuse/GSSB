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
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjBuyDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;

/**
 * 买标信息Service
 * 
 * @author snnu
 * @version 2017-12-17
 */
@Service
@Transactional(readOnly = true)
public class GbjBuyService extends CrudService<GbjBuyDao, GbjBuy> {

	@Autowired
	GbjBuyDao gbjBuydao;

	public GbjBuy get(String id) {
		return super.get(id);
	}

	// 更新浏览次数
	@Transactional(readOnly = false)
	public void upDateLook(String id) {
		gbjBuydao.upDateLook(id);
	}

	public List<GbjBuy> findList(GbjBuy gbjBuy) {
		return super.findList(gbjBuy);
	}

	public Page<GbjBuy> findPage(Page<GbjBuy> page, GbjBuy gbjBuy) {
		return super.findPage(page, gbjBuy);
	}

	@Transactional(readOnly = false)
	public void save(GbjBuy gbjBuy) {
		super.save(gbjBuy);
	}

	// 插入用户id的发布信息 by snnu 2018.1.1
	@Transactional(readOnly = false)
	public void saveBuy(GbjBuy gbjBuy) {
		super.saveBuy(gbjBuy);
	}

	@Transactional(readOnly = false)
	public void delete(GbjBuy gbjBuy) {
		super.delete(gbjBuy);
	}

	@Transactional(readOnly = false)
	public void updateCount(GbjBuy gbjBuy) {

		// System.out.print("service");
		gbjBuydao.updateCount(gbjBuy);
	}

	// 发布买标信息
	@Transactional(readOnly = false)
	public void release(GbjBuy gbjBuy) {

		// System.out.print("service");
		gbjBuydao.release(gbjBuy);
	}

	// 撤回买标信息
	@Transactional(readOnly = false)
	public void withdraw(GbjBuy gbjBuy) {

		// System.out.print("service");
		gbjBuydao.withdraw(gbjBuy);
	}

	public List<GbjBuy> findDomainBuyList(@Param(value = "counts") String count) {
		return gbjBuydao.findDomainBuyList(Integer.parseInt(count));
	}
	/*
	 * public List<GbjBuy> findDomainArticleBuyList(@Param(value="counts") String
	 * count) { return gbjBuydao.findDomainArticleBuyList(Integer.parseInt(count));
	 * }
	 */

}