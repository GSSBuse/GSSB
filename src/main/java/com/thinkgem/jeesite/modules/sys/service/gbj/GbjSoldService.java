/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjSoldDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjSold;

/**
 * 卖标信息管理Service
 * 
 * @author snnu
 * @version 2017-12-16
 */
@Service
@Transactional(readOnly = true)
public class GbjSoldService extends CrudService<GbjSoldDao, GbjSold> {
	@Autowired
	GbjSoldDao gbjSolddao;

	public GbjSold get(String id) {
		return super.get(id);
	}

	// 更新浏览次数
	@Transactional(readOnly = false)
	public void upDateLook(String id) {
		gbjSolddao.upDateLook(id);
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

	@Transactional(readOnly = false) // 自定义save
	public void saveSold(GbjSold gbjSold) {
		super.saveSold(gbjSold);
	}

	@Transactional(readOnly = false)
	public void delete(GbjSold gbjSold) {
		super.delete(gbjSold);
	}

	// 删除前台
	@Transactional(readOnly = false)
	public void shanchu(@RequestParam("id") String id) {
		gbjSolddao.shanchu(id);
	}

	// 卖标点赞
	@Transactional(readOnly = false)
	public void updateCount(GbjSold gbjSold) {

		// System.out.print("service");
		gbjSolddao.updateCount(gbjSold);
	}

	// 发布卖标信息
	@Transactional(readOnly = false)
	public void release(GbjSold gbjSold) {

		// System.out.print("service");
		gbjSolddao.release(gbjSold);
	}

	// 撤回卖标信息
	@Transactional(readOnly = false)
	public void withdraw(GbjSold gbjSold) {

		// System.out.print("service");
		gbjSolddao.withdraw(gbjSold);
	}

	public List<GbjSold> findDomainSoldList(@Param(value = "counts") String count) {
		return gbjSolddao.findDomainSoldList(Integer.parseInt(count));
	}

}