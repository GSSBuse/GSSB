/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserRewardCommentsReply;

/**
 * 悬赏信息评论回复表DAO接口
 * 
 * @author snnu
 * @version 2018-01-18
 */
@MyBatisDao
public interface GbjUserRewardCommentsReplyDao extends CrudDao<GbjUserRewardCommentsReply> {
	public List<GbjUserRewardCommentsReply> findDomainArticleRewardReplyCommentsList(String id);
}