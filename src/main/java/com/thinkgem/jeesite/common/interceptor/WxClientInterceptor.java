package com.thinkgem.jeesite.common.interceptor;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.nutz.mapl.Mapl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.UserAgentUtils;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.bean.UserInfo;
import com.thinkgem.jeesite.common.wx.oauth.Oauth;
import com.thinkgem.jeesite.common.wx.oauth.User;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 微信访问拦截器
 * 已关注用户访问时，更新用户信息。
 * 未关注用户访问时，限制其只能看我要买页面，交易历史页面，个人红包记录页面，个人佣金记录页面。
 * The Class WxClientInterceptor.<br>
 * 
 * @author FNST)WeiCL
 * @version 1.0
 */
public class WxClientInterceptor implements HandlerInterceptor, Constant{
	
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DyClientService dyClientService;
	
	@Autowired
	private DyFinanceService dyFinanceService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 资源类访问跳过 Ajax请求跳过
		if (!(handler instanceof ResourceHttpRequestHandler) && !WeChat.isAjax(request)) {

			UserAgent ua = UserAgentUtils.getUserAgent(request);
			request.setAttribute("isApple",ua.getOperatingSystem().getName().contains("iPhone"));
			logger.debug(ua + ":" + ua.getOperatingSystem());
			
			if (ignore(request, response)) {
				return true;
			}
			// 判断当前用户是否登录
			if (DySysUtils.getCurrentDyClient() == null) {
				// 微信访问时更新用户信息并缓存
				if (WeChat.isWeiXin(request)) {
					// 获取用户信息，并更新到数据库
					String openid = (String)request.getSession().getAttribute("weixin_openid");
					
					if (StringUtils.isEmpty(openid)) {
						// 公众号外访问的时候，静默授权获取OpenId
						Oauth oauth = new Oauth(ConfKit.get("AppId"), ConfKit.get("AppSecret"));
	
						// 用户同意授权后，能获取到code
						String code = request.getParameter("code");
	
						if (code == null) {
							// 静默授权
							response.sendRedirect(oauth.getCode(URLEncoder.encode(request.getRequestURL() + "?" + request.getQueryString(), "UTF8")));
							return false;
						} else {
							// 用户同意授权
							if (!"authdeny".equals(code)) {
								try {
									String responseDate = oauth.getToken(code);
									Object jsonObject = Json.fromJson(responseDate);
									openid = (String)Mapl.cell(jsonObject, "openid");
								} catch (Exception e) {
									logger.debug("=======获取用户OpenId失败=======" + e.getMessage());
								}
							}
						}
					}
					// 用户已关注公众号,且未被封号，更新用户信息并缓存
					DyClient dc = dyClientService.getByOpenid(openid);
					if (dc != null && !"1".equals(dc.getSealFlag())) {
						try {
							UserInfo userInfo = User.getUserInfo(WeChat.getAccessToken(), openid);
							if (StringUtils.isNotEmpty(userInfo.getNickname())) {
								dc.setNickname(userInfo.getNickname());
							}
							if (StringUtils.isNotEmpty(userInfo.getHeadimgurl())) {
								String headImg = userInfo.getHeadimgurl();
								if (headImg != null && headImg.lastIndexOf('/') < headImg.length()-1) {
									headImg = headImg.substring(0, headImg.lastIndexOf('/')+1);
								}
								dc.setPhoto(headImg);
							}
						
							dyClientService.save(dc);
							UserUtils.getSession().setAttribute(Constant.SESSION_KEY_CURRENT_CLIENT, dc);
						} catch (Exception e) {
							logger.debug("=======同步用户信息失败=======" + e.getMessage());
						}
						
						return true;
					}
					if (dc == null) {
						com.thinkgem.jeesite.modules.sys.entity.User broker = null;
						dc = new DyClient();
						dc.setEmailFlag(Constant.EMAIL_FLAG_2);
						// 自动分配经纪人
						broker = dyClientService.getRandomBroker();
						dc.setBrokerId(broker.getId());
						dc.setVip("0");
						dc.setSealFlag("0");
						dc.preInsert(broker);
						dc.setIsNewRecord(true);
						
						UserInfo userInfo = User.getUserInfo(WeChat.getAccessToken(), openid);
						dc.setNickname(userInfo.getNickname());
						dc.setOpenid(openid);
						String headImg = userInfo.getHeadimgurl();
						if (headImg != null && headImg.lastIndexOf('/') < headImg.length()-1) {
							headImg = headImg.substring(0, headImg.lastIndexOf('/')+1);
						}
						dc.setPhoto(headImg);
						dc.setAvoidDeposit(SWITCH_OFF);
						
						dyClientService.save(dc);
						
						DyFinance finance = new DyFinance();
						finance.setClientId(dc.getId());
						finance.setAccountBalance(0L);
						finance.setFreezeBalance(0L);
						finance.preInsert(broker);
						finance.setIsNewRecord(true);
						
						dyFinanceService.save(finance);
						
						UserUtils.getSession().setAttribute("current_dy_client", dc);
					}
				}
				return judgeCanBeAccessed(request, response);
			}
			return true;
		}
		return true;
	}
	
	private boolean judgeCanBeAccessed(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String project = request.getContextPath();
		if (!uri.startsWith(project + "/domainname/") ||
				uri.startsWith(project + "/domainname/ibuy") ||
				uri.startsWith(project + "/domainname/auction-list") ||
				uri.startsWith(project + "/domainname/bidding-list") ||
				uri.startsWith(project + "/domainname/singleDomainname") ||
				uri.startsWith(project + "/domainname/getHistory") ||
				uri.startsWith(project + "/domainname/viewArticle") || 
				uri.startsWith(project + "/domainname/activateEmail") ||
				uri.startsWith(project + "/domainname/bonusRecord") ||
				uri.startsWith(project + "/domainname/commissionRecord") ||
				uri.startsWith(project + "/domainname/wxPaySuccess") ||
				uri.startsWith(project + "/domainname/icenter") ||
				uri.startsWith(project + "/domainname/error")) {
			return true;
		}
		try {
			response.sendRedirect(request.getContextPath() + "/domainname/error.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
	    // TODO Auto-generated method stub
	    
    }
	
	private boolean ignore(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String project = request.getContextPath();
		if (uri.startsWith(project + "/domainname/qrAuth")) {
			return true;
		}
		return false;
	}
}