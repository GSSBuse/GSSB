package com.thinkgem.jeesite.modules.wx.web.domainname;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.exception.ProduceException;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.modules.sys.entity.dy.DomainEndNumEntity;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.utils.ShowDomainCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 我要卖 页面控制器
 * @author songshuqing
 * @since 2015/10/12
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/domainname")
public class IsellController {
	
	Logger logger = Logger.getLogger(getClass());
	// 域名信息管理Service
	@Autowired
	private DyDomainnameService dyDomainnameService;

	// 财务管理Service
	@Autowired
	private DyFinanceService dyFinanceService;
	
	/*
	 * ==================================
	 *   页面显示controller
	 * ==================================
	 */
	
	/**
	 * 拍卖中的域名列表页面
	 * @param model 页面模型
	 * @return 我要卖页面视图
	 */
	@RequestMapping(value = {"isell"})
	public String isell(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			model.addAttribute("sellerDeposit", DySysUtils.SELL_DEPOSIT);
			return "modules/wx/domainname/isell";
		}
	}
	
	/**
	 * 追加红包跳转页面
	 * @return 追加红包页面视图
	 */
	@RequestMapping(value = {"addRedPack"})
	public String addRedPack(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			model.addAttribute("shareBonusEnable", DySysUtils.SHARE_BONUS_ENABLE);
			return "modules/wx/domainname/addRedPack";
		}
	}
	
	/**
	 * 出售域名页面
	 * @param model 页面模型
	 * @param domainnameId 域名ID
	 * @return 出售域名页面视图
	 */
	@RequestMapping(value = {"isell-setting"})
	public String isell_setting(Model model,String domainnameId) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			if(domainnameId == null){
				List<DomainEndNumEntity> list = dyDomainnameService.countLately7DayDomianNum();
				model.addAttribute("Lately7DayDomianNumList", JsonMapper.toJsonString(list));
			}else{
				model.addAttribute("Lately7DayDomianNumList", "[]");
			}
			model.addAttribute("sellerDeposit", DySysUtils.SELL_DEPOSIT);
			model.addAttribute("shareBonusEnable", DySysUtils.SHARE_BONUS_ENABLE);
			return "modules/wx/domainname/isell-setting";
		}
	}
	
	/*
	 * ==================================
	 *   数据处理controller
	 * ==================================
	 */
	
	/**
	 * 获取当前会员所有的拍卖中的域名信息
	 * @param model 页面模型
	 * @return 我要卖页面视图
	 */
	@RequestMapping(value = {"isell/domainList"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult domainList(Model model) {
		List<DyDomainname> list = null;
		try {
			// 获取登录用户信息
			DyClient u = DySysUtils.getCurrentDyClient();
			// 根据会员ID取得所有的拍卖中的列表, 抽取其中状态未结束的拍卖项目
			list = dyDomainnameService.findListByClientId(u.getId(), "0%");
		} catch (Exception e) {
			logger.error("", e);
			AjaxResult ar = AjaxResult.makeError("获取域名列表失败");
			return ar;
		}
		
		AjaxResult ar = AjaxResult.makeSuccess("");
		ar.getData().put("domainList", list);
		return ar;
	}
	
	/**
	 * 获取当前域名信息
	 * @param model 页面模型
	 * @return 我要卖页面视图
	 */
	@RequestMapping(value = {"isell/domainInfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult domainInfo(Model model, String domainId) {
		if (StringUtils.isNotBlank(domainId) && !domainId.equalsIgnoreCase("undefined")) {
			try {
				DyDomainname domainname = dyDomainnameService.get(domainId);
				if (domainname != null) {
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("domainInfo", domainname);
					return ar;
				}
			} catch (Exception e) {
				logger.error("", e);
				AjaxResult ar = AjaxResult.makeError("获取域名信息失败");
				return ar;
			}
		}
		
		AjaxResult ar = AjaxResult.makeSuccess("");
		ar.getData().put("domainInfo", null);
		return ar;
	}
	
	/**
	 * 提交追加红包信息
	 * @param model 页面模型
	 * @return 
	 */
	@RequestMapping(value = {"isell/addRedPackInfo"}, method = RequestMethod.POST)
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult addRedPackInfo(Model model,String domainId,String bonusShareTotal, String bonusShareNumber, String bonusSecond ,String payPassword)throws Exception{
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		try {
			if (domainId != null && !domainId.equals("")) {
				//MD5加密原始密码
				String payPasswordMD5 = DigestUtils.md5Hex(payPassword.toString());
				if(!payPasswordMD5.equals(u.getPayPassword())){
					AjaxResult ar = AjaxResult.makeWarn("安全密码错误");
					return ar;
				}
				DyDomainname dyDomainname = dyDomainnameService.get(domainId);
				if (dyDomainname != null && StringUtils.equals(dyDomainname.getStatus(), Constant.DOMAIN_STATUS_03)) {
					// 审核通过之后只能加红包
					Long total = stringToLong(bonusShareTotal);
					Long number = stringToLong(bonusShareNumber);
					Long second = stringToLong(bonusSecond);
					if(total.longValue() > 0 || number.longValue() > 0){
						if(total.longValue() > number.longValue()*200 || total.longValue() < number.longValue()){
							AjaxResult ar = AjaxResult.makeError("追加红包失败");
							return ar;
						}
					}
					dyDomainnameService.updateBonusSetting(domainId, total, number, second);
					AjaxResult ar = AjaxResult.makeSuccess("追加红包成功");
					ShowDomainCacheUtil.clearCache();
					return ar;
				}else{
					AjaxResult ar = AjaxResult.makeError("无此域名信息");
					return ar;
				}
			}else{
				AjaxResult ar = AjaxResult.makeError("参数错误");
				return ar;
			}
		} catch (ProduceException e) {
			logger.error("", e);
			return AjaxResult.makeSuccess("追加红包成功");
		} catch (Exception e) {
			logger.error("", e);
			return AjaxResult.makeError(e.getMessage());
		}
	}
	
	/**
	 * isell域名列表的提交：金额足够了
	 * @param model 页面模型
	 */
	@RequestMapping(value = {"isell/isellSubmitDomain"}, method = RequestMethod.POST)
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult submitDomain(Model model, String domainnameId){
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		DyDomainname dyDomainname = dyDomainnameService.get(domainnameId);
		boolean isMoneyEnough = dyDomainnameService.submitDomain(dyDomainname,u);
		if(isMoneyEnough){
			AjaxResult ar = AjaxResult.makeSuccess("提交成功");
			ar.getData().put("isMoneyEnough", isMoneyEnough);
			return ar;
		}else{
			AjaxResult ar = AjaxResult.makeWarn("提交失败,金额不足");
			ar.getData().put("isMoneyEnough", isMoneyEnough);
			return ar;
		}
	}
	
	/**
	 * 提交域名信息
	 * @param model 页面模型
	 * @return 提交结果 (1：成功，0：失败)
	 */
	@RequestMapping(value = {"isell/submitDomain"}, method = RequestMethod.POST)
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult submitDomain(Model model, String domainId, String name, String description, String image1, String image2, String image3, String endTime, String reservePrice, 
			String bonusShareTotal, String bonusShareNumber, String bonusSecond,String id) {
		try {
			// 取得当前会员ID
			DyClient client = DySysUtils.getCurrentDyClient();
			String clientId = client.getId();
			
			//二次提交：第一次提交金额不够，充值后需要二次提交
			if(StringUtils.isNotEmpty(id) && !id.equals("null")){
				DyDomainname dyDomainname = dyDomainnameService.get(id);
				if(dyDomainname == null){
					AjaxResult ar = AjaxResult.makeWarn("无此域名");
					return ar;
				}
				if(!Constant.DOMAIN_STATUS_00.equals(dyDomainname.getStatus())){
					AjaxResult ar = AjaxResult.makeWarn("已经提交过了");
					return ar;
				}
				dyDomainname.setIsNewRecord(false);
				boolean isMoneyEnough = dyDomainnameService.submitDomain(dyDomainname,client);
				if(isMoneyEnough){
					AjaxResult ar = AjaxResult.makeSuccess("提交成功");
					ar.getData().put("isMoneyEnough", isMoneyEnough);
					return ar;
				}else{
					AjaxResult ar = AjaxResult.makeWarn("金额不足,请充值");
					ar.getData().put("isMoneyEnough", isMoneyEnough);
					return ar;
				}
			}
			
			//首次提交
			//检查结拍时间是否有效：每天的结拍数量有限制
			List<DomainEndNumEntity> list = dyDomainnameService.countLately7DayDomianNum();
			for(int i = 0;i<list.size();i++){
				if(endTime.equals(list.get(i).getDate()) && list.get(i).getFlag()){
					AjaxResult ar = AjaxResult.makeError("该结拍时间拍卖域名已满，请选择其它时间");
					return ar;
				}
			}
			
			// 插入域名
			if (StringUtils.isBlank(name)) {
				AjaxResult ar = AjaxResult.makeError("域名不能为空");
				return ar;
			}
			
			DyDomainname domainInfo = new DyDomainname();
			domainInfo.setClientId(clientId);
			String newName = fullWidth2halfWidth(name).toLowerCase().replaceAll("。", ".");
			domainInfo.setName(HtmlUtils.htmlEscape(newName));
			
			domainInfo.setDescription(HtmlUtils.htmlEscape(description));
			
			//如果选择了图片，存储图片的serverId,否则存储“”
			String compareStr = ConfKit.get("host_war")+"/static/images/upimage.jpg";
			if (!image1.equals(compareStr)) {
				domainInfo.setImage1(image1);
			}else{
				domainInfo.setImage1("");
			}
			if (!image2.equals(compareStr)) {
				domainInfo.setImage2(image2);
			}else{
				domainInfo.setImage2("");
			}
			if (!image3.equals(compareStr)) {
				domainInfo.setImage3(image3);
			}else{
				domainInfo.setImage3("");
			}
			
			domainInfo.setReservePrice(stringToLong(reservePrice));
			domainInfo.setBonusShareTotal(stringToLong(bonusShareTotal));
			domainInfo.setBonusShareNumber(stringToLong(bonusShareNumber));
			domainInfo.setBonusSecond(stringToLong(bonusSecond));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(endTime);
			domainInfo.setEndTime(date);
			
			domainInfo.setType(DySysUtils.getDomainnameType(name));
			//domainInfo.setStatus(Constant.DOMAIN_STATUS_01); //状态设置为审核中  移到dyDomainnameService.submitDomain（）方法中设置
			domainInfo.setIncrement(getBidIncrement());
			domainInfo.setDeposit(getBuyDeposit());
			
			domainInfo.preInsert(UserUtils.get(client.getBrokerId()));
			domainInfo.setIsNewRecord(true);
			
			//提交域名，保证金扣除及域名信息保存需一个原子操作同时完成
			boolean isMoneyEnough = dyDomainnameService.submitDomain(domainInfo,client);
			
			// 同步，微信服务器上的图片下载到本地服务器
			dyDomainnameService.downloadImages(domainInfo);
			
			AjaxResult ar = AjaxResult.makeSuccess("提交成功");
			ar.getData().put("isMoneyEnough", isMoneyEnough);
			return ar;
		} catch (Exception e) {
			logger.debug(e.getMessage());
			AjaxResult ar = AjaxResult.makeError("提交失败");
			return ar;
		}
	}
	
	/**
	 * 删除当前用户的所有的审核失败的域名信息
	 * @param model 页面模型
	 * @return 删除结果 (1：成功，0：失败)
	 */
	@RequestMapping(value = {"isell/deleteDomain"}, method = RequestMethod.POST)
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult deleteDomain(Model model) {
		// 取得当前会员ID
		try {
			DyClient client = DySysUtils.getCurrentDyClient();
			dyDomainnameService.deleteDomainByClientId(client.getId());
		} catch (Exception e) {
			logger.error("", e);
			AjaxResult ar = AjaxResult.makeError("删除失败");
			return ar;
		}
		
		AjaxResult ar = AjaxResult.makeSuccess("删除成功");
		return ar;
	}

	//将String转为Long
	private Long stringToLong(String string) {
		if((string == null) || (string.length() == 0)) {
			return 0L;
		} else {
			return Long.valueOf(string);
		}
	}
	
	//取得买家保证金
	private Long getBuyDeposit() {
		return DySysUtils.calculateDepositAndIncrement(0L, Constant.BID_RULE_TYPE_INCREMENT);
	}
	//取得加价幅度
	private Long getBidIncrement() {
		return DySysUtils.calculateDepositAndIncrement(0L, Constant.BID_RULE_TYPE_INCREMENT);
	}
	
	//全角转换成半角
	private String fullWidth2halfWidth(String fullWidthStr) {
        char[] charArray = fullWidthStr.toCharArray();
        //对全角字符转换的char数组遍历
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = (int) charArray[i];
            //如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
            if (charIntValue >= 65281 && charIntValue <= 65374) {
                charArray[i] = (char) (charIntValue - 65248);
            } else if (charIntValue == 12288) {
                charArray[i] = (char) 32;
            }
        }
        return new String(charArray);
    }
}
