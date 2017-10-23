package com.thinkgem.jeesite.modules.paimai.front.web;


import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Objects;
import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.ZxingHandler;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${frontPath}/common")
public class FrontCommonController implements Constant{
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DyClientService dyClientService;             // 会员信息管理Service
	
	/**
	 * 登陆验证
	 */
	@ResponseBody
	@RequestMapping(value ={"loginCheck"},method = RequestMethod.POST)
	public String longinCheck(Model model, @RequestParam("name") String name,@RequestParam("password") String password) {
		try{ 
			DyClient user = dyClientService.getByDyid(name);
			
			//用户不存在
			if(user == null){
				return "err";
			}
			//密码正确
			else {
				String payPasswordIntoMD5 = DigestUtils.md5Hex(password);
				if (Objects.equal(user.getPayPassword(), payPasswordIntoMD5)) {
			
					User broker = UserUtils.get(user.getBrokerId());
					user.setBroker(broker);
					
					// 存session
					UserUtils.getSession().setAttribute("current_dy_client", user);
					return "ok";
				} else {
					return "err";
				}
				
			}
		} catch (Exception e) {
			return "err";
		}
	}
	
	/**
	 * 注销
	 */
	@ResponseBody
	@RequestMapping(value ={"logout"},method = RequestMethod.POST)
	public String logout(Model model) {
		try{
			UserUtils.getSession().getAttribute("current_dy_client");
			UserUtils.getSession().removeAttribute("current_dy_client");
			UserUtils.getSession().getAttribute("openId");
			UserUtils.getSession().removeAttribute("openId");
			return "ok";
		} catch (Exception e) {
			return "err";
		}
	}
	
	
	@RequestMapping(value ={"loginQrImage"},method = RequestMethod.GET)
	public void loginQrImage(Model model, HttpServletRequest req, HttpServletResponse resp) {
		try{
//			Serializable sessionId = UserUtils.getSessionId(req, resp);
			Serializable sessionId = UserUtils.getSession().getId();
			
			resp.setContentType("image/png");
			
			String hostUrl = req.getScheme() + "://" + req.getServerName() + (req.getServerPort() == 80? "":":"+req.getServerPort()) + req.getContextPath();
			String uri = ConfKit.get("wechat_login_uri");
			uri += "?sess="+ sessionId;
			logger.debug("PC端登录二维码地址：" + hostUrl + uri);
			ZxingHandler.encode2Stream(hostUrl + uri, 132, 132, resp.getOutputStream());
			
			Object v = UserUtils.getSession().getAttribute("aa");
			System.out.println("session aa:" + v);
			
//			UserUtils.setSessionInfo((String)sessionId, "aa", "qq");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value ={"qrloginCheck"},method = RequestMethod.POST)
	public AjaxResult qrloginCheck(Model model) {
		try{
			String openid = (String)UserUtils.getSession().getAttribute("openId");
			if (openid == null) {
				return AjaxResult.makeError("请确认扫描并授权。");
			}
			DyClient client = dyClientService.getByOpenid(openid);
			
			if (client == null) {
				return AjaxResult.makeError("请先关注公众号扫描并授权。");
			}
			
			UserUtils.getSession().setAttribute(SESSION_KEY_CURRENT_CLIENT, client);
			return AjaxResult.makeSuccess("");
		} catch (Exception e) {
			return AjaxResult.makeError("请确认扫描并授权。");
		}
	}
}
