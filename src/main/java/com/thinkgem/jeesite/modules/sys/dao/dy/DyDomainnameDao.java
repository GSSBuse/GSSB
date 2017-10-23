/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.paimai.bean.TransactionsEntity;
import com.thinkgem.jeesite.modules.sys.entity.dy.DomainEndNumEntity;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.TransactionInformation;
import com.thinkgem.jeesite.modules.sys.entity.dy.FollowInfoToMsg;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidCashInfo;
import com.thinkgem.jeesite.modules.wx.entity.domainname.PageDomainEntity;

/**
 * 域名信息管理DAO接口
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@MyBatisDao
public interface DyDomainnameDao extends CrudDao<DyDomainname> {

	/**
	 * 根据登录者Id获取域名列表
	 * @param dyDomainname
	 * @param userId
	 * @return
	 */
	public List<DyDomainname> findPageByUserId(PageDomainEntity pageDomainEntity);

	/**
	 * 根据会员编号查询域名信息
	 * @param clientId
	 * @return List<DyDomainname>
	 */
	public List<DyDomainname> findListByClientId(@Param("clientId")String clientId, @Param("status")String status);

	/**
	 * 根据域名ID，会员ID获取拍卖列表数据
	 * @param domainId 域名ID
	 * @param clientId 会员ID
	 * @return 拍卖列表数据
	 */
	public PageDomainEntity getAuctionData(String domainId, String clientId);

	/**
	 * 根据ID更新域名的结拍时间
	 * @param domainId 域名ID
	 * @param endTime 结拍时间
	 */
	public void updateEndTimeById(String domainId, Date endTime);

	/**
	 * 自定义获取域名列表
	 * @param dyDomainname 域名实体
	 * @return 域名列表
	 */
	public List<DyDomainname> customizedFindList(DyDomainname dyDomainname);
	
	/**
	 * 根据经纪人ID，获取该经纪人下所有待确认的已付款的域名列表
	 * @param bidCashInfo  付款信息实体
	 * @return
	 */
	public List<BidCashInfo> findConfirmList(BidCashInfo bidCashInfo);

	/**
	 * 根据经纪人ID、域名Id，获取该经纪人下所有待确认的已付款的域名信息
	 * @param bidCashInfo  付款信息实体
	 * @return
	 */
	public BidCashInfo finddealByDomainId(String domainnameId);

	/**
	 * 根据域名ID更新域名状态
	 * @param domainId 域名ID
	 * @param domainStatus 域名状态
	 * @param expired 操作时限
	 * @return 更新记录数
	 */
	public int updateDomainStatusById(String domainId, String domainStatus, String expired);

	/**
	 * 将过了时限的域名更新为对应的状态
	 * @param domainStatus
	 * @param updateStatus
	 * @return 更新操作数
	 */
	public int updateOverdueDomainStatus(String domainStatus, String updateStatus);
	
	/**
	 * @return 最近七天交易记录，记录以Map记录：domainnameId（域名id），status（域名状态），name（域名名称），sellClientId（卖家id），sellNickName（卖家昵称），sellDate（交易日期），bidAmount（成交日期），buyClientId（买家id），buyNickName(买家昵称)
	 */
	public List<Map<String , Object>> getHistoryInfo();
	
	/**状态03，有人出价且无保留价，获取域名、卖家，买家信息
	 * 审核通过的域名并且结拍时间不大于当前时间的域名
	 * @param domainStatus03 审核通过
	 * @return List :Map (domainnameId sellerId buyerId bidAmount )
	 */
	public List<TransactionInformation> domainnameBuyerAndSellerIfDomainEndAuction(@Param("status")String domainStatus03,@Param("domainnameId")String domainnameId);
	
	/**状态03，有人出价且无保留价，状态改为11
	 * 将审核通过的域名并且结拍时间不大于当前时间的域名状态更新为待买家付款
	 * @param domainStatus03 审核通过
	 * @param domainStatus11 待买家付款
	 * @param waitTime 状态由03更改为11时，会员的操作限时天数
	 * @return 更新操作数
	 */
	public int updateStatusIfDomainEndAuction(String domainStatus03, String domainStatus11,int waitTime);
	
	/**状态03，无人出价，获取其域名实体
	 * 获取审核通过的域名并且结拍时间不大于当前时间且无人出价的域名
	 * @param domainStatus03 审核通过
	 * @param domainStatus23 流拍
	 * @return 更新操作数
	 */
	public List<DyDomainname> getDomainnameIfDomainEndAuctionAndNoBid(@Param("domainStatus03")String domainStatus03,@Param("domainnameId")String domainnameId);
	/**状态03，有人出价且低于保留价，状态改为23
	 * @param domainStatus03 审核通过
	 * @return List :Map (domainnameId sellerId buyerId bidAmount )
	 */
	public List<TransactionInformation> getDomainnameIfDomainEndAuctionAndLow(@Param("status")String domainStatus03,@Param("domainnameId")String domainnameid);
	
	/**状态03，有人出价且高于保留价，状态改为11
	 * @param domainStatus03 审核通过
	 * @return List :Map (domainnameId sellerId buyerId bidAmount )
	 */
	public List<TransactionInformation> getDomainnameIfDomainEndAuctionAndHigh(@Param("status")String domainStatus03,@Param("domainnameId")String domainnameId);
	/**
	 * 根据域名Id，获取交易信息
	 * @param domainId 域名Id
	 * @return
	 */
	public TransactionInformation getTransactionByDomainId(String domainId);
	/**
	 * 查询域名交易结束时间在某个时间段内的数量
	 * @param dayStart 交易开始时间
	 * @param dayEnd交易结束时间
	 * @return
	 */
	public int checkDealNums(Date dayStart, Date dayEnd);
	/**
	 * 查询在某个时间段内，最大的交易开始时间
	 * @param dayStart 当天交易开始时间
	 * @param dayEnd 当天交易结束时间
	 * @return
	 */
	public Date getMaxDealDate(Date dayStart, Date dayEnd);
	/**
	 * 获取未审核的域名数量
	 * @return
	 */
	public int unconfirmCount();
	/**
	 * 获取该会员处于（待卖家转移域名）的域名数量
	 * @param clientId 会员id
	 * @return
	 */
	public int clientToSellDo(String clientId);
	/**
	 * 获取该会员处于（待买家付款，待买家确认）的域名数量
	 * @param clientId 会员id
	 * @return
	 */
	public int clientToBuyDo(String clientId);
	/**
	 * 根据经纪人id ,获取未审核的域名数量
	 * @param brokerId 经纪人id
	 * @return
	 */
	public int unconfirmCountBroker(String brokerId);
	/**
	 * 获取等待经济人确认交易结束的域名数量
	 * @return
	 */
	public int unfinishCount();
	/**
	 * 根据经纪人ID，获取待经济人确认交易结束的域名数量
	 * @return
	 */
	public  int unfinishCountBroker(String brokerId);

	/**
	 * 删除会员所有审核失败的域名
	 * @param clientId 会员ID
	 */
	public void deleteDomainByClientId(String clientId);
	
	/**
	 * 删除会员指定的审核失败的域名
	 * @param clientId 会员ID
	 */
	public void deleteDomainById(@Param("clientId")String clientId,@Param("id") String id);
	
	/**PC端取我参与买的交易信息
	 * @param transactionsEntity
	 * @return
	 */
	public List<TransactionsEntity> myTransactionsBuyPage(TransactionsEntity transactionsEntity);
	/**
	 * PC端取我参与卖的交易信息
	 * @param transactionsEntity
	 * @return
	 */
	public List<TransactionsEntity> myTransactionsSellPage(TransactionsEntity transactionsEntity);
	
	/**
	 * @return 还有一小时结拍的域名名称及关注者openid
	 */
	public List<FollowInfoToMsg> lastOneHourDomainInfo(FollowInfoToMsg followInfoToMsg);
	/**
	 * 统计某一天域名结拍的数量
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public List<DomainEndNumEntity> getDomainNum(@Param("maxNum")int maxNum,@Param("beginDate")Date beginDate,@Param("endDate")Date endDate);
}