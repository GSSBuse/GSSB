/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAccessRecord;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyAccessRecordDao;

/**
 * 访问记录管理Service
 * @author quanyf.fnst
 * @version 2015-11-04
 */
@Service
@Transactional(readOnly = true)
public class DyAccessRecordService extends CrudService<DyAccessRecordDao, DyAccessRecord> {

	public DyAccessRecord get(String id) {
		return super.get(id);
	}
	
	public List<DyAccessRecord> findList(DyAccessRecord dyAccessRecord) {
		return super.findList(dyAccessRecord);
	}
	
	public Page<DyAccessRecord> findPage(Page<DyAccessRecord> page, DyAccessRecord dyAccessRecord) {
		return super.findPage(page, dyAccessRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(DyAccessRecord dyAccessRecord) {
		super.save(dyAccessRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyAccessRecord dyAccessRecord) {
		super.delete(dyAccessRecord);
	}
	
}