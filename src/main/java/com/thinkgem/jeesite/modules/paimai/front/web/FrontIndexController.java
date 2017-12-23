package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.SendMailUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.paimai.front.bean.FrontLoginUser;
import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.BuyArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjReward;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjSold;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTouristRequire;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUser;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.RewardArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.SoldArticleList;
import com.thinkgem.jeesite.modules.sys.service.gbj.ArticleListService;
import com.thinkgem.jeesite.modules.sys.service.gbj.BuyArticleListService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBuyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjRewardService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjSoldService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjTouristRequireService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserBuyCommentsService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserService;
import com.thinkgem.jeesite.modules.sys.service.gbj.RewardArticleListService;
import com.thinkgem.jeesite.modules.sys.service.gbj.SoldArticleListService;


@Controller
@RequestMapping(value = "${frontPath}")
public class FrontIndexController extends BaseController{
		
	@Autowired
	private GbjUserService gbjUserService; 								   // 用户 2017/12/23 by cf
	
	@Autowired
	private GbjTouristRequireService gbjTouristRequireService;             // 国商商标查询Service
	
	@Autowired
	private GbjBuyService gbjBuyService;                                  //我要买标信息发布 2017/12/3  by snnu                                
	
	@Autowired
	private GbjSoldService gbjSoldService;                               //我要买标信息发布 2017/12/3  by snnu  
	
	@Autowired
	private GbjRewardService gbjRewardService;                          //我要买标信息发布 2017/12/3  by snnu  
	
	@Autowired
	private ArticleListService articleListService;     

	@Autowired
	private BuyArticleListService buyarticleListService; //买标信息 2017/12/15  by snnu
	@Autowired
	private SoldArticleListService soldarticleListService; //卖标信息发布 2017/12/15  by snnu
	@Autowired
	private RewardArticleListService rewardarticleListService; //悬赏信息发布 2017/12/15 by snnu
	
	@Autowired
	private GbjUserBuyCommentsService gbjUserBuyCommentsService;//评论信息展示2017/12/21 by snnu
	/**
	 * 网站首页
	 
	@RequestMapping(value= {"", "index"})
	public String index(Model model) {
		return "modules/paimai/front/index";
	}	*/

	/**
	 * 网站首页
	 */
	@RequestMapping(value= {"","index1"})
	public String index1(Model model) {
		return "modules/paimai/front/index1";
	}
	/**
	 * 登录注册
	 */
	@RequestMapping(value= {"login"})
	public String login(Model model, HttpServletRequest request) {
		model.addAttribute("userinfo", "test");
		return "modules/paimai/front/login";
	}	
	
	@RequestMapping(value = "nameLogin")
	@ResponseBody
	public AjaxResult nameLogin(HttpServletRequest request, @RequestParam(value = "username") String username,
			@RequestParam(value = "passwd") String password) {
		GbjUser userCondition = new GbjUser();
		userCondition.setUsername(username);
		userCondition.setPassword(password);
		
		List<GbjUser> findedUsers;
		try {
			findedUsers = this.gbjUserService.findList(userCondition);
		} catch (Exception e) {
			logger.error("查询用户出错", e);
			return AjaxResult.makeError("登录出错");
		}
		if (findedUsers == null || findedUsers.isEmpty()) {
			return AjaxResult.makeError("用户名或密码错误");
		}
		
		GbjUser userEntity = findedUsers.get(0);
		
		FrontLoginUser loginUser = new FrontLoginUser();
		loginUser.setId(userEntity.getId());
		loginUser.setName(userEntity.getName());
		loginUser.setUsername(userEntity.getUsername());
		loginUser.setMobile(userEntity.getMobile());
		loginUser.setEmail(userEntity.getEmail());
		
		request.getSession().setAttribute("login_user", loginUser);
		return AjaxResult.makeSuccess("登录成功");
	}
	
	/**
	 * 联系我们页面
	 */
	@RequestMapping(value= {"contact"})
	public String contact(Model model) {
		return "modules/paimai/front/contact";
	}
	

	/**
	 * 所有信息一览页面
	 */
	@RequestMapping(value= {"articles"})
	public String articles(Model model) {
		return "modules/paimai/front/articles";
	}	

