/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyShareRecord;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyShareRecordDao;

/**
 * 分享记录管理Service
 * @author quanyf.fnst
 * @version 2015-11-05
 */
@Service
@Transactional(readOnly = true)
public class DyShareRecordService extends CrudService<DyShareRecordDao, DyShareRecord> {

	public DyShareRecord get(String id) {
		return super.get(id);
	}
	
	public List<DyShareRecord> findList(DyShareRecord dyShareRecord) {
		return super.findList(dyShareRecord);
	}
	
	public Page<DyShareRecord> findPage(Page<DyShareRecord> page, DyShareRecord dyShareRecord) {
		return super.findPage(page, dyShareRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(DyShareRecord dyShareRecord) {
		super.save(dyShareRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyShareRecord dyShareRecord) {
		super.delete(dyShareRecord);
	}
	
}