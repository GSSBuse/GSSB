/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;

/**
 * 会员信息管理DAO接口
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@MyBatisDao
public interface DyClientDao extends CrudDao<DyClient> {
	
	/**
	 * 根据会员ID更新会员名
	 * @param id 会员ID
	 * @param Name 会员名
	 * @return
	 */
	public int updateName(@Param("id")String id, @Param("name")String name);
	
	/**
	 * 保存用户email状态的更改
	 * @param id
	 * @param emailFlag
	 * @return
	 */
	public int saveEmailFlag (@Param("id")String id,@Param("emailFlag")String emailFlag);
	/**
	 * 根据会员ID更新会员微信号
	 * @param id 会员ID
	 * @param wx 会员微信号
	 * @return
	 */
	public int updateWx(@Param("id")String id, @Param("wx")String wx);
	
	/**
	 * 根据会员ID更新会员QQ号
	 * @param id 会员ID
	 * @param qq 会员QQ号
	 * @return
	 */
	public int updateQQ(@Param("id")String id, @Param("qq")String qq);
	
	/**
	 * 根据会员ID更新会员手机号
	 * @param id 会员ID
	 * @param mobile 会员手机号
	 * @return
	 */
	public int updateMobile(@Param("id")String id, @Param("mobile")String mobile);
	
	/**
	 * 根据会员ID更新会员email
	 * @param id 会员ID
	 * @param email 会员email
	 * @return
	 */
	public int updateEmail(@Param("id")String id, @Param("email")String email);	
	
	/**
	 * 根据会员ID更新会员默认支付方式
	 * @param id 会员ID
	 * @param 
	 * @return
	 */
	public int updateDefault_income_expense(@Param("id")String id, @Param("default_income_expense")String default_income_expense);	
	
	/**
	 * 通过OPENID获取用户信息
	 * 
	 * @param openid
	 * @return
	 */
	public DyClient getByOpenid(String openid);
	
	/**
	 * 根据米友号获取会员信息
	 * @param dyid 米友号
	 * @return
	 */
	public DyClient getByDyid(@Param("dyid")String dyid);
	
	/**
	 * 查询负担最小的经纪人
	 * 
	 * @return
	 */
	public User getRandomBroker();

	/**
	 * 获取新的 米友ID
	 * 
	 * @return
	 */
	public String getNewDyId();
	/**
	 * 身份未认证的会员数量
	 * @return
	 */
	public int unmarkCount();
	/**
	 * 根据经纪人Id，获取身份未认证的会员数量
	 * @param brokerId 经纪人Id
	 * @return
	 */
	public int unmarkCountBroker(String brokerId);
	/**
	 * 根据域名id，获取关联的会员id
	 * @param domainIds 域名id列表
	 * @return
	 */
	public List<DyClient> findLinkClientList(@Param("idList")List<String> domainIdList);
	/**
	 * 查询数据库中是否存在米友号
	 * @param dyid 米友号
	 * @return
	 */
	public int hasDyid(String dyid);
	
	/**
	 * 获取某个经纪人所属的所有会员
	 * @param brokerId
	 * @return
	 */
	public List<DyClient> getClientForBroker(@Param("brokerId") String brokerId);
}