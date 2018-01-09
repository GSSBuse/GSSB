package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.BuyArticleList;

@MyBatisDao
public interface BuyArticleListDao extends CrudDao<BuyArticleList> {
	public List<BuyArticleList> findDomainBuyArticleList(@Param(value = "start") int start,
			@Param(value = "end") int end);

	// 个人中心获取个人买标信息
	public List<BuyArticleList> findDomainUserBuyArticleList(String id);

	public int findDomainBuyArticlePageCount();
}
