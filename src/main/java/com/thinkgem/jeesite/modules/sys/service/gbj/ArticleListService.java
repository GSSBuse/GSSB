package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.ArticleListDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjBuyDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
@Service
@Transactional(readOnly = true)
public class ArticleListService extends CrudService<ArticleListDao, ArticleList> {
	@Autowired
	ArticleListDao articleListdao;
	public List<ArticleList> findDomainArticleList(@RequestParam("count") String count) {
		return articleListdao.findDomainArticleList(Integer.parseInt(count));
	}

}
