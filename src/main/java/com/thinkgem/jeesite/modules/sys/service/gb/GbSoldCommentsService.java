/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbSoldComments;
import com.thinkgem.jeesite.modules.sys.dao.gb.GbSoldCommentsDao;

/**
 * 卖标评论管理Service
 * @author 管理员
 * @version 2017-11-05
 */
@Service
@Transactional(readOnly = true)
public class GbSoldCommentsService extends CrudService<GbSoldCommentsDao, GbSoldComments> {

	public GbSoldComments get(String id) {
		return super.get(id);
	}
	
	public List<GbSoldComments> findList(GbSoldComments gbSoldComments) {
		return super.findList(gbSoldComments);
	}
	
	public Page<GbSoldComments> findPage(Page<GbSoldComments> page, GbSoldComments gbSoldComments) {
		return super.findPage(page, gbSoldComments);
	}
	
	@Transactional(readOnly = false)
	public void save(GbSoldComments gbSoldComments) {
		super.save(gbSoldComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbSoldComments gbSoldComments) {
		super.delete(gbSoldComments);
	}
	
}