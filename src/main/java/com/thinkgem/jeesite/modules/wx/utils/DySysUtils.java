/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.util.StreamUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyLevelSettingDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyTemplateMessageDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyLevelSetting;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyTemplateMessage;
import com.thinkgem.jeesite.modules.sys.service.dy.DyAttentionService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBidhistoryService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.ImgCompress;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 登羽系统工具类
 * 
 * @author shenzb.fnst
 * @version 2015-10-12
 */
/**
 * @author shenzb.fnst
 *
 */
/**
 * @author shenzb.fnst
 *
 */
public class DySysUtils implements Constant {

	// 会员出价信息DAO接口
	private static DyBidhistoryService dyBidhistoryService = SpringContextHolder
			.getBean(DyBidhistoryService.class);
	// 会员关注管理
	private static DyAttentionService dyAttentionService = SpringContextHolder
			.getBean(DyAttentionService.class);
	// 加价与保证金增幅DAO接口
	private static DyLevelSettingDao dyLevelSettingDao = SpringContextHolder
			.getBean(DyLevelSettingDao.class);
	// 模板消息DAO接口
	private static DyTemplateMessageDao dyTemplateMessageDao = SpringContextHolder
				.getBean(DyTemplateMessageDao.class);

	// 系统常量取得
	/** 平台佣金比率(百分比) **/
	public static final double BONUS_PERCENT_PLATFORM = getBonusPercentPlatform();
	/** 分享佣金比率(百分比) **/
	public static final double BONUS_PERCENT_SHARE = getBonusPercentShare();

	/** 次高价红包上限(百分比) **/
	public static final double BONUS_SECOND_LIMIT = getBonusSecondLimit();

	/** 每一天的域名截拍时间段-开始时间 **/
	public static final String DEAL_START_TIME = getDealStartTime();
	/** 每一天的域名截拍时间段-结束时间 **/
	public static final String DEAL_END_TIME = getDealEndTime();
	/** 每一天的域名截拍个数 **/
	public static final int DEAL_NUM = getDealNumber(); // 每一天的交易数量

	/** 拍卖时间延时 **/
	public static final int END_TIME_DELAY = getEndTimeDelay();

	/** 操作限时-付款操作限时(天) **/
	public static final int OPERATE_LIMIT_TIME_PAY = getLimitTimePay();
	/** 操作限时-转移域名操作限时(天) **/
	public static final int OPERATE_LIMIT_TIME_TRANSFER = getLimitTimeTransfer();
	/** 操作限时-确认收到域名操作限时(天) **/
	public static final int OPERATE_LIMIT_TIME_RECEIVE = getLimitTimeReceive();
	/** 操作限时-经纪人确认完成交易限时(天) **/
	public static final int OPERATE_LIMIT_TIME_CONFIRM = getLimitTimeConfirm();

	/** 卖家保证金（域名审核时冻结） **/
	public static final long SELL_DEPOSIT = getSellDeposit();

	/** 拍卖列表排序规则 **/
	public static final String SORT_ORDERBY = getSortOrderby();
	
	/** 分享红包功能 1：开启 0：关闭 **/
	public static final String SHARE_BONUS_ENABLE = getShareBonusEnable();

