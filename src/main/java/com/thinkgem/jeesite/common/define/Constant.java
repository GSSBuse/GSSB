/**
 * 
 */
package com.thinkgem.jeesite.common.define;


/**
 * 共通常量定义
 * @author shenzb.fnst
 *
 */
public interface Constant {

	/** 有无效标识 */
	public static String DEL_FLAG_0 = "0"; // 有效
	public static String DEL_FLAG_1 = "1"; // 无效

	/** 页面大小 */
	public static int PAGE_SIZE_2  = 2;
	public static int PAGE_SIZE_3  = 3;
	public static int PAGE_SIZE_5  = 5;
	public static int PAGE_SIZE_8  = 8;
	public static int PAGE_SIZE_10 = 10;
	public static int PAGE_SIZE_12 = 12;


	/** 默认第一页 */
	public static int DEFAULT_PAGE_INDEX = 1;

	/** 查询分页时不获取数据总数标识 */
	public static int QUERY_WITHOUT_COUNT = -1;

	/** 数字字符串 */
	public static String STRING_0 = "0";
	public static String STRING_1 = "1";

	/** Session key */
	public static String SESSION_KEY_SHARE_OPENID = "shareOpenid";
	public static String SESSION_KEY_CURRENT_CLIENT = "current_dy_client";

	/** 域名状态 */
	public static String DOMAIN_STATUS_00 = "00"; // 待付款（金额无法支付保证金，充值后可以重新提交）
	public static String DOMAIN_STATUS_01 = "01"; // 审核中
	public static String DOMAIN_STATUS_02 = "02"; // 审核失败
	public static String DOMAIN_STATUS_03 = "03"; // 审核通过
	public static String DOMAIN_STATUS_11 = "11"; // 待买家付款
	public static String DOMAIN_STATUS_12 = "12"; // 待卖家转移域名
	public static String DOMAIN_STATUS_13 = "13"; // 待买家确认
	public static String DOMAIN_STATUS_14 = "14"; // 待经纪人确认交易完成
	public static String DOMAIN_STATUS_15 = "15"; // 交易完成
	public static String DOMAIN_STATUS_21 = "21"; // 买家违约
	public static String DOMAIN_STATUS_22 = "22"; // 卖家违约
	public static String DOMAIN_STATUS_23 = "23"; // 流拍或终止
	public static String DOMAIN_STATUS_1_ = "1_"; // DOMAIN_STATUS_11,DOMAIN_STATUS_12,DOMAIN_STATUS_13,DOMAIN_STATUS_14,DOMAIN_STATUS_15
	public static String DOMAIN_STATUS_2_ = "2_"; // DOMAIN_STATUS_21,DOMAIN_STATUS_22,DOMAIN_STATUS_23
	
	/**红包佣金表的type**/
	public static String BONUS_TYPE_01 = "01";//分享红包
	public static String BONUS_TYPE_02 = "02";//次高价红包
	public static String BONUS_TYPE_03 = "03";//佣金
	
	/**email激活状态**/
	public static String EMAIL_FLAG_1 = "1";//已激活
	public static String EMAIL_FLAG_0 = "0";//未激活
	public static String EMAIL_FLAG_2 = "2";//当前无email
	
	/**身份认证状态**/
	public static String AUTHENTICATION_MARK_1 = "1";//身份已认证
	public static String AUTHENTICATION_MARK_0 = "0";//身份未认证
	public static String AUTHENTICATION_MARK_2 = "2";//身份认证中
	/**字符编码**/
	public static String UTF_8 = "UTF-8";

	/** 出价规则（保证金、加价幅度）缓存KEY */
	public static String CACHE_BID_RULES_MAP = "bidRulesMap";

	/** 出价规则（保证金、加价幅度） */
	public static String BID_RULE_TYPE_DEPOSIT   = "bid_rule_deposit";   // 保证金
	public static String BID_RULE_TYPE_INCREMENT = "bid_rule_increment"; // 加价幅度
	
	/**短信模板**/
	//模板ID:6988,内容为：【登羽科技】您的验证码是#code#。如非本人操作，请忽略本短信
	public static String TPL_ID_1="6988";
	
	public static String DAY_START_TIME = "00:00:00";		//凌晨
	public static String DAY_END_TIME = "23:59:59";			//夜末
	
	/**资金流的操作类型*/
	public static String CASHFLOW_OPERATE_REBBAG_OUT = "红包支出";
	public static String CASHFLOW_OPERATE_REBBAG_IN = "红包收入";
	public static String CASHFLOW_OPERATE_RECHARGE_ONLINE = "微信充值";
	public static String CASHFLOW_OPERATE_RECHARGE_OUTLINE = "线下充值";
	public static String CASHFLOW_OPERATE_WITHDRAW = "提现";
	public static String CASHFLOW_OPERATE_PAY = "付款";
	public static String CASHFLOW_OPERATE_GET = "收款";
	public static String CASHFLOW_OPERATE_SHARE_IN = "佣金收入";
	public static String BREACH_IN = "违约收入";
	public static String BREACH_OUT = "违约扣除";
	public static String REDPACK_RETURN = "红包退回";
	public static String CASHFLOW_OPERATE_FREEZE = "冻结";
	public static String CASHFLOW_OPERATE_UNFREEZE = "解冻";
	
	/**资金流的确认结果*/
	public static String CASHFLOW_CONFIRM_WAIT = "等待";
	public static String CASHFLOW_COMFIRM_DONE = "完成";
	public static String CASHFLOW_COMFIRM_DOING = "处理中";
	public static String CASHFLOW_COMFIRM_NOT = "打回";
	public static String CASHFLOW_COMFIRM_CANCEL = "已取消";
	
