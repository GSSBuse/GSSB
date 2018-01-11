/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBusinessNumber;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjBusinessNumberDao;

/**
 * 历史交易量Service
 * @author snnu
 * @version 2018-01-11
 */
@Service
@Transactional(readOnly = true)
public class GbjBusinessNumberService extends CrudService<GbjBusinessNumberDao, GbjBusinessNumber> {

	public GbjBusinessNumber get(String id) {
		return super.get(id);
	}
	
	public List<GbjBusinessNumber> findList(GbjBusinessNumber gbjBusinessNumber) {
		return super.findList(gbjBusinessNumber);
	}
	
	public Page<GbjBusinessNumber> findPage(Page<GbjBusinessNumber> page, GbjBusinessNumber gbjBusinessNumber) {
		return super.findPage(page, gbjBusinessNumber);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjBusinessNumber gbjBusinessNumber) {
		super.save(gbjBusinessNumber);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjBusinessNumber gbjBusinessNumber) {
		super.delete(gbjBusinessNumber);
	}
	
}