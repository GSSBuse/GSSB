package com.thinkgem.jeesite.modules.wx.web.domainname;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.bean.Attachment;
import com.thinkgem.jeesite.common.wx.bean.PayResult;
import com.thinkgem.jeesite.common.wx.bean.UnifiedOrder;
import com.thinkgem.jeesite.common.wx.bean.WxReturnUnifiedOrder;
import com.thinkgem.jeesite.common.wx.oauth.Pay;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyDomainnameDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBonus;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyWxpayResult;
import com.thinkgem.jeesite.modules.sys.service.dy.AuctionScheduleService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyAttentionService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBidhistoryService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBonusService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyCashFlowService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyNewsService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyWxpayResultService;
import com.thinkgem.jeesite.modules.sys.utils.SendEmail;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.domainname.ShareOrSecondBonus;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * @author wufl.fnst
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/domainname")
public class IcenterController implements Constant{
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DyClientService clientService;
	
	@Autowired
	private DyFinanceService financeService;
	
	@Autowired
	private DyCashFlowService cashFlowService;
	
	@Autowired
	private DyBonusService bonusService;
	
	@Autowired
	private DyDomainnameService domainnameService;
	
	@Autowired
	private DyBidhistoryService bidhistoryService;
	
	@Autowired
	private DyAttentionService attentionService;
	
	@Autowired
	private DyNewsService newsService;
	
	@Autowired
	private DyWxpayResultService wxpayResultService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuctionScheduleService auctionScheduleService;
	
