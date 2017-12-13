package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
@MyBatisDao
public interface ArticleListDao extends CrudDao<ArticleList> {
	public List<ArticleList> findDomainArticleList(int count);
}
