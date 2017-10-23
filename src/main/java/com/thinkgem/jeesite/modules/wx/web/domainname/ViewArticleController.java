package com.thinkgem.jeesite.modules.wx.web.domainname;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyArticle;
import com.thinkgem.jeesite.modules.sys.service.dy.DyArticleService;

/**
 * 我要卖 页面控制器
 * @author songshuqing
 * @since 2015/10/12
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/domainname")
public class ViewArticleController {
	
	// 文章管理Service
	@Autowired
	private DyArticleService dyArticleService;

	/*
	 * ==================================
	 *   页面显示controller
	 * ==================================
	 */
	
	/**
	 * 文章显示页面
	 * @param model 页面模型
	 * @return 文章显示页面视图
	 */
	@RequestMapping(value = {"viewArticle"})
	public String viewArticle(Model model, String articleId) {
		// 获取文章内容
		DyArticle article = dyArticleService.get(articleId);
		if (article == null || !"0".equals(article.getDelFlag())){
			return "error/404";
		}
		
		model.addAttribute("article", article);
		return "modules/wx/domainname/viewArticle";
	}
}
