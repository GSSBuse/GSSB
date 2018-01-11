package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.RewardArticleListDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.RewardArticleList;

@Service
@Transactional(readOnly = true)
public class RewardArticleListService extends CrudService<RewardArticleListDao, RewardArticleList> {
	@Autowired
	RewardArticleListDao rewardarticleListdao;

	public List<RewardArticleList> findDomainRewardArticleList(@Param(value = "start") int start,
			@Param(value = "end") int end) {
		return rewardarticleListdao.findDomainRewardArticleList(start, end);
	}

	public List<RewardArticleList> findDomainUserRewardArticleList(@RequestParam("id") String id) {
		// TODO Auto-generated method stub
		return rewardarticleListdao.findDomainUserRewardArticleList(id);
	}

	public int findDomainRewardArticlePageCount() {
		return rewardarticleListdao.findDomainRewardArticlePageCount();
	}
}
