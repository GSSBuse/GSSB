package com.thinkgem.jeesite.modules.wx.web.domainname;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.nutz.mapl.Mapl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.wx.bean.UserInfo;
import com.thinkgem.jeesite.common.wx.oauth.Oauth;
import com.thinkgem.jeesite.common.wx.oauth.User;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * @author wufl.fnst
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/domainname")
public class AuthController {
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DyClientService dyClientService;
	
	@Autowired
	private DyFinanceService dyFinanceService;
	
	
	/**
	 * 授权登录失败页面响应
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"admin_fail"}, method={RequestMethod.GET})
	public String adminFail(Model model) {
		return "modules/wx/domainname/admin_fail";
	}
	
	/**
	 * 授权登录成功页面响应
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"admin_success"}, method={RequestMethod.GET})
	public String adminSuccess(Model model) {
		return "modules/wx/domainname/admin_success";
	}
	
	/**
	 * 授权登录处理
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = {"qrAuth"}, method={RequestMethod.GET})
	public String  qrAuth(Model model,HttpServletRequest request,HttpServletResponse response) {
		String sess1 = request.getParameter("sess");
		UserUtils.setSessionInfo(sess1, "aa", "cc");
		
		HttpSession session = request.getSession();// 返回与当前request相关联的session，如果没有则在服务器端创建一个;
		String openid = (String) session.getAttribute("weixin_openid");
		try {
			if (openid == null || "".equals(openid)) {
				//未登录
				Oauth oauth = new Oauth(ConfKit.get("AppId"), ConfKit.get("AppSecret"));
				// 用户同意授权后，能获取到code
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				if (code == null) {
					if(state == null){
						//扫码请求，重定向到授权登录
						response.sendRedirect(oauth.getCodeUserInfo(URLEncoder.encode(request.getRequestURL() + "?" + request.getQueryString(), "UTF8")));
						return null;
					}else{
						//用户拒绝授权登录，重定向到登录失败页面
						return "modules/wx/domainname/admin_fail";
					}
				} else {
					// 用户已经同意授权登录
					if (!"authdeny".equals(code)) {
						// 获取网页授权access_token
						String responseDate = oauth.getToken(code);
						logger.debug("=======网页扫描登录=======");
						logger.debug(responseDate);
						Object jsonObject = Json.fromJson(responseDate);
						openid = (String)Mapl.cell(jsonObject, "openid");
						String acToken = (String)Mapl.cell(jsonObject, "access_token");
						try {
							DyClient dc = dyClientService.getByOpenid(openid);
							boolean isNew = dc == null;
							if (isNew) {
								UserInfo userInfo = User.getUserInfoSns(acToken, openid);
								dc = new DyClient();
								dc.setEmailFlag(Constant.EMAIL_FLAG_2);
								com.thinkgem.jeesite.modules.sys.entity.User broker = null;
								// 自动分配经纪人
								broker = dyClientService.getRandomBroker();
								dc.setBrokerId(broker.getId());
								dc.setVip("0");
								dc.setSealFlag(Constant.SWITCH_OFF);
								dc.preInsert(broker);
								dc.setIsNewRecord(true);
								dc.setAvoidDeposit(Constant.SWITCH_OFF);
						
								dc.setNickname(userInfo.getNickname());
								dc.setOpenid(openid);
								String headImg = userInfo.getHeadimgurl();
								if (headImg != null && headImg.lastIndexOf('/') < headImg.length()-1) {
									headImg = headImg.substring(0, headImg.lastIndexOf('/')+1);
								}
								dc.setPhoto(headImg);
								
								dyClientService.save(dc);
								
								
								DyFinance finance = new DyFinance();
								finance.setClientId(dc.getId());
								finance.setAccountBalance(0L);
								finance.setFreezeBalance(0L);
								finance.preInsert(broker);
								finance.setIsNewRecord(true);
								
								dyFinanceService.save(finance);
							}
						} catch (Exception e) {
							logger.debug("=======获取创建用户信息失败===============");
							logger.debug(e.getMessage());
						}
						session.setAttribute("weixin_openid", openid);
						String sess = request.getParameter("sess");
						if (!UserUtils.setSessionInfo(sess, "openId", openid)) {
							logger.error("SessionId错误。无法自动登录");
						}
						// 跳转到成功登录页面
						return "modules/wx/domainname/admin_success";
					}
				}
			} else {
				String sess = request.getParameter("sess");
				if (!UserUtils.setSessionInfo(sess, "openId", openid)) {
					logger.error("SessionId错误。无法自动登录");
				}
				// 跳转到已经登录页面
				return "modules/wx/domainname/admin_success";
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return "modules/wx/domainname/admin_fail";
	}
	
	public static void main(String[] args) {
		String headImg = "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46";
		headImg = headImg.substring(0, headImg.lastIndexOf('/')+1);
		System.out.println(headImg);
    }
}
