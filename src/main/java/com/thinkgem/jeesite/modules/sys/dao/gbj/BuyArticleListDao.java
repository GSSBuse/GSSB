package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.BuyArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
@MyBatisDao
public interface BuyArticleListDao extends CrudDao<BuyArticleList> {
	public List<BuyArticleList> findDomainBuyArticleList(int count);
	
	//个人中心获取个人买标信息
	public List<BuyArticleList> findDomainUserBuyArticleList(String id);
	
} 
