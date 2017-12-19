/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldComments;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserSoldCommentsDao;

/**
 * 卖标信息评论Service
 * @author 管理员
 * @version 2017-12-18
 */
@Service
@Transactional(readOnly = true)
public class GbjUserSoldCommentsService extends CrudService<GbjUserSoldCommentsDao, GbjUserSoldComments> {

	public GbjUserSoldComments get(String id) {
		return super.get(id);
	}
	
	public List<GbjUserSoldComments> findList(GbjUserSoldComments gbjUserSoldComments) {
		return super.findList(gbjUserSoldComments);
	}
	
	public Page<GbjUserSoldComments> findPage(Page<GbjUserSoldComments> page, GbjUserSoldComments gbjUserSoldComments) {
		return super.findPage(page, gbjUserSoldComments);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjUserSoldComments gbjUserSoldComments) {
		super.save(gbjUserSoldComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjUserSoldComments gbjUserSoldComments) {
		super.delete(gbjUserSoldComments);
	}
	
}