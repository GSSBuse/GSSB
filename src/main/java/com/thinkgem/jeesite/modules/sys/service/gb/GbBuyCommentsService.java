/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbBuyComments;
import com.thinkgem.jeesite.modules.sys.dao.gb.GbBuyCommentsDao;

/**
 * 买标评论Service
 * @author 管理员
 * @version 2017-11-05
 */
@Service
@Transactional(readOnly = true)
public class GbBuyCommentsService extends CrudService<GbBuyCommentsDao, GbBuyComments> {

	public GbBuyComments get(String id) {
		return super.get(id);
	}
	
	public List<GbBuyComments> findList(GbBuyComments gbBuyComments) {
		return super.findList(gbBuyComments);
	}
	
	public Page<GbBuyComments> findPage(Page<GbBuyComments> page, GbBuyComments gbBuyComments) {
		return super.findPage(page, gbBuyComments);
	}
	
	@Transactional(readOnly = false)
	public void save(GbBuyComments gbBuyComments) {
		super.save(gbBuyComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbBuyComments gbBuyComments) {
		super.delete(gbBuyComments);
	}
	
}