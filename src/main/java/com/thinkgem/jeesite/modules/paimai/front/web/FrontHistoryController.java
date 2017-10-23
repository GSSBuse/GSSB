package com.thinkgem.jeesite.modules.paimai.front.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value= {"history"})
public class FrontHistoryController {

	@RequestMapping(value= {""})
	public String ibuyDetal(Model model) {
		return "modules/paimai/front/history";
	}
	
}
