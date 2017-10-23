/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.TransactionInformation;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidCashInfo;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidClient;

/**
 * 会员出价信息DAO接口
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@MyBatisDao
public interface DyBidhistoryDao extends CrudDao<DyBidhistory> {
	
	/**
	 * 根据域名ID获取域名最新价
	 * @param domainId 域名ID
	 * @return 域名最新价
	 */
	DyBidhistory getMaxBidAmount(String domainId);
	/**
	 * 获取域名的最高两条出价会员信息记录
	 * @param domainnameId 域名Id
	 * @return
	 */
	public List<BidCashInfo> findSecondHighList(String domainnameId);
	/**
	 * 根据域名ID，出价记录ID查询出价表获取大于出价金额的出价记录
	 * @param domainnameId 域名ID
	 * @param bidhistoryId 出价记录ID
	 * @return
	 */
	public List<BidClient> findListForAmounter(String domainnameId, String bidhistoryId);
	
	/**
	 * 根据域名ID查询出价表，获取域名的最新出价
	 * @param domainId 域名ID
	 * @return
	 */
	public Long getMaxAmountById(String domainId);
	
	/**
	 * 根据用户ID和域名状态标记查询出价表、域名表获取用户参与交易的域名信息
	 * @param clientId 用户id
	 * @param status 域名状态标记
	 * @return Map:BidAmount domainnameId name endTime status photo
	 */
	public List<Map<String, Object>> myTransactionsBuyInfo(@Param("clientId")String clientId,@Param("status")String status);
	
	/**
	 * 根据用户ID和域名状态标记查询出价表、域名表获取用户参与交易的域名信息:卖域名
	 * @param clientId 用户id
	 * @param status 域名状态标记
	 * @return Map:BidAmount domainnameId name endTime status photo
	 */
	public List<Map<String, Object>> myTransactionsSellInfo(@Param("clientId")String clientId,@Param("status")String status);
	/**
	 * 根据域名Id查询出价表
	 * @param domainnameId 域名Id
	 * @return
	 */
	public List<BidClient> bidhistoryList(BidClient bidClient);

	/**
	 * 自定义出价-会员信息列表
	 * @param bidClient 出价-会员信息
	 * @return 出价-会员信息列表
	 */
	List<BidClient> customizedFindList(BidClient bidClient);

	/**
	 * 根据出价记录ID更新出价记录代理竞价
	 * @param id 出价记录ID
	 * @param proxyAmount 代理竞价金额
	 * @return 更新操作数
	 */
	int updateProxyAmountById(String id, Long proxyAmount);
	

	/**
	 * 根据域名状态查询该状态域名且已经违约的最高出价信息
	 * @param status 域名状态
	 * @return 
	 */
	List<TransactionInformation> domainnameBuyerAndSeller(@Param("status")String status,@Param("domainnameId")String domainnameId);
	/**
	 * @param domainnameId域名id
	 * @return DyBidhistory 中标实体
	 */
	DyBidhistory getSuccessfulBidder(@Param("domainnameId")String domainnameId);
} 