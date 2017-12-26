/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTouristRequire;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjTouristRequireDao;

/**
 * 游客查询管理Service
 * @author snnu
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class GbjTouristRequireService extends CrudService<GbjTouristRequireDao, GbjTouristRequire> {

	public GbjTouristRequire get(String id) {
		return super.get(id);
	}
	
	public List<GbjTouristRequire> findList(GbjTouristRequire gbjTouristRequire) {
		return super.findList(gbjTouristRequire);
	}
	
	public Page<GbjTouristRequire> findPage(Page<GbjTouristRequire> page, GbjTouristRequire gbjTouristRequire) {
		return super.findPage(page, gbjTouristRequire);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjTouristRequire gbjTouristRequire) {
		super.save(gbjTouristRequire);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjTouristRequire gbjTouristRequire) {
		super.delete(gbjTouristRequire);
	}
	
}