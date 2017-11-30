package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.paimai.bean.GbjBuyEntity;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.front.service.gbj.FrontGbjBuyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBuyService;
import com.thinkgem.jeesite.modules.wx.entity.domainname.PageDomainEntity;

@Controller
@RequestMapping(value = "${adminPath}/paimai/front/index")
public class FrontGbjBuyController extends BaseController{

	@Autowired
	private FrontGbjBuyService frontGbjBuyService;
	
	@ModelAttribute
	public GbjBuyEntity get(@RequestParam(required=false) String id) {
		GbjBuyEntity gbjBuyEntity = null;
		if (StringUtils.isNotBlank(id)){
			gbjBuyEntity = frontGbjBuyService.get(id);
		}
		if (gbjBuyEntity == null){
			gbjBuyEntity = new GbjBuyEntity();
		}
		return gbjBuyEntity;
	}
	
	
	//@RequiresPermissions("paimai:front:index:view")
	@RequestMapping(value = "list")
	@Transactional(readOnly = false)
	public String list(GbjBuyEntity gbjBuyEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjBuyEntity> page = frontGbjBuyService.findPage(new Page<GbjBuyEntity>(request, response), gbjBuyEntity); 
		model.addAttribute("page", page);
	
		return "modules/paimai/front/index";
	}
	
	//@RequiresPermissions("paimai:front:index:view")
	/*@RequestMapping(value = "form")
	public String form(GbjBuyEntity gbjBuyEntity, Model model) {
		model.addAttribute("gbjBuyEntity", gbjBuyEntity);
		return "modules/paimai/front/index";
	}
	*/
}
