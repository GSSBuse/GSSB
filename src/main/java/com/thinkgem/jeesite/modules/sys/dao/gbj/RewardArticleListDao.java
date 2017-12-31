package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.BuyArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.RewardArticleList;
@MyBatisDao
public interface RewardArticleListDao extends CrudDao<RewardArticleList> {
	public List<RewardArticleList> findDomainRewardArticleList(int count);

	public List<RewardArticleList> findDomainUserRewardArticleList(String id);
}