	/**平台账户总额的id*/
	public static String PLATFORM_ACCOUNT_FINANCE_ID = "1";
	public static String PLATFORM_ACCOUNT_INCOME_ID = "2";
	
	/**平台收支总额的类型*/
	public static String PLATFORM_INCOME_OPERATE_IN = "域名收入";
	
	/**推送消息表的URLID的类型*/
	public static String MESSAGE_URL_ARTILE = "0";	//文章
	public static String MESSAGE_URL_DOMAIN = "1";	//域名
	
	/**发送消息时是否群发*/
	public static String MESSAGEGROUP_SEND_ALL = "1";	//群发
	public static String MESSAGEGROUP_SEND_NOTALL = "0";	//关联发送
	
	/**消息的发送状态*/
	public static String MESSAGEGROUP_STATUS_SEND = "1";	//已经发送
	public static String MESSAGEGROUP_STATUS_NOTSEND = "0";	//没有发送
	
	/**发送消息的每一条链接类型*/
	public static String MESSAGE_URLTYPE_ARTICLE = "0";	//文章
	public static String MESSAGE_URLTYPE_DOMAIN = "1";	//域名
	
	/**域名审核时，交易结束时间的延迟(5小时)*/
	public static long DOMAINNAE_ENDDATE_LAST = 5 * 3600 * 1000;
	
	/**压缩图片的宽度和高度*/
	
	public static int FORMAT_IMAGE_WIDTH = 400; //宽度
	public static int FORMAT_IMAGE_HEIGHT = 300; //高度
	
	/**日志标题类型*/
	public static String LOG_CASH_FLOW_APPLY = "会员管理-充值提现申请";
	public static String LOG_CLIENT_FORM_SAVE = "会员管理-修改";
	public static String LOG_DOMAINNAME_SAVEPASS = "域名拍卖管理-审核";
	public static String LOG_DOMAINNAME_SAVE = "域名拍卖管理-修改（添加）";
	public static String LOG_DOMAINNAME_STOPSELL = "域名拍卖管理-停止拍卖";
	public static String LOG_BIDHISTORY_BID = "域名拍卖管理-出价管理-出价";
	public static String LOG_BIDHISTORY_DELETE = "域名拍卖管理-出价管理-删除";
	public static String LOG_DOMAINNAME_DEALSAVE = "域名成交管理-修改";
	public static String LOG_CASHFLOW_DOING = "财务管理-提现处理";
	public static String LOG_CASHFLOW_CONFIRM = "财务管理-确认";
	public static String LOG_CASHFLOW_DELETE = "财务管理-删除";
	public static String LOG_BIDHISTORY_UPDATEPROXY = "域名拍卖管理-出价管理-修改代理竞价";
	
	/**日志标题对应的url*/
	public static String LOG_CASH_FLOW_APPLY_URL = "/sys/dy/dyCashFlow/applySave";		//会员管理-充值提现申请
	public static String LOG_CLIENT_FORM_SAVE_URL = "/sys/dy/dyClient/save";			//会员管理-修改
	public static String LOG_DOMAINNAME_SAVEPASS_URL = "/sys/dy/dyDomainname/savePass";//域名拍卖管理-审核
	public static String LOG_DOMAINNAME_SAVE_URL = "/sys/dy/dyDomainname/save";		//域名拍卖管理-修改
	public static String LOG_DOMAINNAME_STOPSELL_URL = "/sys/dy/dyDomainname/stopSell";	//域名拍卖管理-停止拍卖
	public static String LOG_BIDHISTORY_BID_URL = "/sys/dy/dyBidhistory/save";	//域名拍卖管理-出价管理-出价
	public static String LOG_BIDHISTORY_DELETE_URL = "/sys/dy/dyBidhistory/delete";	//域名拍卖管理-出价管理-删除
	public static String LOG_DOMAINNAME_DEALSAVE_URL = "/sys/dy/dyDomainname/dealSave";	//域名成交管理-修改
	public static String LOG_CASHFLOW_DOING_URL = "/sys/dy/dyCashFlow/drawProcessing";		//财务管理-提现处理
	public static String LOG_CASHFLOW_CONFIRM_URL = "/sys/dy/dyCashFlow/confirmDo";		//财务管理-确认
	public static String LOG_CASHFLOW_DELETE_URL = "/sys/dy/dyCashFlow/delete";		//财务管理-删除
	public static String LOG_BIDHISTORY_UPDATEPROXY_URL = "/sys/dy/dyBidhistory/updateProxy";	//域名拍卖管理-出价管理-修改代理竞价;
	
	/**日志的用户请求代理*/
	public static String LOG_USER_AGENT = "user-agent";
	
	public static final String PAGE_DATA_CACHE = "pageDataCache";
	public static final String SHOW_DOMAIN_LIST_KEY = "showDomainList";
	public static final String SHOW_DOMAIN_ID_SET_KEY = "showDomainIdSet";
	public static final String SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX = "showDomainAttentionList_";
	public static final String SHOW_DOMAIN_BIDS_KEY_PREFIX = "showDomainBidList_";
	/**红包功能开关*/
	public static String SHARE_BONUS_SWITCH_ON = "1";
	public static String SHARE_BONUS_SWITCH_OFF = "0";
	/**最近N天*/
	public static int LATELY_N_DAY = 7;//提交域名时选择结拍时间的天数
	
	/** 开关: 开（1） */
	public static String SWITCH_ON = "1";
	/** 开关: 关（0） */
	public static String SWITCH_OFF = "0";
}
