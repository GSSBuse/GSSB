package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.SendMailUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.paimai.front.bean.FrontLoginUser;
import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.BuyArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBusinessNumber;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjReward;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjSold;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTag;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTouristRequire;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUser;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyCommentsReply;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserRewardComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserRewardCommentsReply;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldCommentsReply;
import com.thinkgem.jeesite.modules.sys.entity.gbj.RewardArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.SoldArticleList;
import com.thinkgem.jeesite.modules.sys.service.gbj.ArticleListService;
import com.thinkgem.jeesite.modules.sys.service.gbj.BuyArticleListService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBusinessNumberService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBuyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjRewardService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjSoldService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjTagService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjTouristRequireService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserBuyCommentsReplyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserBuyCommentsService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserCommentsService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserRewardCommentsReplyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserRewardCommentsService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserSoldCommentsReplyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserSoldCommentsService;
import com.thinkgem.jeesite.modules.sys.service.gbj.RewardArticleListService;
import com.thinkgem.jeesite.modules.sys.service.gbj.SoldArticleListService;

@Controller
@RequestMapping(value = "${frontPath}")
public class FrontIndexController extends BaseController {

	@Autowired
	private GbjUserService gbjUserService;

	@Autowired
	private GbjTouristRequireService gbjTouristRequireService; // 国商商标查询Service

	@Autowired
	private GbjBuyService gbjBuyService; // 我要买标信息发布 2017/12/3 by snnu

	@Autowired
	private GbjSoldService gbjSoldService; // 我要买标信息发布 2017/12/3 by snnu

	@Autowired
	private GbjRewardService gbjRewardService; // 我要买标信息发布 2017/12/3 by snnu

	@Autowired
	private ArticleListService articleListService;

	@Autowired
	private BuyArticleListService buyarticleListService; // 买标信息 2017/12/15 by
															// snnu
	@Autowired
	private SoldArticleListService soldarticleListService; // 卖标信息发布 2017/12/15
															// by snnu
	@Autowired
	private RewardArticleListService rewardarticleListService; // 悬赏信息发布
																// 2017/12/15 by
																// snnu

	@Autowired
	private GbjUserBuyCommentsService gbjUserBuyCommentsService;// 买标评论信息展示2017/12/21
																// by snnu

	@Autowired
	private GbjUserSoldCommentsService gbjUserSoldCommentsService;// 卖标评论信息展示2017/12/29
																	// by snnu
	@Autowired
	private GbjUserRewardCommentsService gbjUserRewardCommentsService;// 悬赏评论信息展示2017/12/29
																		// by
																		// snnu

	@Autowired
	private GbjUserCommentsService gbjUserCommentsService;// 悬赏评论信息展示2017/12/29
															// by snnu
	@Autowired
	private GbjTagService gbjTagService;
	@Autowired
	private GbjBusinessNumberService gbjBusinessNumberService;

	@Autowired
	GbjUserBuyCommentsReplyService gbjUserBuyCommentsReplyService;
	@Autowired
	GbjUserSoldCommentsReplyService gbjUserSoldCommentsReplyService;
	@Autowired
	GbjUserRewardCommentsReplyService gbjUserRewardCommentsReplyService;

	/**
	 * 网站首页
	 * 
	 * @RequestMapping(value= {"", "index"}) public String index(Model model) {
	 * return "modules/paimai/front/index"; }
	 */

	/**
	 * 网站首页
	 */
	@RequestMapping(value = { "", "index1" })
	public ModelAndView index1(Model model) {
		ModelAndView mav = null;
		mav = new ModelAndView("modules/paimai/front/index1");
		GbjBusinessNumber GbjBusinessNumber = gbjBusinessNumberService.findDomainBusinessNumber();
		mav.addObject("GbjBusinessNumber", GbjBusinessNumber);
		return mav;
	}

	/**
	 * 登录注册
	 */
	@RequestMapping(value = { "login" })
	public String login(Model model, HttpServletRequest request) {
		model.addAttribute("userinfo", "test");
		return "modules/paimai/front/login";
	}

	/**
	 * 个人中心
	 */
	/*
	 * @RequestMapping(value= {"personalcenter"}) public String personalcenter(Model
	 * model, HttpServletRequest request) { model.addAttribute("userinfo", "test");
	 * return "modules/paimai/front/personalcenter"; }
	 */

	/**
	 * 帐号登录
	 */
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
	 * 退出登录
	 */
	@RequestMapping(value = "logout")
	@ResponseBody
	public AjaxResult logout(HttpServletRequest request) {
		request.getSession().removeAttribute("login_user");
		return AjaxResult.makeSuccess("退出成功");
	}

	/**
	 * 帐号登录
	 */
	@RequestMapping(value = "register")
	@ResponseBody
	public AjaxResult register(HttpServletRequest request, @RequestParam(value = "mobile") String mobile,
			@RequestParam(value = "passwd") String password) {
		GbjUser userCondition = new GbjUser();
		userCondition.setUsername(mobile);

		List<GbjUser> findedUsers;
		try {
			findedUsers = this.gbjUserService.findList(userCondition);
		} catch (Exception e) {
			logger.error("查询用户出错", e);
			return AjaxResult.makeError("注册出错");
		}
		if (findedUsers != null && !findedUsers.isEmpty()) {
			return AjaxResult.makeError("用户名已被使用");
		}

		GbjUser userEntity = new GbjUser();

		userEntity.setUsername(mobile);
		userEntity.setPassword(password);
		userEntity.setMobile(mobile);

		try {
			this.gbjUserService.save(userEntity);
		} catch (Exception e) {
			logger.error("保存用户出错", e);
			return AjaxResult.makeError("注册出错");
		}

		return AjaxResult.makeSuccess("注册成功");
	}

