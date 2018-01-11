package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.RewardArticleList;

@MyBatisDao
public interface RewardArticleListDao extends CrudDao<RewardArticleList> {
	public List<RewardArticleList> findDomainRewardArticleList(@Param(value = "start") int start,
			@Param(value = "end") int end);

	public List<RewardArticleList> findDomainUserRewardArticleList(String id);

	public int findDomainRewardArticlePageCount();
}