	@Autowired
	private DyDomainnameDao dyDomainnameDao;
	/*
	 * ==================================
	 *   页面显示controller
	 * ==================================
	 */
	//关注页面
	@RequestMapping(value = {"error"}, method={RequestMethod.GET})
	public String error(Model model) {
		return "modules/wx/domainname/error";
	}
	//个人中心导航页显示
	@RequestMapping(value = {"icenter"})
	public String index(Model model) {

		if ("true".equals(Global.getConfig("local.debug"))) {
			DyClient user = clientService.get("1");
			UserUtils.getSession().setAttribute("current_dy_client", user);
		}
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user1 = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user1);
		}
		
		return "modules/wx/domainname/icenter";
	}
	
	//个人信息显示
	@RequestMapping(value = {"personalInfo"}, method={RequestMethod.GET})
	public String personalInfo(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user);
			return "modules/wx/domainname/personalInfo";
		}
	}
	
	//输入支付密码页面显示
	@RequestMapping(value = {"inputPayPassword"}, method={RequestMethod.GET})
	public String inputPayPassword(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user);
			return "modules/wx/domainname/inputPayPassword";
		}
	}

	//财务信息显示
	@RequestMapping(value = {"financeInfo"},method={RequestMethod.GET})
	public String financeInfo(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user);
			return "modules/wx/domainname/financeInfo";
		}
	}
	
	//安全设置显示
	@RequestMapping(value = {"securitySettings"}, method={RequestMethod.GET})
	public String securitySettings(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user);
			return "modules/wx/domainname/securitySettings";
		}
	}
	
	//修改设置支付密码显示
	@RequestMapping(value = {"addPayPassword"}, method={RequestMethod.GET})
	public String addPayPassword(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user);
			return "modules/wx/domainname/addPayPassword";
		}
	}
	
	//提现进度显示
	@RequestMapping(value = {"withdrawalsProgress"}, method={RequestMethod.GET})
	public String withdrawalsProgress(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user);
			return "modules/wx/domainname/withdrawalsProgress";
		}
	}
	
	//我的交易显示
	@RequestMapping(value = {"myTransactionsInfo"}, method={RequestMethod.GET})
	public String myTransactionsInfo(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user);
			return "modules/wx/domainname/myTransactionsInfo";
		}
	}
	
	//修改手机显示
	@RequestMapping(value = {"changePhone"})
	public String changePhone(Model model) {
		if (DySysUtils.getCurrentDyClient() != null){
			return "modules/wx/domainname/changePhone";
		}else{
			return "modules/wx/domainname/error";
		}
		
	}
	
	//修改邮箱显示
	@RequestMapping(value = {"changeEmail"})
	public String changeE_mail(Model model) {
		if(DySysUtils.getCurrentDyClient() != null){
			return "modules/wx/domainname/changeEmail";
		}else{
			return "modules/wx/domainname/error";
		}
	}
	
	//资金流信息显示
	@RequestMapping(value = {"cashFlow"})
	public String cashFlow(Model model) {
		if(DySysUtils.getCurrentDyClient() != null){
			return "modules/wx/domainname/cashFlow";
		}else{
			return "modules/wx/domainname/error";
		}
	}
	
	//冻结记录显示
	@RequestMapping(value = {"freezeRecord"})
	public String freezeRecord(Model model) {
		if(DySysUtils.getCurrentDyClient() != null){
			return "modules/wx/domainname/freezeRecord";
		}else{
			return "modules/wx/domainname/error";
		}
	}
	
	//佣金记录显示
	@RequestMapping(value = {"commissionRecord"})
	public String commissionRecord(Model model,String clientId) {
		if(clientId == null){//登录用户访问
			if(DySysUtils.getCurrentDyClient()!=null){
				model.addAttribute("clientId","clientId_null");//clientId_null表示非分享访问
				model.addAttribute("current_dy_client_id", DySysUtils.getCurrentDyClient().getId());//存储当前登录用户的id,用于分享时地址中参数clientId的值
				return "modules/wx/domainname/commissionRecord";
			}else{
				return "modules/wx/domainname/error";
			}
		}else{//clientId不为null表示通过分享访问
			model.addAttribute("clientId", clientId);//分享用，记录分享者的id
			return "modules/wx/domainname/commissionRecord";
		}
	}
	
	//分享红包，次高价红包记录
	@RequestMapping(value = {"bonusRecord"})
	public String bonusRecord(Model model,String clientId) {
		if(clientId == null){//登录用户访问
			if(DySysUtils.getCurrentDyClient()!=null){
				model.addAttribute("clientId","clientId_null");//clientId_null表示非分享访问
				model.addAttribute("current_dy_client_id", DySysUtils.getCurrentDyClient().getId());//存储当前登录用户的id,用于分享时地址中参数clientId的值
				return "modules/wx/domainname/bonusRecord";
			}else{
				return "modules/wx/domainname/commissionRecord";
			}
		}else{//clientId不为null表示通过分享访问
			model.addAttribute("clientId", clientId);//分享用，记录分享者的id
			return "modules/wx/domainname/bonusRecord";
		}
	}
	
	/*
	 * ==================================
	 *   数据处理controller
	 * ==================================
	 */
	
	/**
	 * 个人中心：更改手机的发送验证码
	 */
	@RequestMapping(value = {"changeMobile"}, method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult changeMobile(HttpServletRequest request, Model model,String mobile) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		
		if(u != null){
			/**向用户提供的手机号（mobile）发送验证码：12345688,待检**/
			Map<String, String> tplValueMap = new HashMap<String, String>();
			//6位随机验证码
			Random random = new Random();
			int n = random.nextInt(899999);
			n = n+100000;
			String sys_verificationCode = String.valueOf(n);
			tplValueMap.put("code", sys_verificationCode);
			try {
				System.out.println("code: "+sys_verificationCode); 
//				SmsSendUtils.sendSms(Long.parseLong(mobile), Constant.TPL_ID_1, tplValueMap);
			} catch (Exception e) {
				AjaxResult ar = AjaxResult.makeError("");
				return ar;
			}
			AjaxResult ar = AjaxResult.makeSuccess("");
			if (WeChat.isWeiXin(request)) {
				ar.getData().put("sys_verificationCode", sys_verificationCode);
			}
			UserUtils.getSession().setAttribute("sys_verificationCode", sys_verificationCode);
			return ar;
		}else{
			AjaxResult ar = AjaxResult.makeWarn("非法访问！");
			return ar;
		}
		
	}
	
	/**
	 * 个人中心：验证码验证通过，更改手机号
	 */
	@RequestMapping(value = {"verificationPhone"}, method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult verificationPhone(Model model,String mobile, String verificationCode) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				if (verificationCode == null || !verificationCode.equals(UserUtils.getSession().getAttribute("sys_verificationCode"))) {
					AjaxResult ar = AjaxResult.makeError("验证码不正确");
					return ar;
				}
				int flag = clientService.changeMobile(u.getId(), mobile);
				if (flag == 1) {
					u.setMobile(mobile);
					AjaxResult ar = AjaxResult.makeSuccess("会员手机号更新成功");
					UserUtils.getSession().removeAttribute("sys_verificationCode");
					return ar;
				} else {
					return AjaxResult.makeWarn("会员手机号更新有误");
				}
			} catch (Exception e) {
				logger.error("会员手机号更新出错", e);
				return AjaxResult.makeError("会员手机号更新出错:" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：修改email，获取我的个人信息
	 */
	@RequestMapping(value = {"userInfoForEmail"}, method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult userInfoForEmail(HttpServletRequest request, Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			/** 向用户提供的手机号（mobile）发送验证码：123,待检 **/
			Map<String, String> tplValueMap = new HashMap<String, String>();
			//6位随机验证码
			Random random = new Random();
			int n = random.nextInt(899999);
			n = n+100000;
			String sys_verificationCode = String.valueOf(n);
			tplValueMap.put("code", sys_verificationCode);
			try {
				System.out.println("code: "+sys_verificationCode);
//				SmsSendUtils.sendSms(Long.parseLong(u.getMobile()), Constant.TPL_ID_1, tplValueMap);
			} catch (Exception e) {
				AjaxResult ar = AjaxResult.makeError("");
				ar.getData().put("userinfo", u);
				return ar;
			}
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("userinfo", u);
			if (WeChat.isWeiXin(request)) {
				ar.getData().put("sys_verificationCode", sys_verificationCode);
			}
			UserUtils.getSession().setAttribute("sys_verificationCode", sys_verificationCode);
			return ar;
		} else {
			return AjaxResult.makeWarn("非法访问！");
		}
	}
	/**
	 * 个人中心：页面跳回icenter,获取我的个人信息
	 */
	@RequestMapping(value = {"userInfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult userInfo(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("userinfo", u);
			return ar;
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：修改email,验证已通过，更新email或者无email时的添加email
	 */
	@RequestMapping(value = {"emailInput"}, method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult emailInput(Model model,String email,HttpServletRequest req, String verificationCode) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				if ( !(StringUtils.isEmpty(u.getEmailFlag()) || EMAIL_FLAG_2.equals(u.getEmailFlag())) // 空白或为2时，不需要检查验证码
						&& (verificationCode == null || !verificationCode.equals(UserUtils.getSession().getAttribute("sys_verificationCode")))) {
					AjaxResult ar = AjaxResult.makeError("验证码不正确");
					return ar;
				}
				
				// 发送邮件：激活链接，对参数params用Base64编码.
				String params = "clientId="+u.getId() + "&timeStamp=" + new Date().getTime() + "&email=" + email;
				SendEmail.sendEmail(email, null, req, Encodes.encodeBase64(params));
				int flag = clientService.changeEmail(u.getId(), email);
				if (flag == 1) {
					u.setEmail(email);
					u.setEmailFlag(EMAIL_FLAG_0);
					AjaxResult ar = AjaxResult.makeSuccess("激活邮件已经发送至您的邮箱");
					UserUtils.getSession().removeAttribute("sys_verificationCode");
					return ar;
				} else {
					return AjaxResult.makeWarn("系统有误，重新操作！");
				}
			} catch (Exception e) {
				logger.error("会员email更新出错", e);
				return AjaxResult.makeError("email邮件无法发送" );
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：激活email，更新emailFlag
	 */
	@RequestMapping(value = {"activateEmail"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public void activateEmail(Model model,String params,HttpServletRequest req,HttpServletResponse res,String email) throws Exception {
		res.setCharacterEncoding(UTF_8);
		res.setContentType("text/html; charset=utf-8");
		//email为null，说明激活操作，否则是请求发送激活链接
		if(email ==null){
			try {
				// 获取激活用户用户信息
				if(params != null){
					//params需要用Base64解码,解码后的格式为：clientId=1111&timeStamp=54545454545&email=6563@qq.com;
					String stringParams= Encodes.decodeBase64String(params);
					String decodeParams [] = stringParams.split("&");
					String decodeClientId = decodeParams[0].split("=")[1];
					String decodeTimeStamp = decodeParams[1].split("=")[1];
					String decodeEmail = decodeParams[2].split("=")[1];
					
					//比对参数有效性
					DyClient u = clientService.get(decodeClientId);
					if(u == null){
						throw new Exception("无此用户");
					}
					
					String activeEmail = u.getEmail();
					if(activeEmail == null){
						throw new Exception("email为空");
					}
					
					if(!decodeEmail.equals(activeEmail)){
						throw new Exception("已失效，邮箱与平台不一致");
					}
					
					if((new Date().getTime() + 48*3600*1000) < Long.parseLong(decodeTimeStamp)){
						throw new Exception("已过期，您可以重新获取激活链接");
					}
					
					String activeEmailFlag = u.getEmailFlag();
					if(EMAIL_FLAG_0.equals(activeEmailFlag)){
						logger.debug("首次激活邮箱。");
						clientService.saveEmailFlag(u.getId(),EMAIL_FLAG_1);
					} else if (EMAIL_FLAG_1.equals(activeEmailFlag)){
						logger.debug("邮箱已经激活，这是再次激活（什么都不做）。");
					} else {
						logger.error("邮箱flag不为1和0。有些奇怪： " + activeEmailFlag);
					}
					
//					u.setEmailFlag(EMAIL_FLAG_1);
//					clientService.save(u);
					PrintWriter out = res.getWriter();
					out.print("邮箱已经成功激活！");
					out.close();
				}
			} catch (Exception e) {
				logger.error("激活email出错：" + params, e);
				try{
					PrintWriter out = res.getWriter();
					out.print("激活失败！"+ e.getMessage());
					out.close();
				}catch(Exception e1){
					
				}
			}
		}else{
			// 获取登录用户信息;
			DyClient currnetClient = DySysUtils.getCurrentDyClient();
			try{
				//发送邮件:激活链接,对参数param用Base64编码.
				String param = "clientId="+currnetClient.getId() + "&timeStamp=" + (new Date()).getTime() + "&email=" + email;
				SendEmail.sendEmail(currnetClient.getEmail(),null,req,Encodes.encodeBase64(param));
			}catch(Exception e){
				throw e;
			}
		}
	}
	
	/**
	 * 个人中心：获取当前会员的经纪人信息
	 */
	@RequestMapping(value = {"brokerInfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult brokerInfo(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				User broker = null;
				broker = clientService.viewBroker(u.getBrokerId());
				if (broker != null) {
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("brokerinfo", broker);
					return ar;
				} else {
					return AjaxResult.makeWarn("查询经纪人失败");
				}
			} catch (Exception e) {
				logger.error("查询经纪人出错", e);
				return AjaxResult.makeError("查询经纪人出错:" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：获取我的个人信息
	 */
	@RequestMapping(value = {"myInfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult myInfo(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if(u != null){
			//ajax返回的数据
			AjaxResult ar = AjaxResult.makeSuccess("");
			
			//用户信息
			ar.getData().put("userinfo", u);
			//财务信息
			ar.getData().putAll(financeService.Financeinfo().getData());// Financeinfo() 返回我的财务信息
			//交易信息
			ar.getData().putAll(domainnameService.myTransactions().getData()); // myTransactions() 返回我的交易信息
			
			return ar;
		}else{
			//ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
	}
	
	/**
	 * 个人中心：交易信息刷新
	 */
	@RequestMapping(value = {"refreshMyTransactions"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult refreshMyTransactions(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if(u != null){
			// ajax返回的数据
			AjaxResult ar = domainnameService.myTransactions();// 交易信息
			ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
			return ar;
		}else{
			//ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
		
	}
	
	/**
	 * 个人中心：待处理的交易提示
	 */
	@RequestMapping(value = {"myTransactionsWaitToDeal"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult myTransactionsWaitToDeal(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if(u != null){
			//我的交易：待处理
			int myTransactionsWaitToDealSize = 0;
			//获取我的交易：待处理交易的域名信息(参与买)
			List<Map<String, Object>> myTransactionsBoughtList = bidhistoryService.myTransactionsBuyInfo(u.getId(),DOMAIN_STATUS_1_);// 该结果在数据库语句中剔除了15状态，一个Map存储一条记录	：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）  waitTime(截止时间)
			if(!myTransactionsBoughtList.isEmpty()){
				for(int i = 0;i < myTransactionsBoughtList.size();i++){
					String status = myTransactionsBoughtList.get(i).get("status").toString();
					if(status.equals(DOMAIN_STATUS_11) || status.equals(DOMAIN_STATUS_13)){
						myTransactionsWaitToDealSize = myTransactionsWaitToDealSize  + 1;
					}
				}
			}
			
			//获取我的交易：待处理成交易的域名信息(卖)
			List<Map<String, Object>> myTransactionsSoldList = bidhistoryService.myTransactionsSellInfo(u.getId(),DOMAIN_STATUS_1_);//该结果在数据库语句中剔除了15状态，一个Map存储一条记录：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）  waitTime(截止时间)
			if(!myTransactionsSoldList.isEmpty()){
				for(int i = 0;i < myTransactionsSoldList.size();i++){
					String status = myTransactionsSoldList.get(i).get("status").toString();
					if(status.equals(DOMAIN_STATUS_12)){
						myTransactionsWaitToDealSize = myTransactionsWaitToDealSize + 1;
					}
				}
			}
			
			// ajax返回的数据
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("myTransactionsWaitToDealSize", myTransactionsWaitToDealSize);
			return ar;
		}else{
			//ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
		
	}
	
	/**
	 * 个人中心：财务管理刷新
	 */
	@RequestMapping(value = {"refreshFinanceinfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult refreshFinanceinfo(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if(u !=null){
			//财务信息
			AjaxResult ar = financeService.Financeinfo();
			//红包开启标志
			ar.getData().put("shareBonusEnable", DySysUtils.SHARE_BONUS_ENABLE);
			return ar;
		}else{
			//ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
		
	}
	
	/**
	 * 个人中心：获取我的资金流信息
	 */
	@RequestMapping(value = {"myCashFlow"},method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult myCashFlow(Model model,String pageIndex) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			// 获取登录用户的资金流信息：冻结、解冻的记录不需要
			DyCashFlow dyCashFlow = new DyCashFlow();
			dyCashFlow.setClientId(u.getId());
			dyCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
			try {
				
				Page<DyCashFlow> page = new Page<DyCashFlow>();
				page.setPageSize(PAGE_SIZE_12);
				page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
				page.setOrderBy("a.operate_time desc");
				page = cashFlowService.findCashFlowPage(page, dyCashFlow);
				if (page.getPageNo() < Integer.parseInt(pageIndex)) {
					return AjaxResult.makeWarn("没有更多资金流了");
				}
				if (page.getList().isEmpty()) {
					AjaxResult ar = AjaxResult.makeWarn("当前没有资金流信息");
					ar.getData().put("cashflowinfo", page.getList());
					ar.getData().put("pageIndex", page.getPageNo());
					return ar;
				} else {
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("cashflowinfo", page.getList());
					ar.getData().put("pageIndex", page.getPageNo());
					return ar;
				}
			} catch (Exception e) {
				logger.error("获取资金流出错", e);
				return AjaxResult.makeError("获取资金流出错:" + e.getMessage());
			}
		} else {
			// ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
	}
	
	/**
	 * 个人中心：获取我的冻结资金信息
	 */
	@RequestMapping(value = {"freezeinfo"},method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult freezeInfo(Model model,String pageIndex) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			// 获取登录用户的冻结资金流信息：冻结和提现记录
			DyCashFlow dyCashFlow = new DyCashFlow();
			dyCashFlow.setClientId(u.getId());
			try {
				Page<DyCashFlow> page = new Page<DyCashFlow>();
				page.setPageSize(PAGE_SIZE_12);
				page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
				page.setOrderBy("a.create_date desc");
				page = cashFlowService.findFreezeInfoPage(page, dyCashFlow);
				if (page.getPageNo() < Integer.parseInt(pageIndex)) {
					return AjaxResult.makeWarn("没有更多冻结记录了");
				}
				if (page.getList().isEmpty()) {
					AjaxResult ar = AjaxResult.makeWarn("当前没有冻结记录信息");
					ar.getData().put("freezeInfo", page.getList());
					ar.getData().put("pageIndex", page.getPageNo());
					return ar;
				} else {
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("freezeinfo", page.getList());
					ar.getData().put("pageIndex", page.getPageNo());
					return ar;
				}
			} catch (Exception e) {
				logger.error("获取冻结记录出错", e);
				return AjaxResult.makeError("获取冻结记录出错:" + e.getMessage());
			}
		} else {
			// ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
	}
	
	/**
	 * 个人中心：获取我的领佣金红包信息
	 */
	@RequestMapping(value = {"myCommissionRecordOrBonusRecord"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult myCommissionRecord(Model model,String type,String clientId) {
		// 获取登录用户信息或分享者信息
		DyClient u = null;
		if(clientId.equals("clientId_null")){
			u = DySysUtils.getCurrentDyClient();
			if(u == null){
				AjaxResult ar = AjaxResult.makeWarn("您没有登录");
				return ar;
			}
		}else{
			u = clientService.get(clientId);
			if(u == null){
				AjaxResult ar = AjaxResult.makeWarn("非法访问");
				return ar;
			}
		}
		
		//获取登录用户的红包佣金信息
		DyBonus dyBonus = new DyBonus();
		dyBonus.setClientId(u.getId());
		dyBonus.setDelFlag(DEL_FLAG_0);
		dyBonus.setType(type);
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//处理日期格式
			
		try{
			//当type:03时，获取佣金信息
			if(type.equals(BONUS_TYPE_03)){
				List <Map<String, Object>> list = new ArrayList <Map<String, Object>>();//记录所需最终结果	
				List <DyBonus> commissionRecordInfo = bonusService.findList(dyBonus);
				if (commissionRecordInfo.size() > 0) {
					for (int i = 0; i < commissionRecordInfo.size(); i++) {
							Map<String, Object> data = new HashMap<String, Object>();//记录每条记录所需信息
							data.put("type", "佣金");
							data.put("createDate", format.format(commissionRecordInfo.get(i).getCreateDate()));
							data.put("money", commissionRecordInfo.get(i).getMoney());
							DyDomainname domainname = domainnameService.get(commissionRecordInfo.get(i).getDomainnameId());
							data.put("domainname", domainname.getName());
							list.add(data);
					}
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("commissionRecordOrBonusRecordInfo", list);
					ar.getData().put("size", list.size());
					ar.getData().put("currentClientName", u.getName());
					ar.getData().put("photo", u.getPhoto());
					return ar;
				}else{
					AjaxResult ar = AjaxResult.makeWarn("没有记录");
					ar.getData().put("size", 0);
					ar.getData().put("currentClientName", u.getName());
					ar.getData().put("photo", u.getPhoto());
					return ar;
				}
			}
			//当type:01时，即分享红包，还需获取次高价红包记录
			if(type.equals(BONUS_TYPE_01)){				
				//获取分享红包记录
				List <ShareOrSecondBonus> shareOrsecondBonusRecordInfo = bonusService.findShareOrsecondBonusList(dyBonus);
				
				if (shareOrsecondBonusRecordInfo.size() > 0) {
					for (int i = 0; i < shareOrsecondBonusRecordInfo.size(); i++) {
						ShareOrSecondBonus shareOrSecondBonus = shareOrsecondBonusRecordInfo.get(i);
						if(shareOrSecondBonus.getType().equals(BONUS_TYPE_01)){
							shareOrSecondBonus.setType("分享红包");
						}
						if(shareOrSecondBonus.getType().equals(BONUS_TYPE_02)){
							shareOrSecondBonus.setType("次高价红包");
						}
					}
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("commissionRecordOrBonusRecordInfo", shareOrsecondBonusRecordInfo);
					ar.getData().put("size", shareOrsecondBonusRecordInfo.size());
					ar.getData().put("currentClientName", u.getName());
					ar.getData().put("photo", u.getPhoto());
					return ar;
				}else{
					AjaxResult ar = AjaxResult.makeWarn("没有记录");
					ar.getData().put("size", 0);
					ar.getData().put("currentClientName", u.getName());
					ar.getData().put("photo", u.getPhoto());
					return ar;
				}
			}
			AjaxResult ar = AjaxResult.makeWarn("系统错误");
			return ar;
		}catch(Exception e){
			logger.error("获取红包佣金记录出错", e);
			return AjaxResult.makeError("获取红包佣金记录出错:" + e.getMessage());
		}
	}
	/**
	 * 个人中心：根据会员ID更新会员名
	 * @param Name 会员名
	 * @return
	 */
	@RequestMapping(value = {"changeName"}, method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult changeName(Model model,String name) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				int flag = clientService.changeName(u.getId(), name);
				if (flag == 1) {
					AjaxResult ar = AjaxResult.makeSuccess("会员名更新成功");
					u.setName(name);
					ar.getData().put("newName", name);
					return ar;
				} else {
					return AjaxResult.makeWarn("会员名更新失败");
				}

			} catch (Exception e) {
				logger.error("会员名更新出错", e);
				return AjaxResult.makeError("会员名更新出错:" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：根据会员ID更新会员微信号
	 * @param id 会员ID
	 * @param wx 会员微信号
	 * @return
	 */
	@RequestMapping(value = {"changeWx"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult changeWx(Model model,String wx) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				int flag = clientService.changeWx(u.getId(), wx);
				if (flag == 1) {
					AjaxResult ar = AjaxResult.makeSuccess("微信号更新成功");
					u.setWx(wx);
					ar.getData().put("newWx", wx);
					return ar;
				} else {
					return AjaxResult.makeWarn("微信号更新有误");
				}
			} catch (Exception e) {
				logger.error("会员微信号更新出错", e);
				return AjaxResult.makeError("会员微信号更新出错:" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：根据会员ID更新会员QQ号
	 * @param id 会员ID
	 * @param qq 会员QQ号
	 * @return
	 */
	@RequestMapping(value = {"changeQQ"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult changeQQ(Model model,String qq) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				int flag = clientService.changeQQ(u.getId(), qq);
				if (flag == 1) {
					AjaxResult ar = AjaxResult.makeSuccess("会员QQ号更新成功");
					u.setQq(qq);
					ar.getData().put("newQQ", qq);
					return ar;
				} else {
					return AjaxResult.makeWarn("会员QQ号更新有误");
				}
			} catch (Exception e) {
				logger.error("会员QQ号更新出错", e);
				return AjaxResult.makeError("会员QQ号更新出错:" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心添加支付密码
	 * @param payPassword 支付密码原文
	 * @return
	 */
	@RequestMapping(value = {"addPayPassword"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult addPayPassword(Model model,String payPassword) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		u = clientService.get(u.getId());
		if (u != null) {
			try {
				//MD5加密原始密码
				String payPasswordIntoMD5 = DigestUtils.md5Hex(payPassword.toString());
				if(StringUtils.isBlank(u.getPayPassword())){
					//首次添加支付密码
					u.setPayPassword(payPasswordIntoMD5);
					clientService.save(u);
					
					AjaxResult ar = AjaxResult.makeSuccess("添加安全密码成功");
					return ar;
				}else{
					//修改支付密码
					if(u.getPayPassword().equals(payPasswordIntoMD5)){
						AjaxResult ar = AjaxResult.makeWarn("不能与原密码相同");
						return ar;
					}else {
						u.setPayPassword(payPasswordIntoMD5);
						clientService.save(u);
						
						AjaxResult ar = AjaxResult.makeSuccess("添加安全密码成功");
						return ar;
					}
				}
			} catch (Exception e) {
				logger.error("添加安全密码出错", e);
				return AjaxResult.makeError("添加支付出错，重新操作");
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心验证支付密码
	 * @param oldPassword 旧密码原文
	 * @return
	 */
	@RequestMapping(value = {"verificationOldPassword"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult verificationOldPassword(Model model,String oldPassword) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				//MD5加密原始密码
				String oldPasswordMD5 = DigestUtils.md5Hex(oldPassword.toString());
				
				if(oldPasswordMD5.equals(u.getPayPassword())){
					AjaxResult ar = AjaxResult.makeSuccess("密码验证成功");
					return ar;
				}else{
					AjaxResult ar = AjaxResult.makeWarn("密码错误");
					return ar;
				}
			} catch (Exception e) {
				logger.error("密码验证出错", e);
				return AjaxResult.makeError("密码验证出错，重新操作");
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：身份认证 （需从微信服务器上下载会员上传的身份证正反面照片，因为选择微信接口上传，图片暂存在了微信服务器，需在后台根据serverId调用微信接口下载到自己的服务器上）
	 * @param image1_serverId 会员反面身份证在微信服务器上的id
	 * @param image2_serverId 会员正面身份证在微信服务器上的id
	 * @return
	 */
	@RequestMapping(value = {"authenticationIDcard"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult authenticationIDcard(Model model, String image1_serverId, String image2_serverId,String IDcardNumber,HttpServletRequest req) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		u = clientService.get(u.getId());
		if (u != null) {
			try {
				//获取access_token
				final String accessToken = WeChat.getAccessToken();
				//从微信服务器下载用户上传的图片
				String baseDir = Global.getUserfilesBaseDir();
				if (StringUtils.isNotBlank(image2_serverId)) {
					Attachment attachment = WeChat.getMedia(accessToken, image1_serverId);
					String filePath = Global.USERFILES_BASE_URL + u.getId() + File.separatorChar + "authentication_positive_image_url." + attachment.getSuffix();
					FileUtils.createFile(baseDir + filePath);
					BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(baseDir + filePath));
					FileCopyUtils.copy(attachment.getFileStream(), outputStream);
					u.setAuthenticationPositiveImageUrl(req.getContextPath() + filePath);					
				}
				
				if (StringUtils.isNotBlank(image2_serverId)) {
					Attachment attachment = WeChat.getMedia(accessToken, image2_serverId);
					String filePath = Global.USERFILES_BASE_URL + u.getId() + File.separatorChar + "authentication_negative_image_url." + attachment.getSuffix();
					FileUtils.createFile(baseDir + filePath);
					BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(baseDir + filePath));
					FileCopyUtils.copy(attachment.getFileStream(), outputStream);
					u.setAuthenticationNegativeImageUrl(req.getContextPath() + filePath);
				}
				
				//存储到数据库
				u.setAuthenticationMark(AUTHENTICATION_MARK_2);
				u.setIDcardNumber(IDcardNumber);
				clientService.save(u);

				AjaxResult ar = AjaxResult.makeSuccess("上传成功，请等待平台验证");
				 ar.getData().put("authenticationPositiveImageUrl", u.getAuthenticationPositiveImageUrl());
				 ar.getData().put("authenticationNegativeImageUrl", u.getAuthenticationNegativeImageUrl());
				return ar;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("下载身份认证照片出错", e);
				return AjaxResult.makeError("上传失败，请重新操作");
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	/**
	 * 个人中心：根据会员ID更新会员默认支付方式
	 * @param defaultIncomeExpense 会员默认支付方式
	 * @return
	 */
	@RequestMapping(value = {"changeDefaultIncomeExpense"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult changeDefaultIncomeExpense(Model model,String defaultIncomeExpense) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				int flag = clientService.changeDefault_income_expense(
						u.getId(), defaultIncomeExpense);
				if (flag == 1) {
					AjaxResult ar = AjaxResult.makeSuccess("银行信息更新成功");
					u.setDefaultIncomeExpense(defaultIncomeExpense);
					ar.getData().put("newDefaultIncomeExpense",
							defaultIncomeExpense);
					return ar;
				} else {
					return AjaxResult.makeWarn("银行信息更新失败");
				}
			} catch (Exception e) {
				logger.error("会员银行信息更新出错", e);
				return AjaxResult.makeError("会员银行信息更新出错:" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：获取用户提现进程信息
	 * @return
	 */
	@RequestMapping(value = {"getWithdrawalsInfo"},method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult getWithdrawalsInfo(Model model){
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			//获取用户处于等待和处理中的提现信息
			List<DyCashFlow> withdrawalsInfo = cashFlowService.findWithdrawalsList(u.getId(),CASHFLOW_OPERATE_WITHDRAW,CASHFLOW_CONFIRM_WAIT,CASHFLOW_COMFIRM_DOING);
			if(!withdrawalsInfo.isEmpty()){
				AjaxResult ar = AjaxResult.makeSuccess("");
				ar.getData().put("withdrawalsInfo", withdrawalsInfo);
				ar.getData().put("size", withdrawalsInfo.size());
				return ar;
			}else{
				AjaxResult ar = AjaxResult.makeWarn("没有提现记录");
				ar.getData().put("size", 0);
				return ar;
			}
		}else{
			return AjaxResult.makeError("非法访问！");
			
		}
	}
	
	
	/**
	 * 取消提现
	 * @param model
	 * @param id 提现的资金流id
	 * @return
	 */
	@RequestMapping(value = {"cancelWithdrawal"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult cancelWithdrawal(Model model,String id,String money)throws Exception{
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			//根据用户id和提现的资金流id，取消用户的提现申请
			cashFlowService.cancel(u.getId(),id,money);
			AjaxResult ar = AjaxResult.makeSuccess("取消提现成功");
			return ar;
		}else{
			return AjaxResult.makeError("非法访问！");
			
		}
	}
	
	/**
	 * 个人中心：财务管理提现充值
	 * @param operate 操作类型
	 * @param operateAmount操作金额
	 * @return
	 */
	@RequestMapping(value = {"rechargeOrWithdrawals"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult rechargeOrWithdrawals(Model model,String operate,String operateAmount,String from)throws Exception {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			if(from != null && from.equals("rechargeForBid")){
				//出价金额不足的充值不需要检测个人信息
			}else{
				//验证用户是否完善了个人信息的填写
				if(!clientService.checkPersonalInfo(u)){
					return AjaxResult.makeError("请先填写完整的个人信息");
				}
			}
			
			Long money = Long.parseLong(operateAmount);
			// 如果是提现操作，需要将提现金额冻结
			if (operate.equals("提现")) {
				try {
					financeService.updateFreezeBalanceForRecharge(u, money,CASHFLOW_OPERATE_WITHDRAW);
				} catch (Exception e) {
					throw e;
				}
			} else {// 充值（线下操作）
				try {
					// 构建资金流表记录，插入到表中
					DyCashFlow cashFlow = new DyCashFlow();
					cashFlow.setClientId(u.getId());
					cashFlow.setOperate(CASHFLOW_OPERATE_RECHARGE_OUTLINE);
					cashFlow.setOperateAmount(money);
					cashFlow.setOperateTime(new Date());
					cashFlow.setConfirmResult(CASHFLOW_CONFIRM_WAIT);
					// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
					cashFlow.preInsert(UserUtils.get(u.getBrokerId()));
					// 设置该实体为新记录
					cashFlow.setIsNewRecord(true);
					cashFlowService.save(cashFlow);
				} catch (Exception e) {
					logger.error(operate + "出错", e);
					return AjaxResult.makeError(operate + "出错，请联系经纪人");
				}
			}
			AjaxResult ar = AjaxResult.makeSuccess(operate + "申请成功,等待确认");
			return ar;
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：财务管理充值（微信充值） 待检！！！！！
	 * @param operate 操作类型
	 * @param operateAmount操作金额
	 * @return
	 */
	@RequestMapping(value = {"wxPay"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult wxPay(Model model, String operate, String operateAmount,String payFrom,HttpServletRequest req)throws Exception {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			
			if("isell".equals(payFrom) || "ibuy".equals(payFrom)){
				//我要卖页面的支付不需要验证信息
			}else{
				//验证用户是否完善了个人信息的填写
				if(!clientService.checkPersonalInfo(u)){
					return AjaxResult.makeError("请先填写完整的个人信息");
				}
			}
			String key = ConfKit.get("partnerKey");//待检？
			//统一下单参数实体
			String appid = ConfKit.get("AppId");
			String mch_id = ConfKit.get("mch_id");
			String nonce_str = IdGen.uuid().substring(0, 30);
			String sign = "";//WeChat.Order（）方法内会生成
			String body = u.getNickname()+":微信充值";
			String out_trade_no = WeChat.tradeNumber("pay");
			Long total_fee = Long.parseLong(operateAmount)*100;//微信支付的单位是分
			String spbill_create_ip = req.getRemoteAddr();//java.net.InetAddress.getLocalHost().getHostAddress();
			String notify_url = req.getScheme() + "://" + req.getServerName()+req.getContextPath()+"/domainname/wxPaySuccess.html";
			logger.debug("notify_url:"+notify_url);
			String trade_type ="JSAPI" ;
			String openid = u.getOpenid();
			UnifiedOrder unifiedOrderParameters = new UnifiedOrder(appid, mch_id, nonce_str, sign, body, out_trade_no, total_fee, spbill_create_ip, notify_url, trade_type, openid);// 参数值待检
			
			// 统一下单，返回的信息
			WxReturnUnifiedOrder wxReturnUnifiedOrder = WeChat.Order(unifiedOrderParameters, key);
			
			if (wxReturnUnifiedOrder.getReturn_code() != null && wxReturnUnifiedOrder.getReturn_code().equals("SUCCESS") && wxReturnUnifiedOrder.getResult_code() != null && wxReturnUnifiedOrder.getResult_code().equals("SUCCESS")) {
				// 将订单信息写入数据库,构建微信支付结果表记录，插入到表中
				DyWxpayResult wxpayResult = new DyWxpayResult();
				wxpayResult.setOpenid(unifiedOrderParameters.getOpenid());
				wxpayResult.setSign(wxReturnUnifiedOrder.getSign());
				wxpayResult.setOutTradeNo(unifiedOrderParameters.getOut_trade_no());
				wxpayResult.setTotalFee(unifiedOrderParameters.getTotal_fee()/100);
				wxpayResult.setStatus("0");
				// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
				wxpayResult.preInsert(UserUtils.get(u.getBrokerId()));
				// 设置该实体为新记录
				wxpayResult.setIsNewRecord(true);
				wxpayResultService.save(wxpayResult);
				
				//前台接口调用所需数据
				String appId = ConfKit.get("AppId");
				String timeStamp = Long.toString(new Date().getTime());// 时间戳
				String nonceStr = IdGen.uuid().substring(0, 30);// 随机字符串
				String packages = "prepay_id="+wxReturnUnifiedOrder.getPrepay_id();
				
				// 获取支付签名
				String paySign = Pay.paySign(appId, timeStamp, nonceStr,packages, key);

				AjaxResult ar = AjaxResult.makeSuccess("");
				ar.getData().put("timestamp", timeStamp);
				ar.getData().put("nonceStr", nonceStr);
				ar.getData().put("package", packages);
				ar.getData().put("signType", "MD5");
				ar.getData().put("paySign", paySign);
				return ar;
			} else {
				Exception e = new Exception(u.getNickname()+":充值下订单失败");
				logger.debug(e.getMessage());
				throw e;
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}

	/**
	 * 个人中心：财务管理充值（微信充值） ，微信发送通知请求的响应。待检！！！！！
	 * @return
	 */
	@RequestMapping(value = {"wxPaySuccess"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public void wxPaySuccess(Model model,HttpServletRequest req,HttpServletResponse res)throws Exception {
		logger.debug("成功进入支付处理程序");
		PrintWriter out = res.getWriter();
		try {
			//获取支付结果，并进行处理
			PayResult payResult = WeChat.payResult(req);
			if(payResult.getReturn_code() != null && payResult.getReturn_code().equals("SUCCESS")){
				//获取平台订单信息
				DyWxpayResult wxpayResult = new DyWxpayResult();
				wxpayResult.setOpenid(payResult.getOpenid());
				wxpayResult.setOutTradeNo(payResult.getOut_trade_no());
				List<DyWxpayResult> list = wxpayResultService.findList(wxpayResult);
				
				if (!list.isEmpty() && list.get(0).getStatus().equals("0")) {
					wxpayResult = list.get(0);
					//更新微信支付结果表的实体
					wxpayResult.setStatus("1");
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					wxpayResult.setTimeEnd(df.parse(payResult.getTime_end()));
					wxpayResult.setTransactionId(payResult.getTransaction_id());
					wxpayResult.setResultCode(payResult.getResult_code());
					
					//更新微信支付结果表，并向资金流表插入数据，并跟新财务表
					wxpayResultService.dealWithPayResult(wxpayResult);
					
					//回复微信请求
					String reply = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
					out.print(reply);
					out.close();
				} else {
					String reply = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[POST_DATA_EMPTY]]></return_msg></xml>";
					out.print(reply);
					out.close();
				}
			}else{
				String reply = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[POST_DATA_EMPTY]]></return_msg></xml>";
				out.print(reply);
				out.close();
			}
		} catch (Exception e) {
			String reply = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[POST_DATA_EMPTY]]></return_msg></xml>";
			out.print(reply);
			out.close();
			throw e;
		}
	}
	
	/**
	 * 个人中心：我的交易-关注、取消关注
	 * @param attentionFlag 关注、取消关注标记，0取消，1关注
	 * @param domainnameId域名ID
	 * @return
	 */
	@RequestMapping(value = {"addOrCancelAttention"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult addOrCancelAttention(Model model,String domainnameId,String attentionFlag) {
		// 获取登录用户信息
		DyClient currClient = DySysUtils.getCurrentDyClient();
		if (currClient != null) {
			try {
				DyAttention entity = new DyAttention();
				entity.setClientId(currClient.getId());
				entity.setDomainnameId(domainnameId);
				entity.setDelFlag(DyAttention.DEL_FLAG_NORMAL);
				if (attentionFlag.equals("1")) {
					entity.preInsert(UserUtils.get(currClient.getBrokerId()));
					entity.setIsNewRecord(true);
					attentionService.save(entity);
					AjaxResult ar = AjaxResult.makeSuccess("关注成功！");
					return ar;
				} else {
					attentionService.delete(entity);
					AjaxResult ar = AjaxResult.makeSuccess("取消关注成功！");
					return ar;
				}
			} catch (Exception e) {
				logger.error("个人中心：我的交易关注、取消关注中出错", e);
				return AjaxResult.makeError("操作失败，重新操作" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：我的交易-付款
	 * @param payMoney 付款金额
	 * @param domainnameId域名ID
	 * @return
	 */
	@RequestMapping(value = {"payment"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult payment(Model model,String payMoney,String domainname,String domainnameId) throws Exception{
		// 获取登录用户信息
		DyClient currClient = DySysUtils.getCurrentDyClient();
		if (currClient != null) {
			// 扣款并记录在资金流表中
			try {
				// 买家付款
				Date waitTime = financeService.pay(currClient, payMoney,domainnameId);// 此方法涉及同步问题，待检!!!!!!!!!!!!!
				AjaxResult ar = AjaxResult.makeSuccess("付款成功");
				ar.getData().put("waitTime", waitTime);
				return ar;
			} catch (Exception e) {
				logger.error("个人中心：我的交易-付款出错", e);
				throw e;
				//return AjaxResult.makeError("付款失败，重新操作或联系经纪人" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	
	/**
	 * 个人中心：我的交易-转移域名
	 * @param domainnameId域名ID
	 * @param bidAmount成交价
	 * @return
	 */
	@RequestMapping(value = {"transferDomainname"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult transferDomainname(Model model,String domainnameId,String bidAmount,String domainname) {
		// 获取登录用户信息
		DyClient currClient = DySysUtils.getCurrentDyClient();
		if (currClient != null) {
			try {
				Date waitTime = domainnameService.transferDomainname(currClient, domainnameId);
				AjaxResult ar = AjaxResult.makeSuccess("转移域名成功");
				ar.getData().put("waitTime", waitTime);
				return ar;
			} catch (Exception e) {
				logger.error("个人中心：我的交易-转移域名出错", e);
				return AjaxResult.makeError("转移域名失败，请重新操作或联系经纪人"
						+ e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	
	/**
	 * 个人中心：我的交易-收到域名
	 * @param domainnameId域名ID
	 * @return
	 */
	@RequestMapping(value = {"receiveDomamainname"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult receiveDomamainname(Model model,String domainnameId,String domainname,String bidAmount) {
		// 获取登录用户信息
		DyClient currClient = DySysUtils.getCurrentDyClient();
		if (currClient != null) {
			try {
				//设置域名状态，并向卖家发送消息，提示买家已收到域名
				Date waitTime = domainnameService.receiveDomamainname(domainnameId, bidAmount);
				AjaxResult ar = AjaxResult.makeSuccess("买卖成功,等待系统确认");
				ar.getData().put("waitTime", waitTime);
				return ar;
			} catch (Exception e) {
				logger.error("个人中心：我的交易-确认收到域名出错", e);
				return AjaxResult.makeError("确认收到域名失败，请重新操作或联系经纪人"
						+ e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}

	/**
	 * 分享：获取jsticket
	 * @return
	 */
	@RequestMapping(value = { "jsapiTicket" }, method = { RequestMethod.GET })
	@ResponseBody
	/* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult jsapiTicket(String href) {
		try {
			String appId = ConfKit.get("AppId");
			// 随机生成30位数字字符串
			String nonceStr = IdGen.uuid().substring(0, 30);// 随机字符串
			String timestamp = Long.toString(new Date().getTime());// 时间戳

			// 获取请求页面的url,#及之后的不需要
			String url = null;
			if (href.indexOf("#") != -1) {
				url = href.substring(0, href.indexOf("#"));
			} else {
				url = href;
			}
			logger.debug("jsapi ticket href:" + href);
			logger.debug("jsapi ticket url:" + url);
			String jsapiTicket = WeChat.getJsapiTicket();
			String signature = WeChat.signature(nonceStr, timestamp, url,jsapiTicket);

			AjaxResult ar = AjaxResult.makeSuccess("获取签名成功！");
			ar.getData().put("debug", false);
			ar.getData().put("appId", appId);
			ar.getData().put("nonceStr", nonceStr);
			ar.getData().put("timestamp", timestamp);
			ar.getData().put("signature", signature);
			// 获取登录用户信息
			DyClient currClient = DySysUtils.getCurrentDyClient();
			if (currClient != null) {
				ar.getData().put("shareClientId", currClient.getId());
			}
			return ar;
		} catch (Exception e) {
			logger.error("微信JS API：获取签名出错", e);
			return AjaxResult.makeError("获取签名出错" + e.getMessage());
		}
	}
}
