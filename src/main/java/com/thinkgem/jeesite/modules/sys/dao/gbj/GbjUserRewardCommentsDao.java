/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserRewardComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldComments;

/**
 * 悬赏信息评论DAO接口
 * @author snnu
 * @version 2017-12-21
 */
@MyBatisDao
public interface GbjUserRewardCommentsDao extends CrudDao<GbjUserRewardComments> {
	//根据reward_id查询卖标评论信息
		public List<GbjUserRewardComments>  getCommentsList(String reward_Id);
}