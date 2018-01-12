/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserSoldCommentsDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldComments;

/**
 * 卖标信息评论Service
 * 
 * @author 管理员
 * @version 2017-12-21
 */
@Service
@Transactional(readOnly = true)
public class GbjUserSoldCommentsService extends CrudService<GbjUserSoldCommentsDao, GbjUserSoldComments> {

	@Autowired
	GbjUserSoldCommentsDao gbjUserSoldCommentsDao;

	public GbjUserSoldComments get(String id) {
		return super.get(id);
	}

	public List<GbjUserSoldComments> findList(GbjUserSoldComments gbjUserSoldComments) {
		return super.findList(gbjUserSoldComments);
	}

	// 根据sold_id 查询评论
	public Page<GbjUserSoldComments> findPages(Page<GbjUserSoldComments> page, String sold_Id) {

		/* return gbjUserSoldCommentsDao.getCommentsList(sold_Id); */
		page.setList(gbjUserSoldCommentsDao.getCommentsList(sold_Id));

		return page;
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

	public List<GbjUserSoldComments> findDomainArticleSoldCommentsList(@RequestParam("id") String id) {
		return gbjUserSoldCommentsDao.findDomainArticleSoldCommentsList(id);
	}

	public List<GbjUserSoldComments> findDomainArticleSoldReplyCommentsList(@RequestParam("id") String id) {
		return gbjUserSoldCommentsDao.findDomainArticleSoldReplyCommentsList(id);
	}
}