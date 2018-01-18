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
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserSoldCommentsReplyDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldCommentsReply;

/**
 * 卖标信息评论回复管理Service
 * 
 * @author snnu
 * @version 2018-01-18
 */
@Service
@Transactional(readOnly = true)
public class GbjUserSoldCommentsReplyService
		extends CrudService<GbjUserSoldCommentsReplyDao, GbjUserSoldCommentsReply> {

	@Autowired
	GbjUserSoldCommentsReplyDao gbjUserSoldCommentsReplyDao;

	public GbjUserSoldCommentsReply get(String id) {
		return super.get(id);
	}

	public List<GbjUserSoldCommentsReply> findList(GbjUserSoldCommentsReply gbjUserSoldCommentsReply) {
		return super.findList(gbjUserSoldCommentsReply);
	}

	public Page<GbjUserSoldCommentsReply> findPage(Page<GbjUserSoldCommentsReply> page,
			GbjUserSoldCommentsReply gbjUserSoldCommentsReply) {
		return super.findPage(page, gbjUserSoldCommentsReply);
	}

	@Transactional(readOnly = false)
	public void save(GbjUserSoldCommentsReply gbjUserSoldCommentsReply) {
		super.save(gbjUserSoldCommentsReply);
	}

	@Transactional(readOnly = false)
	public void delete(GbjUserSoldCommentsReply gbjUserSoldCommentsReply) {
		super.delete(gbjUserSoldCommentsReply);
	}

	public List<GbjUserSoldCommentsReply> findDomainArticleSoldReplyCommentsList(@RequestParam("id") String id) {
		return gbjUserSoldCommentsReplyDao.findDomainArticleSoldReplyCommentsList(id);
	}

}