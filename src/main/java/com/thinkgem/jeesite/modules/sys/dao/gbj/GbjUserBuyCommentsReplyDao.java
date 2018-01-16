/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyCommentsReply;

/**
 * 买标信息评论回复管理DAO接口
 * 
 * @author snnu
 * @version 2018-01-15
 */
@MyBatisDao
public interface GbjUserBuyCommentsReplyDao extends CrudDao<GbjUserBuyCommentsReply> {
	public List<GbjUserBuyCommentsReply> findDomainArticleBuyReplyCommentsList(String id);
}