	/**
	 * 个人中心信息修改 2018/1/5 by snnu
	 */
	@RequestMapping(value = { "change" })
	@ResponseBody
	public AjaxResult change(HttpServletRequest request, @RequestParam(value = "id") String id,
			@RequestParam(value = "username") String username, @RequestParam(value = "name") String name,
			@RequestParam(value = "mobile") String mobile, @RequestParam(value = "email") String email,
			@RequestParam(value = "qq") String qq, @RequestParam(value = "wechat") String wechat,
			@RequestParam(value = "payway") String payway) {
		GbjUser gbjUser = new GbjUser();
		// GbjBuy gbjBuy = new GbjBuy();
		try {
			// logger.info(parentId);
			// STEP1 提交查询信息，保存到数据库
			// gbjUser.setBuy(gbjBuyService.get(id));
			gbjUser.setId(id);
			gbjUser.setEmail(email);
			gbjUser.setMobile(mobile);
			gbjUser.setPayway(payway);
			gbjUser.setQq(qq);
			gbjUser.setWechat(wechat);
			gbjUser.setUsername(username);
			gbjUser.setName(name);
			gbjUser.setCreateDate(new Date());

			gbjUserService.save(gbjUser);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 联系我们页面
	 */
	@RequestMapping(value = { "contact" })
	public String contact(Model model) {
		return "modules/paimai/front/contact";
	}

	/**
	 * 所有信息一览页面
	 */
	@RequestMapping(value = { "articles" })
	public String articles(Model model) {
		return "modules/paimai/front/articles";
	}

	/**
	 * 个人中心详细一览页面（根据）
	 */
	@RequestMapping(value = { "personalcenter" })
	public ModelAndView personalcenter(@RequestParam("id") String id, Model model) {
		ModelAndView mav = null;
		// 参数param从前台传递过来
		// 这里把3个single都放到一起了，便于处理，根据type来分是买标、卖标还是悬赏

		GbjUser gbjUserDetail = gbjUserService.get(id);
		gbjBuyService.shanchu(id);
		// 如果是卖标，跳转到gbjsoldsingle卖标页面
		mav = new ModelAndView("modules/paimai/front/personalcenter");
		// 参数拿到了，就根据参数去数据库里面查询详细
		// 检索出来后就前台元素中去
		mav.addObject("gbjUserDetail", gbjUserDetail);
		return mav;

	}

	/**
	 * 删除个人中心买标信息
	 *//*
		 * @RequestMapping(value = { "frontdelete" }) public ModelAndView
		 * frontdelete(@RequestParam("id") String id, Model model) {
		 * BuyArticleListService.frontdelete(id); return new
		 * ModelAndView("modules/paimai/front/personalcenter"); }
		 */
	/**
	 * 登录注册
	 */
	@RequestMapping(value = { "personaluserbuy" })
	public String personaluserbuy(Model model) {

		return "modules/paimai/front/personaluserbuy";
	}

	/**
	 * 咨询详细一览页面
	 * 
	 * @RequestMapping(value= {"single"}) public String single(Model model) { return
	 * "modules/paimai/front/single"; }
	 */
	/**
	 * 前台详细一览页面（根据type判断买标、卖标、悬赏）
	 */
	@RequestMapping(value = { "single" })
	public ModelAndView single(@RequestParam("id") String id, @RequestParam("type") String type, Model model) {
		ModelAndView mav = null;
		// 参数param从前台传递过来
		// 这里把3个single都放到一起了，便于处理，根据type来分是买标、卖标还是悬赏
		if ("sold".equals(type)) {
			// 如果是卖标，跳转到gbjsoldsingle卖标页面
			mav = new ModelAndView("modules/paimai/front/gbjsoldsingle");
			// 参数拿到了，就根据参数去数据库里面查询详细
			GbjSold gbjSoldDetail = gbjSoldService.get(id);

			gbjSoldService.upDateLook(id);

			// 检索出来后就前台元素中去
			mav.addObject("gbjSoldDetail", gbjSoldDetail);
		} else if ("reward".equals(type)) {
			// TODO 和上面一样
			mav = new ModelAndView("modules/paimai/front/gbjrewardsingle");
			// 参数拿到了，就根据参数去数据库里面查询详细
			GbjReward gbjRewardDetail = gbjRewardService.get(id);

			gbjRewardService.upDateLook(id);
			// 检索出来后就前台元素中去
			mav.addObject("gbjRewardDetail", gbjRewardDetail);
		} else if ("buy".equals(type)) {
			// TODO 和上面一样
			mav = new ModelAndView("modules/paimai/front/gbjbuysingle");
			// 参数拿到了，就根据参数去数据库里面查询详细
			GbjBuy gbjBuyDetail = gbjBuyService.get(id);
			gbjBuyService.upDateLook(id);
			// List<GbjUserBuyComments>
			// gbjUserBuyCommentsDetail=gbjUserBuyCommentsService.getFrontCommentsList(id);
			// 检索出来后就前台元素中去
			mav.addObject("gbjBuyDetail", gbjBuyDetail);
			// JSONArray
			// jsonObject=JSONArray.fromObject(gbjUserBuyCommentsDetail);
			// mav.addObject("gbjUserBuyCommentsDetail", jsonObject);
		}
		// 然后return跳转到详细页面去
		return mav;
	}

	/**
	 * 买标信息详细一览页面 snnu 12.21
	 */
	@RequestMapping(value = { "gbjbuysingle" })
	public String gbjbuysingle(Model model) {
		return "modules/paimai/front/gbjbuysingle";
	}

	/**
	 * 卖标信息详细一览页面 snnu 12.23
	 */
	/*
	 * @RequestMapping(value= {"gbjsoldsingle"}) public String gbjsoldsingle(Model
	 * model) { return "modules/paimai/front/gbjsoldsingle"; }
	 */

	/**
	 * 咨询淘金客一览页面
	 */
	@RequestMapping(value = { "goldMiner" })
	public String newbuyarticles(Model model) {
		return "modules/paimai/front/goldMiner";
	}

	/**
	 * 帮助说明页面
	 */
	@RequestMapping(value = { "faqs" })
	public String faqs(Model model) {
		return "modules/paimai/front/faqs";
	}

	/**
	 * 商标注册说明页面
	 */
	@RequestMapping(value = { "registers" })
	public String registers(Model model) {
		return "modules/paimai/front/registers";
	}

	/**
	 * 免费查询提交表单
	 */
	@RequestMapping(value = { "searchBrand" })
	@ResponseBody
	public AjaxResult searchBrand(Model model, GbjTouristRequire gbjTouristRequire, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("domainInfoJson", JsonMapper.toJsonString(gbjTouristRequire));
		try {

			// STEP1 提交查询信息，保存到数据库
			gbjTouristRequireService.save(gbjTouristRequire);

			/* 保存日志 */ // 暂时去掉业务日志，后期打开 by wanghs
			// LogUtils.saveSpecialLog(request, null);

			// STEP2 发送邮件 TODO
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("SearchContents", gbjTouristRequire.getSearchContents());
			map.put("Name", gbjTouristRequire.getName());
			map.put("Mobile", gbjTouristRequire.getMobile());
			map.put("Qq", gbjTouristRequire.getQq());
			String toMailAddr = "928335288@qq.com";
			String subject = "国商商标查询";
			String templatePath = "mailtemplate/test.ftl";
			SendMailUtil.sendFtlMail(toMailAddr, subject, templatePath, map);

			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");

			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【" + e.getMessage() + "】");
			return AjaxResult.makeError("提交查询成功失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 我要买标信息提交 2018/1/1 by snnu
	 */
	@RequestMapping(value = { "gbBuy" })
	@ResponseBody
	public AjaxResult gbBuy(HttpServletRequest request, @RequestParam(value = "id") String id,
			@RequestParam(value = "title") String title, @RequestParam(value = "mobile") String mobile,
			@RequestParam(value = "realname") String realname) {
		// GbjUserBuyComments gbjUserBuyComments = new GbjUserBuyComments();
		GbjBuy gbjBuy = new GbjBuy();
		GbjUser gbjUser = new GbjUser();
		try {
			logger.info(id);
			// STEP1 提交查询信息，保存到数据库
			gbjBuy.setUser(gbjUserService.get(id));
			gbjBuy.setUser_id(id);
			gbjBuy.setRealname(realname);
			gbjBuy.setTitle(title);
			gbjBuy.setMobile(mobile);
			gbjBuy.setCreateDate(new Date());

			gbjBuyService.saveBuy(gbjBuy);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 我要卖标信息提交 2018/1/1 by snnu
	 */
	@RequestMapping(value = { "gbSold" })
	@ResponseBody
	public AjaxResult gbSold(HttpServletRequest request, @RequestParam(value = "id") String id,
			@RequestParam(value = "title") String title, @RequestParam(value = "mobile") String mobile,
			@RequestParam(value = "realname") String realname) {
		// GbjUserBuyComments gbjUserBuyComments = new GbjUserBuyComments();
		GbjSold gbjSold = new GbjSold();
		GbjUser gbjUser = new GbjUser();
		try {
			logger.info(id);
			// STEP1 提交查询信息，保存到数据库
			gbjSold.setUser(gbjUserService.get(id));
			gbjSold.setUser_id(id);
			gbjSold.setRealname(realname);
			gbjSold.setTitle(title);
			gbjSold.setMobile(mobile);
			gbjSold.setCreateDate(new Date());

			gbjSoldService.saveSold(gbjSold);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 我要卖标信息提交 2018/1/1 by snnu
	 */
	@RequestMapping(value = { "gbReward" })
	@ResponseBody
	public AjaxResult gbReward(HttpServletRequest request, @RequestParam(value = "id") String id,
			@RequestParam(value = "mobile") String mobile, @RequestParam(value = "realname") String realname,
			@RequestParam(value = "price") Long price, @RequestParam(value = "description") String description) {
		// GbjUserBuyComments gbjUserBuyComments = new GbjUserBuyComments();
		GbjReward gbjReward = new GbjReward();
		GbjUser gbjUser = new GbjUser();
		try {
			logger.info(id);
			// STEP1 提交查询信息，保存到数据库
			gbjReward.setUser(gbjUserService.get(id));
			gbjReward.setUser_id(id);
			gbjReward.setRealname(realname);
			gbjReward.setDescription(description);
			gbjReward.setPrice(price);
			gbjReward.setMobile(mobile);
			gbjReward.setCreateDate(new Date());

			gbjRewardService.saveReward(gbjReward);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 我要买标评论提交信息提交 2017/12/27 by snnu
	 */
	@RequestMapping(value = { "buycomments" })
	@ResponseBody
	public AjaxResult buycomments(HttpServletRequest request, @RequestParam(value = "id") String id,
			@RequestParam(value = "comment") String comment, @RequestParam(value = "parentId") String parentId) {
		GbjUserBuyComments gbjUserBuyComments = new GbjUserBuyComments();
		GbjBuy gbjBuy = new GbjBuy();
		try {
			logger.info(parentId);
			// STEP1 提交查询信息，保存到数据库
			gbjUserBuyComments.setBuy(gbjBuyService.get(id));
			gbjUserBuyComments.setComment(comment);
			gbjUserBuyComments.setParentId(parentId);
			gbjUserBuyComments.setCreateDate(new Date());

			gbjUserBuyCommentsService.save(gbjUserBuyComments);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 我要卖标评论提交信息提交 2017/12/27 by snnu
	 */
	@RequestMapping(value = { "soldcomments" })
	@ResponseBody
	public AjaxResult soldcomments(HttpServletRequest request, @RequestParam(value = "id") String id,
			@RequestParam(value = "comment") String comment, @RequestParam(value = "parentId") String parentId) {
		GbjUserSoldComments gbjUserSoldComments = new GbjUserSoldComments();
		GbjSold gbjSold = new GbjSold();
		try {
			logger.info(parentId);
			// STEP1 提交查询信息，保存到数据库
			gbjUserSoldComments.setSold(gbjSoldService.get(id));
			gbjUserSoldComments.setComment(comment);
			gbjUserSoldComments.setParentId(parentId);
			gbjUserSoldComments.setCreateDate(new Date());

			gbjUserSoldCommentsService.save(gbjUserSoldComments);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 我要悬赏评论提交信息提交 2017/12/27 by snnu
	 */
	@RequestMapping(value = { "rewardcomments" })
	@ResponseBody
	public AjaxResult rewardcomments(HttpServletRequest request, @RequestParam(value = "id") String id,
			@RequestParam(value = "comment") String comment, @RequestParam(value = "parentId") String parentId) {
		GbjUserRewardComments gbjUserRewardComments = new GbjUserRewardComments();
		GbjReward gbjReward = new GbjReward();
		try {
			logger.info(parentId);
			// STEP1 提交查询信息，保存到数据库
			gbjUserRewardComments.setReward(gbjRewardService.get(id));
			gbjUserRewardComments.setComment(comment);
			gbjUserRewardComments.setParentId(parentId);
			gbjUserRewardComments.setCreateDate(new Date());

			gbjUserRewardCommentsService.save(gbjUserRewardComments);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 回复我要买标评论提交信息提交 2018/1/12 by snnu
	 */
	@RequestMapping(value = { "buyreplycomments" })
	@ResponseBody
	public AjaxResult buyreplycomments(HttpServletRequest request, @RequestParam(value = "buyId") String buyId,
			@RequestParam(value = "replyComments") String replyComments, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "toId") String toId) {
		// GbjUserBuyComments gbjUserBuyComments = new GbjUserBuyComments();
		GbjUserBuyCommentsReply gbjUserBuyCommentsReply = new GbjUserBuyCommentsReply();
		GbjBuy gbjBuy = new GbjBuy();
		try {
			// logger.info(parentId);
			// STEP1 提交查询信息，保存到数据库
			// gbjUserBuyCommentsReply.setBuy(gbjBuyService.get(id));
			gbjUserBuyCommentsReply.setBuyId(buyId);
			gbjUserBuyCommentsReply.setReplyComments(replyComments);
			gbjUserBuyCommentsReply.setUserId(userId);
			gbjUserBuyCommentsReply.setToId(toId);
			gbjUserBuyCommentsReply.setCreateDate(new Date());

			// gbjUserBuyCommentsService.save(gbjUserBuyComments);
			gbjUserBuyCommentsReplyService.save(gbjUserBuyCommentsReply);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 回复我要卖标评论提交信息提交 2018/1/13 by snnu
	 */
	@RequestMapping(value = { "soldreplycomments" })
	@ResponseBody
	public AjaxResult soldreplycomments(HttpServletRequest request, @RequestParam(value = "soldId") String soldId,
			@RequestParam(value = "replyComments") String replyComments, @RequestParam(value = "toId") String toId,
			@RequestParam(value = "userId") String userId) {
		GbjUserSoldCommentsReply gbjUserSoldCommentsReply = new GbjUserSoldCommentsReply();
		GbjSold gbjSold = new GbjSold();
		try {
			// logger.info(parentId);
			// STEP1 提交查询信息，保存到数据库
			gbjUserSoldCommentsReply.setSoldId(soldId);
			gbjUserSoldCommentsReply.setReplyComments(replyComments);
			gbjUserSoldCommentsReply.setUserId(userId);
			gbjUserSoldCommentsReply.setToId(toId);
			gbjUserSoldCommentsReply.setCreateDate(new Date());

			gbjUserSoldCommentsReplyService.save(gbjUserSoldCommentsReply);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 回复我要悬赏评论提交信息提交 2018/1/13 by snnu
	 */
	@RequestMapping(value = { "rewardreplycomments" })
	@ResponseBody
	public AjaxResult rewardreplycomments(HttpServletRequest request, @RequestParam(value = "rewardId") String rewardId,
			@RequestParam(value = "replyComments") String replyComments, @RequestParam(value = "userId") String userId,
			@RequestParam(value = "toId") String toId) {
		GbjUserRewardCommentsReply gbjUserRewardCommentsReply = new GbjUserRewardCommentsReply();
		GbjReward gbjReward = new GbjReward();
		try {
			// logger.info(parentId);
			// STEP1 提交查询信息，保存到数据库
			gbjUserRewardCommentsReply.setRewardId(rewardId);
			gbjUserRewardCommentsReply.setReplyComments(replyComments);
			gbjUserRewardCommentsReply.setUserId(userId);
			gbjUserRewardCommentsReply.setToId(toId);
			gbjUserRewardCommentsReply.setCreateDate(new Date());

			gbjUserRewardCommentsReplyService.save(gbjUserRewardCommentsReply);

			return AjaxResult.makeSuccess("您很棒，评论成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return AjaxResult.makeError("失败【" + e.getMessage() + "】");
		}

	}

	/**
	 * 获取页面显示我要买标信息的最新数据
	 * 
	 * @param domainBuyIdList
	 * @return 我要买标的最新数据
	 */
	@RequestMapping(value = "polling/gbbuyData")
	@ResponseBody
	public AjaxResult gbbuyData(@RequestParam("count") String count) {

		// 取得最新的我要买标信息
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
	 * 
	 * @param domainSoldIdList
	 * @return 我要卖标的最新数据
	 */
	@RequestMapping(value = "polling/gbsoldData")
	@ResponseBody
	public AjaxResult gbsoldData(@RequestParam("count") String count) {

		// 取得最新的我要卖标信息
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
	 * 
	 * @param domainSoldIdList
	 * @return 悬赏的最新数据
	 */
	@RequestMapping(value = "polling/gbrewardData")
	@ResponseBody
	public AjaxResult gbrewardData(@RequestParam("count") String count) {

		// 取得最新的我要买标信息
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
	 * 获取页面显示标签数据
	 * 
	 * @param domainTagList
	 * @return 标签数据
	 */
	@RequestMapping(value = "polling/TagData")
	@ResponseBody
	public AjaxResult TagData(@RequestParam("count") String count) {

		// 取得最新的我要买标信息
		List<GbjTag> pageDomainTagList = new ArrayList<GbjTag>();
		try {
			pageDomainTagList = gbjTagService.findDomainTagList(count);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("TagData", pageDomainTagList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示个人中心的买标信息的最新数据 01
	 * 
	 * @param domainIdList
	 * @return 全部的最新数据
	 */
	@RequestMapping(value = "polling/UserBuyArticleData")
	@ResponseBody
	public AjaxResult UserBuyArticleData(@RequestParam("id") String id) {

		// 取得最新全部的信息
		List<BuyArticleList> pageDomainUserBuyArticleList = new ArrayList<BuyArticleList>();
		try {
			pageDomainUserBuyArticleList = buyarticleListService.findDomainUserBuyArticleList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("UserBuyArticleData", pageDomainUserBuyArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示个人中心的卖标信息的最新数据 02
	 * 
	 * @param domainIdList
	 * @return 全部的最新数据
	 */
	@RequestMapping(value = "polling/UserSoldArticleData")
	@ResponseBody
	public AjaxResult UserSoldArticleData(@RequestParam("id") String id) {

		// 取得最新全部的信息
		List<SoldArticleList> pageDomainUserSoldArticleList = new ArrayList<SoldArticleList>();
		try {
			pageDomainUserSoldArticleList = soldarticleListService.findDomainUserSoldArticleList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("UserSoldArticleData", pageDomainUserSoldArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示个人中心的悬赏信息的最新数据 03
	 * 
	 * @param domainIdList
	 * @return 全部的最新数据
	 */
	@RequestMapping(value = "polling/UserRewardArticleData")
	@ResponseBody
	public AjaxResult UserRewardArticleData(@RequestParam("id") String id) {

		// 取得最新全部的信息
		List<RewardArticleList> pageDomainUserRewardArticleList = new ArrayList<RewardArticleList>();
		try {
			pageDomainUserRewardArticleList = rewardarticleListService.findDomainUserRewardArticleList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("UserRewardArticleData", pageDomainUserRewardArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 咨询买标一览页面
	 */
	@RequestMapping(value = { "buyarticles" })
	public ModelAndView buyarticles(Model model) {
		ModelAndView mav = null;
		mav = new ModelAndView("modules/paimai/front/buyarticles");
		int gbjBuycount = buyarticleListService.findDomainBuyArticlePageCount();
		mav.addObject("gbjBuycount", gbjBuycount);
		return mav;
	}

	/**
	 * 咨询卖标一览页面
	 */
	@RequestMapping(value = { "soldarticles" })
	public ModelAndView soldarticles(Model model) {
		ModelAndView mav = null;
		mav = new ModelAndView("modules/paimai/front/soldarticles");
		int gbjSoldcount = soldarticleListService.findDomainSoldArticlePageCount();
		mav.addObject("gbjSoldcount", gbjSoldcount);
		return mav;
	}

	/**
	 * 咨询悬赏一览页面
	 */
	@RequestMapping(value = { "rewardarticles" })
	public ModelAndView rewardarticles(Model model) {
		ModelAndView mav = null;
		mav = new ModelAndView("modules/paimai/front/rewardarticles");
		int gbjRewardcount = rewardarticleListService.findDomainRewardArticlePageCount();
		mav.addObject("gbjRewardcount", gbjRewardcount);
		return mav;
	}

	/**
	 * 获取页面显示我要买标信息的全部信息
	 * 
	 * @param domainBuyIdList
	 * @return 我要买标的最新数据
	 */
	@RequestMapping(value = "polling/ArticleBuyData")
	@ResponseBody
	public AjaxResult ArticleBuyData(@RequestParam("count") String count, @RequestParam("page") String page) {

		if (page == null || "".equals(page) || !StringUtils.isNumeric(page)) {
			page = "1"; // 不存在或者不是数字是默认第一页
		}
		int start = Integer.parseInt(count) * (Integer.parseInt(page) - 1);
		int end = Integer.parseInt(count);

		// 取得我要买标信息
		List<BuyArticleList> pageDomainBuyArticleList = new ArrayList<BuyArticleList>();
		try {
			pageDomainBuyArticleList = buyarticleListService.findDomainBuyArticleList(start, end);

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
	 * 
	 * @param domainBuyIdList
	 * @return 卖标的最新数据
	 */
	@RequestMapping(value = "polling/ArticleSoldData")
	@ResponseBody
	public AjaxResult ArticleSoldData(@RequestParam("count") String count, @RequestParam("page") String page) {

		if (page == null || "".equals(page) || !StringUtils.isNumeric(page)) {
			page = "1"; // 不存在或者不是数字是默认第一页
		}
		int start = Integer.parseInt(count) * (Integer.parseInt(page) - 1);
		int end = Integer.parseInt(count);

		// 取得最新的我要卖标信息
		List<SoldArticleList> pageDomainSoldArticleList = new ArrayList<SoldArticleList>();
		try {
			pageDomainSoldArticleList = soldarticleListService.findDomainSoldArticleList(start, end);

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
	 * 
	 * @param domainBuyIdList
	 * @return 悬赏的最新数据
	 */
	@RequestMapping(value = "polling/ArticleRewardData")
	@ResponseBody
	public AjaxResult ArticleRewardData(@RequestParam("count") String count, @RequestParam("page") String page) {

		if (page == null || "".equals(page) || !StringUtils.isNumeric(page)) {
			page = "1"; // 不存在或者不是数字是默认第一页
		}
		int start = Integer.parseInt(count) * (Integer.parseInt(page) - 1);
		int end = Integer.parseInt(count);

		// 取得最新的我要悬赏信息
		List<RewardArticleList> pageDomainRewardArticleList = new ArrayList<RewardArticleList>();
		try {
			pageDomainRewardArticleList = rewardarticleListService.findDomainRewardArticleList(start, end);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleRewardData", pageDomainRewardArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取用户评论信息 by snnu 2017/12/30
	 * 
	 * @param CommentsData
	 * @return 评论信息
	 */
	@RequestMapping(value = "polling/CommentsData")
	@ResponseBody
	public AjaxResult CommentsData(@RequestParam("id") String id) {

		// 单个买标信息和评论信息
		List<GbjUserComments> pageDomainCommentsArticleList = new ArrayList<GbjUserComments>();
		try {
			pageDomainCommentsArticleList = gbjUserCommentsService.findDomainGbjUserCommentsList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleCommentsData", pageDomainCommentsArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获sidebar信息一览（最新5条）12.31 by snnu
	 * 
	 * @param CommentsData
	 * @return 评论信息
	 */
	@RequestMapping(value = "polling/GbjArticle")
	@ResponseBody
	public AjaxResult GbjArticle(@RequestParam("count") String count) {

		// 单个买标信息和评论信息
		List<ArticleList> pageDomainArticleList = new ArrayList<ArticleList>();
		try {
			pageDomainArticleList = articleListService.findDomainArticleList(count);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("GbjArticle", pageDomainArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示单个买标信息和评论信息 by snnu 2017/12/21
	 * 
	 * @param ArticleBuyCommentsData
	 * @return 单个买标信息和评论信息
	 */
	@RequestMapping(value = "polling/ArticleBuyCommentsData")
	@ResponseBody
	public AjaxResult ArticleBuyCommentsData(@RequestParam("id") String id) {

		// 单个买标信息和评论信息
		// List<GbjUserBuyComments> pageDomainBuyCommentsArticleList = new
		// ArrayList<GbjUserBuyComments>();
		try {
			List<GbjUserBuyComments> pageDomainBuyCommentsArticleList = gbjUserBuyCommentsService
					.findDomainArticleBuyCommentsList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleBuyCommentsData", pageDomainBuyCommentsArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示单个买标信息和评论信息 by snnu 2017/12/21
	 * 
	 * @param ArticleBuyCommentsData
	 * @return 单个买标信息和评论信息
	 */
	@RequestMapping(value = "polling/ArticleSoldReplyCommentsData")
	@ResponseBody
	public AjaxResult ArticleSoldReplyCommentsData(@RequestParam("id") String id) {

		// 单个买标信息和评论信息
		List<GbjUserSoldCommentsReply> pageDomainSoldReplyCommentsArticleList = new ArrayList<GbjUserSoldCommentsReply>();
		try {
			pageDomainSoldReplyCommentsArticleList = gbjUserSoldCommentsReplyService
					.findDomainArticleSoldReplyCommentsList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleSoldReplyCommentsData", pageDomainSoldReplyCommentsArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示单个买标信息和评论信息 by snnu 2017/12/21
	 * 
	 * @param ArticleBuyCommentsData
	 * @return 单个买标信息和评论信息
	 */
	@RequestMapping(value = "polling/ArticleRewardReplyCommentsData")
	@ResponseBody
	public AjaxResult ArticleRewardReplyCommentsData(@RequestParam("id") String id) {

		// 单个买标信息和评论信息
		List<GbjUserRewardCommentsReply> pageDomainRewardReplyCommentsArticleList = new ArrayList<GbjUserRewardCommentsReply>();
		try {
			pageDomainRewardReplyCommentsArticleList = gbjUserRewardCommentsReplyService
					.findDomainArticleRewardReplyCommentsList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleRewardReplyCommentsData", pageDomainRewardReplyCommentsArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示单个买标信息和评论信息 by snnu 2017/12/21
	 * 
	 * @param ArticleBuyCommentsData
	 * @return 单个买标信息和评论信息
	 */
	@RequestMapping(value = "polling/ArticleBuyReplyCommentsData")
	@ResponseBody
	public AjaxResult ArticleBuyReplyCommentsData(@RequestParam("id") String id) {

		// 单个买标信息和评论信息
		List<GbjUserBuyCommentsReply> pageDomainBuyReplyCommentsArticleList = new ArrayList<GbjUserBuyCommentsReply>();
		try {
			pageDomainBuyReplyCommentsArticleList = gbjUserBuyCommentsReplyService
					.findDomainArticleBuyReplyCommentsList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleBuyReplyCommentsData", pageDomainBuyReplyCommentsArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示单个賣标信息和评论信息 by snnu 2017/12/29
	 * 
	 * @param ArticleSoldCommentsData
	 * @return 单个买标信息和评论信息
	 */
	@RequestMapping(value = "polling/ArticleSoldCommentsData")
	@ResponseBody
	public AjaxResult ArticleSoldCommentsData(@RequestParam("id") String id) {

		// 单个买标信息和评论信息
		List<GbjUserSoldComments> pageDomainSoldCommentsArticleList = new ArrayList<GbjUserSoldComments>();
		try {
			pageDomainSoldCommentsArticleList = gbjUserSoldCommentsService.findDomainArticleSoldCommentsList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleSoldCommentsData", pageDomainSoldCommentsArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}

	/**
	 * 获取页面显示单个悬赏信息和评论信息 by snnu 2017/12/29
	 * 
	 * @param ArticleSoldCommentsData
	 * @return 单个买标信息和评论信息
	 */
	@RequestMapping(value = "polling/ArticleRewardCommentsData")
	@ResponseBody
	public AjaxResult ArticleRewardCommentsData(@RequestParam("id") String id) {

		// 单个买标信息和评论信息
		List<GbjUserRewardComments> pageDomainRewardCommentsArticleList = new ArrayList<GbjUserRewardComments>();
		try {
			pageDomainRewardCommentsArticleList = gbjUserRewardCommentsService.findDomainArticleRewardCommentsList(id);

			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("ArticleRewardCommentsData", pageDomainRewardCommentsArticleList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}
	/*
	 * @RequestMapping(value="/test")
	 * 
	 * @ResponseBody public String test(@RequestParam("id") String
	 * id,@RequestParam("count") int count) { System.out.println("id = " + id);
	 * return "id= " + id; }
	 */

	// 买标点赞
	@RequestMapping(value = { "buyupcounts" })
	@ResponseBody
	public AjaxResult buyupcounts(Model model, GbjBuy gbjBuy, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		/*
		 * model.addAttribute("domainInfo1", JsonMapper.toJsonString(gbjBuy));
		 */
		try {

			// STEP1 提交查询信息，保存到数据库
			gbjBuyService.updateCount(gbjBuy);

			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");

			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【" + e.getMessage() + "】");
			return AjaxResult.makeError("提交查询成功失败【" + e.getMessage() + "】");
		}

	}

	// 卖标点赞
	@RequestMapping(value = { "soldupcounts" })
	@ResponseBody
	public AjaxResult soldupcounts(Model model, GbjSold gbjSold, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		/*
		 * model.addAttribute("domainInfo1", JsonMapper.toJsonString(gbjBuy));
		 */
		try {

			// STEP1 提交查询信息，保存到数据库
			gbjSoldService.updateCount(gbjSold);

			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");

			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【" + e.getMessage() + "】");
			return AjaxResult.makeError("提交查询成功失败【" + e.getMessage() + "】");
		}

	}

	// 悬赏点赞
	@RequestMapping(value = { "rewardupcounts" })
	@ResponseBody
	public AjaxResult rewardupcounts(Model model, GbjReward gbjReward, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		/*
		 * model.addAttribute("domainInfo1", JsonMapper.toJsonString(gbjBuy));
		 */
		try {

			// STEP1 提交查询信息，保存到数据库
			gbjRewardService.updateCount(gbjReward);

			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");

			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【" + e.getMessage() + "】");
			return AjaxResult.makeError("提交查询成功失败【" + e.getMessage() + "】");
		}

	}

	/*
	 * 静态页面
	 */
	@RequestMapping(value = { "static_trademark" })
	public String static_trademark(Model model) {
		return "modules/paimai/front/static_trademark";
	}

	@RequestMapping(value = { "static_trademark1" })
	public String static_trademark1(Model model) {
		return "modules/paimai/front/static_trademark1";
	}

	@RequestMapping(value = { "static_trademark2" })
	public String static_trademark2(Model model) {
		return "modules/paimai/front/static_trademark2";
	}

	@RequestMapping(value = { "static_trademark3" })
	public String static_trademark3(Model model) {
		return "modules/paimai/front/static_trademark3";
	}

	@RequestMapping(value = { "static_trademark4" })
	public String static_trademark4(Model model) {
		return "modules/paimai/front/static_trademark4";
	}

	@RequestMapping(value = { "static_qa0" })
	public String static_qa0(Model model) {
		return "modules/paimai/front/static_qa0";
	}

	@RequestMapping(value = { "static_qa1" })
	public String static_qa1(Model model) {
		return "modules/paimai/front/static_qa1";
	}

	@RequestMapping(value = { "static_qa2" })
	public String static_qa2(Model model) {
		return "modules/paimai/front/static_qa2";
	}

	@RequestMapping(value = { "static_qa3" })
	public String static_qa3(Model model) {
		return "modules/paimai/front/static_qa3";
	}

	@RequestMapping(value = { "static_patent0" })
	public String static_patent0(Model model) {
		return "modules/paimai/front/static_patent0";
	}

	@RequestMapping(value = { "static_patent1" })
	public String static_patent1(Model model) {
		return "modules/paimai/front/static_patent1";
	}

	@RequestMapping(value = { "static_patent2" })
	public String static_patent2(Model model) {
		return "modules/paimai/front/static_patent2";
	}

	@RequestMapping(value = { "static_patent3" })
	public String static_patent3(Model model) {
		return "modules/paimai/front/static_patent3";
	}

	@RequestMapping(value = { "static_patent4" })
	public String static_patent4(Model model) {
		return "modules/paimai/front/static_patent4";
	}

	@RequestMapping(value = { "static_patent5" })
	public String static_patent5(Model model) {
		return "modules/paimai/front/static_patent5";
	}

	@RequestMapping(value = { "static_patent6" })
	public String static_patent6(Model model) {
		return "modules/paimai/front/static_patent6";
	}

	@RequestMapping(value = { "static_patent7" })
	public String static_patent7(Model model) {
		return "modules/paimai/front/static_patent7";
	}

	@RequestMapping(value = { "static_patent8" })
	public String static_patent8(Model model) {
		return "modules/paimai/front/static_patent8";
	}

	@RequestMapping(value = { "static_patent9" })
	public String static_patent9(Model model) {
		return "modules/paimai/front/static_patent9";
	}

	@RequestMapping(value = { "static_patent10" })
	public String static_patent10(Model model) {
		return "modules/paimai/front/static_patent10";
	}

	@RequestMapping(value = { "static_patent11" })
	public String static_patent11(Model model) {
		return "modules/paimai/front/static_patent11";
	}

	@RequestMapping(value = { "static_patent12" })
	public String static_patent12(Model model) {
		return "modules/paimai/front/static_patent12";
	}

	@RequestMapping(value = { "static_patent13" })
	public String static_patent13(Model model) {
		return "modules/paimai/front/static_patent13";
	}

	@RequestMapping(value = { "static_patent14" })
	public String static_patent14(Model model) {
		return "modules/paimai/front/static_patent14";
	}

	@RequestMapping(value = { "static_patent15" })
	public String static_patent15(Model model) {
		return "modules/paimai/front/static_patent15";
	}

	@RequestMapping(value = { "static_patent16" })
	public String static_patent16(Model model) {
		return "modules/paimai/front/static_patent16";
	}

	@RequestMapping(value = { "static_patent17" })
	public String static_patent17(Model model) {
		return "modules/paimai/front/static_patent17";
	}

	@RequestMapping(value = { "static_patent18" })
	public String static_patent18(Model model) {
		return "modules/paimai/front/static_patent18";
	}

	@RequestMapping(value = { "static_patent19" })
	public String static_patent19(Model model) {
		return "modules/paimai/front/static_patent19";
	}

	@RequestMapping(value = { "static_international0" })
	public String static_international0(Model model) {
		return "modules/paimai/front/static_international0";
	}

	@RequestMapping(value = { "static_international1" })
	public String static_international1(Model model) {
		return "modules/paimai/front/static_international1";
	}

	@RequestMapping(value = { "static_international2" })
	public String static_international2(Model model) {
		return "modules/paimai/front/static_international2";
	}

	@RequestMapping(value = { "static_international3" })
	public String static_international3(Model model) {
		return "modules/paimai/front/static_international3";
	}

	@RequestMapping(value = { "static_international4" })
	public String static_international4(Model model) {
		return "modules/paimai/front/static_international4";
	}

	@RequestMapping(value = { "static_international5" })
	public String static_international5(Model model) {
		return "modules/paimai/front/static_international5";
	}

	@RequestMapping(value = { "static_international6" })
	public String static_international6(Model model) {
		return "modules/paimai/front/static_international6";
	}

	@RequestMapping(value = { "static_international7" })
	public String static_international7(Model model) {
		return "modules/paimai/front/static_international7";
	}

	@RequestMapping(value = { "static_international8" })
	public String static_international8(Model model) {
		return "modules/paimai/front/static_international8";
	}

	@RequestMapping(value = { "static_international9" })
	public String static_international9(Model model) {
		return "modules/paimai/front/static_international9";
	}

	@RequestMapping(value = { "static_lawservice0" })
	public String static_lawservice0(Model model) {
		return "modules/paimai/front/static_lawservice0";
	}

	@RequestMapping(value = { "static_lawservice1" })
	public String static_lawservice1(Model model) {
		return "modules/paimai/front/static_lawservice1";
	}

	@RequestMapping(value = { "static_lawservice2" })
	public String static_lawservice2(Model model) {
		return "modules/paimai/front/static_lawservice2";
	}

	@RequestMapping(value = { "static_lawservice3" })
	public String static_lawservice3(Model model) {
		return "modules/paimai/front/static_lawservice3";
	}

	@RequestMapping(value = { "static_lawservice4" })
	public String static_lawservice4(Model model) {
		return "modules/paimai/front/static_lawservice4";
	}

	@RequestMapping(value = { "static_lawservice5" })
	public String static_lawservice5(Model model) {
		return "modules/paimai/front/static_lawservice5";
	}

	@RequestMapping(value = { "static_lawservice6" })
	public String static_lawservice6(Model model) {
		return "modules/paimai/front/static_lawservice6";
	}

	@RequestMapping(value = { "static_lawservice7" })
	public String static_lawservice7(Model model) {
		return "modules/paimai/front/static_lawservice7";
	}

	@RequestMapping(value = { "static_lawservice8" })
	public String static_lawservice8(Model model) {
		return "modules/paimai/front/static_lawservice8";
	}

	@RequestMapping(value = { "static_lawservice9" })
	public String static_lawservice9(Model model) {
		return "modules/paimai/front/static_lawservice9";
	}

	@RequestMapping(value = { "static_lawservice10" })
	public String static_lawservice10(Model model) {
		return "modules/paimai/front/static_lawservice10";
	}

	@RequestMapping(value = { "static_lawservice11" })
	public String static_lawservice11(Model model) {
		return "modules/paimai/front/static_lawservice11";
	}

	@RequestMapping(value = { "static_lawservice12" })
	public String static_lawservice12(Model model) {
		return "modules/paimai/front/static_lawservice12";
	}

	@RequestMapping(value = { "static_lawservice13" })
	public String static_lawservice13(Model model) {
		return "modules/paimai/front/static_lawservice13";
	}

	@RequestMapping(value = { "static_knowledge" })
	public String static_knowledge(Model model) {
		return "modules/paimai/front/static_knowledge";
	}

	@RequestMapping(value = { "static_copyright" })
	public String static_copyright(Model model) {
		return "modules/paimai/front/static_copyright";
	}

	@RequestMapping(value = { "static_search" })
	public String static_search(Model model) {
		return "modules/paimai/front/static_search";
	}
	/**
	 * 我要买标信息提交 2017/12/3 by snnu
	 */
	/*
	 * @RequestMapping(value= {"gbBuy"})
	 * 
	 * @ResponseBody public AjaxResult gbBuy(Model model, GbjBuy gbjBuy,
	 * HttpServletRequest request, RedirectAttributes redirectAttributes) {
	 * 
	 * model.addAttribute("domainInfo1Json", JsonMapper.toJsonString(gbjBuy)); try{
	 * //STEP1 提交查询信息，保存到数据库 gbjBuyService.saveBuy(gbjBuy);
	 * 
	 * addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
	 * 
	 * return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！"); } catch (Exception e) {
	 * logger.error(e.getMessage()); addMessage(redirectAttributes,
	 * "提交查询成功失败【"+e.getMessage()+"】"); return
	 * AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】"); }
	 * 
	 * }
	 */
	/**
	 * 我要卖标信息提交 2017/12/5 by snnu
	 */
	/*
	 * @RequestMapping(value= {"upcounts"})
	 * 
	 * @ResponseBody public AjaxResult upcounts(Model model, GbjBuy gbjBuy,
	 * HttpServletRequest request, RedirectAttributes redirectAttributes) {
	 * 
	 * 
	 * model.addAttribute("domainInfo1", JsonMapper.toJsonString(gbjBuy)); try{
	 * 
	 * System.out.print("zzzzz"); //STEP1 提交查询信息，保存到数据库
	 * gbjBuyService.updateCount(gbjBuy);
	 * 
	 * addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
	 * 
	 * return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！"); } catch (Exception e) {
	 * logger.error(e.getMessage()); addMessage(redirectAttributes,
	 * "提交查询成功失败【"+e.getMessage()+"】"); return
	 * AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】"); }
	 * 
	 * }
	 */

	/**
	 * 我要卖标信息提交 2017/12/5 by snnu
	 */
	/*
	 * @RequestMapping(value= {"gbsold"})
	 * 
	 * @ResponseBody public AjaxResult gbsold(Model model, GbjSold gbjSold,
	 * HttpServletRequest request, RedirectAttributes redirectAttributes) {
	 * 
	 * model.addAttribute("domainInfo2Json", JsonMapper.toJsonString(gbjSold)); try{
	 * 
	 * 
	 * //STEP1 提交查询信息，保存到数据库 gbjSoldService.save(gbjSold);
	 * 
	 * addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
	 * 
	 * return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！"); } catch (Exception e) {
	 * logger.error(e.getMessage()); addMessage(redirectAttributes,
	 * "提交查询成功失败【"+e.getMessage()+"】"); return
	 * AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】"); }
	 * 
	 * }
	 */
	/**
	 * 悬赏起名信息提交 2017/12/5 by snnu
	 */
	/*
	 * @RequestMapping(value= {"gbreward"})
	 * 
	 * @ResponseBody public AjaxResult gbreward(Model model, GbjReward gbjReward,
	 * HttpServletRequest request, RedirectAttributes redirectAttributes) {
	 * 
	 * model.addAttribute("domainInfo3Json", JsonMapper.toJsonString(gbjReward));
	 * try{
	 * 
	 * 
	 * //STEP1 提交查询信息，保存到数据库 gbjRewardService.save(gbjReward);
	 * 
	 * addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
	 * 
	 * return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！"); } catch (Exception e) {
	 * logger.error(e.getMessage()); addMessage(redirectAttributes,
	 * "提交查询成功失败【"+e.getMessage()+"】"); return
	 * AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】"); }
	 * 
	 * }
	 */
}
