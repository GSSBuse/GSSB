package com.thinkgem.jeesite.modules.paimai.front.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;


@Controller
@RequestMapping(value= {"securitysetting"})
public class FrontSecurityController {
	@Autowired
	private DyClientService clientService;
	
	@RequestMapping(value= {""})
	public String ibuyDetal(Model model) {
		DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
		UserUtils.getSession().setAttribute("current_dy_client", user);
		return "modules/paimai/front/securitysetting";
	}
	/**
	 * 验证原密码是否正确
	 */
	@RequestMapping(value="checkOldPassword")
	@ResponseBody
	public String checkOldPassword(HttpServletRequest request){
		String oldPassword = request.getParameter("payPassword");
		oldPassword = DigestUtils.md5Hex(oldPassword);
		DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
		if(!StringUtils.equals(oldPassword, user.getPayPassword())){
			return "error";
		}
		return "success";
	}
	/**
	 * 修改支付密码
	 */
	@RequestMapping(value="updatePayPassword")
	public String updatePayPassword(HttpServletRequest request){
		String newPassword = request.getParameter("payPassword");
		newPassword = DigestUtils.md5Hex(newPassword);
		DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
		if(StringUtils.isBlank(newPassword)){
			return "error";
		}
		user.setPayPassword(newPassword);
		try{
			clientService.save(user);
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
}

