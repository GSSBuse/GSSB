package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserComments;
@MyBatisDao
public interface GbjUserCommentsDao extends CrudDao<GbjUserComments> {
	public List<GbjUserComments> findDomainGbjUserCommentsList(String id);
}
