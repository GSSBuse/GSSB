/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyTemplateMessage;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyTemplateMessageDao;

/**
 * 通知消息设置Service
 * @author songshuqing
 * @version 2015-11-15
 */
@Service
@Transactional(readOnly = true)
public class DyTemplateMessageService extends CrudService<DyTemplateMessageDao, DyTemplateMessage> {

	public DyTemplateMessage get(String id) {
		return super.get(id);
	}
	
	public List<DyTemplateMessage> findList(DyTemplateMessage dyTemplateMessage) {
		return super.findList(dyTemplateMessage);
	}
	
	public Page<DyTemplateMessage> findPage(Page<DyTemplateMessage> page, DyTemplateMessage dyTemplateMessage) {
		return super.findPage(page, dyTemplateMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(DyTemplateMessage dyTemplateMessage) {
		super.save(dyTemplateMessage);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyTemplateMessage dyTemplateMessage) {
		super.delete(dyTemplateMessage);
	}
	
}