	/** 模板消息标题-成功提交域名通知-余额足够 **/
	public static final String TEMPLATE_TITLE_0001 = getTemplateTitle("1");
	/** 模板消息标题-成功提交域名通知-余额不足 **/
	public static final String TEMPLATE_TITLE_0002 = getTemplateTitle("2");
	/** 模板消息标题-域名审核失败通知 **/
	public static final String TEMPLATE_TITLE_0003 = getTemplateTitle("3");
	/** 模板消息标题-域名审核成功通知 **/
	public static final String TEMPLATE_TITLE_0004 = getTemplateTitle("4");
	/** 模板消息标题-竞拍成交通知-卖家 **/
	public static final String TEMPLATE_TITLE_0005 = getTemplateTitle("5");
	/** 模板消息标题-竞拍成交通知-买家 **/
	public static final String TEMPLATE_TITLE_0006 = getTemplateTitle("6");
	/** 模板消息标题-域名流拍通知 **/
	public static final String TEMPLATE_TITLE_0007 = getTemplateTitle("7");
	/** 模板消息标题-域名停拍通知 **/
	public static final String TEMPLATE_TITLE_0008 = getTemplateTitle("8");
	/** 模板消息标题-买家已付款通知 **/
	public static final String TEMPLATE_TITLE_0009 = getTemplateTitle("9");
	/** 模板消息标题-买家违约通知-买家 **/
	public static final String TEMPLATE_TITLE_0010 = getTemplateTitle("10");
	/** 模板消息标题-买家违约通知-卖家 **/
	public static final String TEMPLATE_TITLE_0011 = getTemplateTitle("11");
	/** 模板消息标题-卖家已转移域名通知 **/
	public static final String TEMPLATE_TITLE_0012 = getTemplateTitle("12");
	/** 模板消息标题-卖家违约通知-买家 **/
	public static final String TEMPLATE_TITLE_0013 = getTemplateTitle("13");
	/** 模板消息标题-卖家违约通知-卖家 **/
	public static final String TEMPLATE_TITLE_0014 = getTemplateTitle("14");
	/** 模板消息标题-买家已确认收到域名通知-买家 **/
	public static final String TEMPLATE_TITLE_0015 = getTemplateTitle("15");
	/** 模板消息标题-买家已确认收到域名通知-卖家 **/
	public static final String TEMPLATE_TITLE_0016 = getTemplateTitle("16");
	/** 模板消息标题-最终成交通知-买家 **/
	public static final String TEMPLATE_TITLE_0017 = getTemplateTitle("17");
	/** 模板消息标题-最终成交通知-卖家 **/
	public static final String TEMPLATE_TITLE_0018 = getTemplateTitle("18");
	/** 模板消息标题-获得次高价红包通知 **/
	public static final String TEMPLATE_TITLE_0019 = getTemplateTitle("19");
	/** 模板消息标题-获得分享佣金通知 **/
	public static final String TEMPLATE_TITLE_0020 = getTemplateTitle("20");
	/** 模板消息标题-充值成功通知 **/
	public static final String TEMPLATE_TITLE_0021 = getTemplateTitle("21");
	/** 模板消息标题-提现成功通知 **/
	public static final String TEMPLATE_TITLE_0022 = getTemplateTitle("22");
	/** 模板消息标题-获得分享红包通知 **/
	public static final String TEMPLATE_TITLE_0023 = getTemplateTitle("23");
	/** 模板消息标题-充值失败通知 **/
	public static final String TEMPLATE_TITLE_0024 = getTemplateTitle("24");
	/** 模板消息标题-提现失败通知 **/
	public static final String TEMPLATE_TITLE_0025 = getTemplateTitle("25");
	/** 模板消息标题-追加红包信息 **/
	public static final String TEMPLATE_TITLE_0026 = getTemplateTitle("26");
	/** 模板消息标题-微信支付信息 **/
	public static final String TEMPLATE_TITLE_0027 = getTemplateTitle("27");
	/** 模板消息标题-结拍最后一小时 **/
	public static final String TEMPLATE_TITLE_0028 = getTemplateTitle("28");
	