	/**
	 * 咨询详细一览页面
	 */
	@RequestMapping(value= {"single"})
	public String single(Model model) {
		return "modules/paimai/front/single";
	}	
	/**
	 * 买标信息详细一览页面     snnu  12.21
	 */
	@RequestMapping(value= {"gbjbuysingle"})
	public String gbjbuysingle(Model model) {
		return "modules/paimai/front/gbjbuysingle";
	}	
	/**
	 * 咨询买标一览页面
	 */
	@RequestMapping(value= {"buyarticles"})
	public String buyarticles(Model model) {
		return "modules/paimai/front/buyarticles";
	}	/**
	 * 咨询卖标一览页面
	 */
	@RequestMapping(value= {"soldarticles"})
	public String soldarticles(Model model) {
		return "modules/paimai/front/soldarticles";
	}	/**
	 * 咨询悬赏一览页面
	 */
	@RequestMapping(value= {"rewardarticles"})
	public String rewardarticles(Model model) {
		return "modules/paimai/front/rewardarticles";
	}	
	/**
	 * 帮助说明页面
	 */
	@RequestMapping(value= {"faqs"})
	public String faqs(Model model) {
		return "modules/paimai/front/faqs";
	}
	/**
	 * 免费查询提交表单
	 */
	@RequestMapping(value= {"searchBrand"})
	@ResponseBody
	public AjaxResult searchBrand(Model model, GbjTouristRequire gbjTouristRequire, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("domainInfoJson", JsonMapper.toJsonString(gbjTouristRequire));
		try{
			
			//STEP1  提交查询信息，保存到数据库
			gbjTouristRequireService.save(gbjTouristRequire);
			
			/*保存日志*/  //暂时去掉业务日志，后期打开 by wanghs
			//LogUtils.saveSpecialLog(request, null);
			
			//STEP2 发送邮件  TODO
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("subject", "测试标题");
			map.put("content", "测试内容");
			String toMailAddr = "928335288@qq.com";
			String subject = "国商商标查询";
			String templatePath = "mailtemplate/test.ftl";
			String message = JsonMapper.toJsonString(gbjTouristRequire);
		    SendMailUtil.sendFtlMail(toMailAddr, subject,templatePath, map);			
			
			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
			
			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【"+e.getMessage()+"】");
			return AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】");
		}
		
	}
	/**
	 * 我要买标信息提交   2017/12/3  
	 * by snnu
 	*/
	@RequestMapping(value= {"gbBuy"})  
	@ResponseBody
	public AjaxResult gbBuy(Model model, GbjBuy gbjBuy, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("domainInfo1Json", JsonMapper.toJsonString(gbjBuy));
		try{
			
			
			//STEP1  提交查询信息，保存到数据库
			gbjBuyService.save(gbjBuy);
		
			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
			
			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【"+e.getMessage()+"】");
			return AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】");
		}
		
	}
	/**
	 * 我要卖标信息提交   2017/12/5 
	 * by snnu
	 */
	@RequestMapping(value= {"gbsold"})  
	@ResponseBody
	public AjaxResult gbsold(Model model, GbjSold gbjSold, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("domainInfo2Json", JsonMapper.toJsonString(gbjSold));
		try{
			
			
			//STEP1  提交查询信息，保存到数据库
			gbjSoldService.save(gbjSold);
		
			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
			
			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【"+e.getMessage()+"】");
			return AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】");
		}
		
	}	
	/**
	 * 悬赏起名信息提交   2017/12/5  
	 * by snnu
 	*/
	@RequestMapping(value= {"gbreward"})  
	@ResponseBody
	public AjaxResult gbreward(Model model, GbjReward gbjReward, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("domainInfo3Json", JsonMapper.toJsonString(gbjReward));
		try{
			
			
			//STEP1  提交查询信息，保存到数据库
			gbjRewardService.save(gbjReward);
		
			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
			
			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【"+e.getMessage()+"】");
			return AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】");
		}
		
	}
	/**
	 * 获取页面显示我要买标信息的最新数据
	 * @param domainBuyIdList
	 * @return 我要买标的最新数据
	 */
	@RequestMapping(value = "polling/gbbuyData")
	@ResponseBody
	public AjaxResult gbbuyData( @RequestParam("count") String count) {
		
		//取得最新的我要买标信息
		List<GbjBuy> pageDomainBuyList = new ArrayList<GbjBuy>();
		try {
			pageDomainBuyList = gbjBuyService.findDomainBuyList(count);
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("gbbuyData", pageDomainBuyList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}
	/**
	 * 获取页面显示我要卖标信息的最新数据
	 * @param domainSoldIdList
	 * @return 我要卖标的最新数据
	 */
	@RequestMapping(value = "polling/gbsoldData")
	@ResponseBody
	public AjaxResult gbsoldData( @RequestParam("count") String count) {
		
		//取得最新的我要卖标信息
		List<GbjSold> pageDomainSoldList = new ArrayList<GbjSold>();
		try {
			pageDomainSoldList = gbjSoldService.findDomainSoldList(count);
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("gbsoldData", pageDomainSoldList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	} 
	/**
	 * 获取页面显示悬赏信息的最新数据
	 * @param domainSoldIdList
	 * @return 悬赏的最新数据
	 */
	@RequestMapping(value = "polling/gbrewardData")
	@ResponseBody
	public AjaxResult gbrewardData( @RequestParam("count") String count) {
		
		//取得最新的我要买标信息
		List<GbjReward> pageDomainRewardList = new ArrayList<GbjReward>();
		try {
			pageDomainRewardList = gbjRewardService.findDomainRewardList(count);
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("gbrewardData", pageDomainRewardList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	} 
	/**
	 * 获取页面显示全部信息的最新数据
	 * @param domainIdList
	 * @return 全部的最新数据
	 */
	@RequestMapping(value = "polling/ArticleData")
	@ResponseBody
	public AjaxResult ArticleData( @RequestParam("count") String count) {
		
		//取得最新全部的信息
		List<ArticleList> pageDomainArticleList = new ArrayList<ArticleList>();
		try {
			pageDomainArticleList = articleListService.findDomainArticleList(count);
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleData", pageDomainArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	} 
	/**
	 * 获取页面显示我要买标信息的全部信息
	 * @param domainBuyIdList
	 * @return 我要买标的最新数据
	 */
	@RequestMapping(value = "polling/ArticleBuyData")
	@ResponseBody
	public AjaxResult ArticleBuyData( @RequestParam("count") String count) {
		
		//取得最新的我要买标信息
		List<BuyArticleList> pageDomainBuyArticleList = new ArrayList<BuyArticleList>();
		try {
			pageDomainBuyArticleList = buyarticleListService.findDomainBuyArticleList(count);
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleBuyData", pageDomainBuyArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}
	
	/**
	 * 获取页面显示我要卖信息的全部信息
	 * @param domainBuyIdList
	 * @return 卖标的最新数据
	 */
	@RequestMapping(value = "polling/ArticleSoldData")
	@ResponseBody
	public AjaxResult ArticleSoldData( @RequestParam("count") String count) {
		
		//取得最新的我要卖标信息
		List<SoldArticleList> pageDomainSoldArticleList = new ArrayList<SoldArticleList>();
		try {
			pageDomainSoldArticleList = soldarticleListService.findDomainSoldArticleList(count);
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleSoldData", pageDomainSoldArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}
	/**
	 * 获取页面显示悬赏信息的全部信息
	 * @param domainBuyIdList
	 * @return 悬赏的最新数据
	*/
	@RequestMapping(value = "polling/ArticleRewardData")
	@ResponseBody
	public AjaxResult ArticleRewardData( @RequestParam("count") String count) {
		
		//取得最新的我要悬赏信息
		List<RewardArticleList> pageDomainRewardArticleList = new ArrayList<RewardArticleList>();
		try {
			pageDomainRewardArticleList = rewardarticleListService.findDomainRewardArticleList(count);
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleRewardData", pageDomainRewardArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	} 
	/**
	 * 获取页面显示单个买标信息和评论信息    by snnu 2017/12/21
	 * @param ArticleBuyCommentsData
	 * @return 单个买标信息和评论信息
	 */
	@RequestMapping(value = "polling/ArticleBuyCommentsData")
	@ResponseBody
	public AjaxResult ArticleBuyCommentsData( @RequestParam("count") String count) {
		
		//单个买标信息和评论信息
		List<GbjUserBuyComments> pageDomainBuyCommentsArticleList = new ArrayList<GbjUserBuyComments>();
		try {
			pageDomainBuyCommentsArticleList = gbjUserBuyCommentsService.findDomainArticleBuyCommentsList(count );
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleBuyCommentsData", pageDomainBuyCommentsArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}
	
	
	
}