	/** 模板消息-成功提交域名通知-余额足够 **/
	public static final String TEMPLATE_MESSAGE_0001 = getTemplateContent("1");
	/** 模板消息-成功提交域名通知-余额不足 **/
	public static final String TEMPLATE_MESSAGE_0002 = getTemplateContent("2");
	/** 模板消息-域名审核失败通知 **/
	public static final String TEMPLATE_MESSAGE_0003 = getTemplateContent("3");
	/** 模板消息-域名审核成功通知 **/
	public static final String TEMPLATE_MESSAGE_0004 = getTemplateContent("4");
	/** 模板消息-竞拍成交通知-卖家 **/
	public static final String TEMPLATE_MESSAGE_0005 = getTemplateContent("5");
	/** 模板消息-竞拍成交通知-买家 **/
	public static final String TEMPLATE_MESSAGE_0006 = getTemplateContent("6");
	/** 模板消息-域名流拍通知 **/
	public static final String TEMPLATE_MESSAGE_0007 = getTemplateContent("7");
	/** 模板消息-域名停拍通知 **/
	public static final String TEMPLATE_MESSAGE_0008 = getTemplateContent("8");
	/** 模板消息-买家已付款通知 **/
	public static final String TEMPLATE_MESSAGE_0009 = getTemplateContent("9");
	/** 模板消息-买家违约通知-买家 **/
	public static final String TEMPLATE_MESSAGE_0010 = getTemplateContent("10");
	/** 模板消息-买家违约通知-卖家 **/
	public static final String TEMPLATE_MESSAGE_0011 = getTemplateContent("11");
	/** 模板消息-卖家已转移域名通知 **/
	public static final String TEMPLATE_MESSAGE_0012 = getTemplateContent("12");
	/** 模板消息-卖家违约通知-买家 **/
	public static final String TEMPLATE_MESSAGE_0013 = getTemplateContent("13");
	/** 模板消息-卖家违约通知-卖家 **/
	public static final String TEMPLATE_MESSAGE_0014 = getTemplateContent("14");
	/** 模板消息-买家已确认收到域名通知-买家 **/
	public static final String TEMPLATE_MESSAGE_0015 = getTemplateContent("15");
	/** 模板消息-买家已确认收到域名通知-卖家 **/
	public static final String TEMPLATE_MESSAGE_0016 = getTemplateContent("16");
	/** 模板消息-最终成交通知-买家 **/
	public static final String TEMPLATE_MESSAGE_0017 = getTemplateContent("17");
	/** 模板消息-最终成交通知-卖家 **/
	public static final String TEMPLATE_MESSAGE_0018 = getTemplateContent("18");
	/** 模板消息-获得次高价红包通知 **/
	public static final String TEMPLATE_MESSAGE_0019 = getTemplateContent("19");
	/** 模板消息-获得分享佣金通知 **/
	public static final String TEMPLATE_MESSAGE_0020 = getTemplateContent("20");
	/** 模板消息-充值成功通知 **/
	public static final String TEMPLATE_MESSAGE_0021 = getTemplateContent("21");
	/** 模板消息-提现成功通知 **/
	public static final String TEMPLATE_MESSAGE_0022 = getTemplateContent("22");
	/** 模板消息-获得分享红包通知 **/
	public static final String TEMPLATE_MESSAGE_0023 = getTemplateContent("23");
	/** 模板消息-充值失败通知 **/
	public static final String TEMPLATE_MESSAGE_0024 = getTemplateContent("24");
	/** 模板消息-提现失败通知 **/
	public static final String TEMPLATE_MESSAGE_0025 = getTemplateContent("25");
	/** 模板消息-追加红包信息 **/
	public static final String TEMPLATE_MESSAGE_0026 = getTemplateContent("26");
	/** 模板消息-微信支付信息 **/
	public static final String TEMPLATE_MESSAGE_0027 = getTemplateContent("27");
	/** 模板消息-结拍最后一小时 **/
	public static final String TEMPLATE_MESSAGE_0028 = getTemplateContent("28");
	
	
	/** 初始化常量 **/
	private static String getShareBonusEnable() {
		String value = DictUtils.getDictValueByType("dy_constant_sharebonus_enable");
		return value;
	}
	
	private static String getSortOrderby() {
		String value = DictUtils.getDictValueByType("dy_constant_sort_orderby");
		return value;
	}

	private static long getSellDeposit() {
		String value = DictUtils.getDictValueByType("dy_constant_sell_deposit");
		try {
			long deposit = Long.valueOf(value.trim());
			return deposit;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 500;
	}

	private static int getLimitTimeReceive() {
		String value = DictUtils.getDictValueByType("dy_constant_operate_limit_time_receive");
		try {
			int time = Integer.valueOf(value.trim());
			return time;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 2;
	}

	private static int getLimitTimeConfirm() {
		String value = DictUtils.getDictValueByType("dy_constant_operate_limit_time_confirm");
		try {
			int time = Integer.valueOf(value.trim());
			return time;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 10;
	}

	private static int getLimitTimeTransfer() {
		String value = DictUtils.getDictValueByType("dy_constant_operate_limit_time_transfer");
		try {
			int time = Integer.valueOf(value.trim());
			return time;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 7;
	}

	private static int getLimitTimePay() {
		String value = DictUtils.getDictValueByType("dy_constant_operate_limit_time_pay");
		try {
			int time = Integer.valueOf(value.trim());
			return time;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 2;
	}

	private static int getEndTimeDelay() {
		String value = DictUtils.getDictValueByType("dy_constant_endtime_delay");
		try {
			int time = Integer.valueOf(value.trim());
			return time;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 3;
	}

	private static int getDealNumber() {
		String value = DictUtils.getDictValueByType("dy_constant_deal_number");
		try {
			int num = Integer.valueOf(value.trim());
			return num;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 20;
	}

	private static String getDealEndTime() {
		String value = DictUtils.getDictValueByType("dy_constant_deal_end_time");
		return value;
	}

	private static String getDealStartTime() {
		String value = DictUtils.getDictValueByType("dy_constant_deal_start_time");
		return value;
	}

	private static double getBonusSecondLimit() {
		String value = DictUtils.getDictValueByType("dy_constant_bonus_second_limit");
		try {
			double percent = Double.valueOf(value.trim());
			return percent;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 10;
	}

	private static double getBonusPercentShare() {
		String value = DictUtils.getDictValueByType("dy_constant_bonus_percent_share");
		try {
			double percent = Double.valueOf(value.trim());
			return percent;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 0.4;
	}

	private static double getBonusPercentPlatform() {
		String value = DictUtils.getDictValueByType("dy_constant_bonus_percent_platform");
		try {
			double percent = Double.valueOf(value.trim());
			return percent;
		} catch (Exception e) {
			// DO NOTHING
		}
		return 2;
	}
	
	private static String getTemplateContent(String id) {
		DyTemplateMessage message = dyTemplateMessageDao.get(id);
		if (message != null) {
			return message.getContent();
		}
		return "";
	}
	
	private static String getTemplateTitle(String id) {
		DyTemplateMessage message = dyTemplateMessageDao.get(id);
		if (message != null) {
			return message.getTitle();
		}
		return "";
	}

	/**
	 * 获取当前登录会员实体
	 * 
	 * @return 当前登录会员实体
	 */
	public static DyClient getCurrentDyClient() {
		return (DyClient) UserUtils.getSession().getAttribute(
				SESSION_KEY_CURRENT_CLIENT);
	}

	/**
	 * 获取分享链接会员openid
	 * 
	 * @return 分享链接会员openid
	 */
	public static String getShareOpenid() {
		return (String) UserUtils.getSession().getAttribute(
				SESSION_KEY_SHARE_OPENID);
	}

	/**
	 * 根据域名实体判断会员是否参与（关注中或者出过价）过该域名
	 * 
	 * @param client
	 *            会员实体
	 * @param domain
	 *            域名实体
	 * @return 参与/未参与
	 */
	public static boolean isParticipated(DyClient client, DyDomainname domain) {
		if (null == client || null == domain) {
			return false;
		}
		DyAttention dyAttention = new DyAttention();
		dyAttention.setClientId(client.getId());
		dyAttention.setDomainnameId(domain.getId());
		dyAttention.setDelFlag(DEL_FLAG_0);

		DyBidhistory dyBidhistory = new DyBidhistory();
		dyBidhistory.setClientId(client.getId());
		dyBidhistory.setDomainnameId(domain.getId());
		dyBidhistory.setDelFlag(DEL_FLAG_0);

		return dyAttentionService.isAttented(dyAttention)
				|| dyBidhistoryService.isBided(dyBidhistory)
				|| client.equals(domain.getClientId());
	}

	/**
	 * 域名分类
	 * 
	 * @param name
	 *            域名
	 * @return 域名类别
	 */
	public static String getDomainnameType(String name) {
		return "00";
	}

	/**
	 * 根据类型（保证金/加价幅度）获取出价规则列表
	 * 
	 * @param type
	 *            类型
	 * @return 出价规则列表
	 */
	public static List<DyLevelSetting> getBidRulesList(String type) {
		@SuppressWarnings("unchecked")
		Map<String, List<DyLevelSetting>> bidRulesMap = (Map<String, List<DyLevelSetting>>) CacheUtils
				.get(CACHE_BID_RULES_MAP);
		if (bidRulesMap == null) {
			bidRulesMap = Maps.newHashMap();
			for (DyLevelSetting level : dyLevelSettingDao
					.findAllList(new DyLevelSetting())) {
				List<DyLevelSetting> levelList = bidRulesMap.get(level
						.getType());
				if (null != levelList) {
					levelList.add(level);
				} else {
					bidRulesMap.put(level.getType(), Lists.newArrayList(level));
				}
			}
			CacheUtils.put(CACHE_BID_RULES_MAP, bidRulesMap);
		}
		List<DyLevelSetting> bidRulesList = bidRulesMap.get(type);
		if (bidRulesList == null) {
			bidRulesList = Lists.newArrayList();
		}
		return bidRulesList;
	}

	/**
	 * 根据加价金额计算保证金以及加价幅度
	 * 
	 * @param bidAmount
	 *            加价金额
	 * @param type
	 *            类型
	 * @return 保证金/加价幅度
	 */
	public static Long calculateDepositAndIncrement(Long bidAmount, String type) {
		List<DyLevelSetting> levelList = Lists.newArrayList(); // 规则列表

		levelList.addAll(getBidRulesList(type));

		for (int idx = 0; idx < levelList.size(); idx++) {
			DyLevelSetting level = levelList.get(idx);
			if (bidAmount.longValue() < level.getLevel()) {
				return levelList.get(idx).getPrice();
			}
		}
		return levelList.isEmpty() ? 200L : levelList.get(levelList.size() - 1)
				.getPrice();

	}

	/**
	 * 根据加价金额确认是否满足加价规则
	 * 
	 * @param bidAmount
	 *            加价金额
	 * @return 满足/不满足
	 */
	public static boolean conformToBidRule(Long bidAmount) {
		List<DyLevelSetting> levelList = Lists.newArrayList(); // 规则列表

		levelList.addAll(getBidRulesList(BID_RULE_TYPE_INCREMENT));

		for (int idx = 0; idx < levelList.size(); idx++) {
			DyLevelSetting level = levelList.get(idx);
			if (bidAmount.longValue() < level.getLevel()) {
				if (idx == 0) {
					return (bidAmount.longValue() % level.getPrice()) == 0;
				} else {
					DyLevelSetting prevLevel = levelList.get(idx - 1);
					return ((bidAmount.longValue() - prevLevel.getLevel()) % level
							.getPrice()) == 0;
				}
			}
		}

		DyLevelSetting lastLevel = null;
		if (levelList.isEmpty()) {
			return true;
		} else {
			lastLevel = levelList.get(levelList.size() - 1);
		}

		return (bidAmount.longValue() - lastLevel.getLevel())
				% lastLevel.getPrice() == 0;
	}

	/**
	 * 根据加价金额确认获取对应区间加价规则
	 * 
	 * @param bidAmount
	 *            加价金额
	 * @return 区间加价规则
	 */
	public static String[] getBidLevel(Long bidAmount) {
		String[] rule = new String[2];
		List<DyLevelSetting> levelList = Lists.newArrayList(); // 规则列表

		levelList.addAll(getBidRulesList(BID_RULE_TYPE_INCREMENT));

		for (int idx = 0; idx < levelList.size(); idx++) {
			DyLevelSetting level = levelList.get(idx);
			if (bidAmount.longValue() < level.getLevel()) {
				if (idx == 0) {
					rule[0] = "0~" + moneyTransShort(String.valueOf(level.getLevel()));
					rule[1] = moneyTransShort(String.valueOf(level.getPrice()));
					return rule;
				} else {
					DyLevelSetting prevLevel = levelList.get(idx-1);
					rule[0] = moneyTransShort(String.valueOf(prevLevel.getLevel())) + "~"
							+ moneyTransShort(String.valueOf(level.getLevel()));
					rule[1] = moneyTransShort(String.valueOf(level.getPrice()));
					return rule;
				}
			}
		}

		DyLevelSetting lastLevel = null;
		if (levelList.isEmpty()) {
			rule = new String[] { "", "" };
		} else {
			lastLevel = levelList.get(levelList.size() - 1);
			rule[0] = moneyTransShort(String.valueOf(lastLevel.getLevel())) + "以上";
			rule[1] = moneyTransShort(String.valueOf(lastLevel.getPrice()));
		}

		return rule;
	}

	
	/**
	 * 根据红包总金额和个数获取一个随机红包
	 * @param moneySum
	 * @param redNum
	 * @return 随机红包
	 */
	public static double wxAlgorithm(double moneySum, int redNum) {
		
		if (moneySum/redNum == 1 || moneySum/redNum == 200) {
			return moneySum/redNum;
		}

		// 设置最小的金额和最大的金额
		double moneyMin = 1.00;
		double moneyMax = moneySum >= 200 ? 200 : moneySum;
		
		Random random = new Random();
		// 精确小数点2位
		NumberFormat formatter = new DecimalFormat("#.##");
		double money = random.nextDouble() * (moneyMax - moneyMin) + moneyMin;
		double remainAvearge = (moneySum - money) / (redNum - 1);
		while (remainAvearge > 200 || remainAvearge < 1) {
			if (remainAvearge > 200) {
				money = random.nextDouble() * (200 - money) + money;
				remainAvearge = (moneySum - money) / (redNum - 1);
			} else {
				money = random.nextDouble() * (money - 1) + 1;
				remainAvearge = (moneySum - money) / (redNum - 1);
			}
		}
		
		return Double.parseDouble(formatter.format(money));
	}
	
	/**
	 * 根据消息模板发送微信消息
	 * @param messgeTemplate 消息模板
	 * @param openid openid
	 * @param params 模板参数
	 * @return 是否成功
	 */
	public static boolean sendWxMessage(String title , String messgeTemplate, String openid, String domainnameId, String... params) {
		Message message = new Message();
		String content = messgeTemplate;
		if ((messgeTemplate.split("\\{\\{").length - 1) != params.length) {
			return false;
		}
		for (String param : params) {
			content = content.replaceFirst("\\{\\{.+?\\}\\}", param);
		}
		try {
			message.SendNews(WeChat.getAccessToken(), openid, title, content, domainnameId);
			//message.sendText(WeChat.getAccessToken(), openid, content);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 压缩图片
	 * @param imagePath 图片路径
	 */
	public static void formatPicture(String imagePath){
		try {
			ImgCompress imgCom = new ImgCompress();
			String realPath = Global.getUserfilesBaseDir() + imagePath.substring(imagePath.indexOf(Global.USERFILES_BASE_URL));
			imgCom.saveImageAsJpg(realPath, realPath, Constant.FORMAT_IMAGE_WIDTH, Constant.FORMAT_IMAGE_HEIGHT);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	public static void formatPicture(InputStream imageStream, String saveImagePath) throws Exception{
		BufferedOutputStream outputStream = null;
		try {
			//保存原图
			FileUtils.createFile(saveImagePath + ".org");
			outputStream = new BufferedOutputStream(new FileOutputStream(saveImagePath + ".org"));
			StreamUtils.copy(imageStream, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				imageStream.close();
			}
			catch (IOException ex) {
			}
			
			if (outputStream != null) {
				try {
					outputStream.close();
				}
				catch (IOException ex) {
				}
			}
		}
		
		try {
			//压缩
			ImgCompress imgCom = new ImgCompress();
			imgCom.saveImageAsJpg(saveImagePath + ".org", saveImagePath, Constant.FORMAT_IMAGE_WIDTH, Constant.FORMAT_IMAGE_HEIGHT);
		} catch (Exception e) {
			e.printStackTrace();
			// DO NOTHING
		}
	}
	/**
	 * 域名状态注释
	 */
	public static String transformDomainStatus(String status){
		String type = "";
		if(StringUtils.equals(Constant.DOMAIN_STATUS_01, status)){
			type = "审核中";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_02, status)){
			type = "审核失败";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_03, status)){
			type = "审核通过";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_11, status)){
			type = "待买家付款";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_12, status)){
			type = "待卖家转移域名";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_13, status)){
			type = "待买家确认";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_14, status)){
			type = "待经纪人确认交易完成";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_15, status)){
			type = "交易完成";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_21, status)){
			type = "买家违约";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_22, status)){
			type = "卖家违约";
		}else if(StringUtils.equals(Constant.DOMAIN_STATUS_23, status)){
			type = "流拍或终止";
		}
		return type;
	}
	
	public static String moneyTransShort(String num) {
		if (StringUtils.isEmpty(num)) {
			return num;
		}
		
		if (num.endsWith("0000")) {
			return num.substring(0, num.length()-4) + "万";
		} else {
			return num;
		}
	}
}
