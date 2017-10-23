/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyClientDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyFinanceDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyArticle;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessage;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 会员信息管理Service
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class DyClientService extends CrudService<DyClientDao, DyClient> implements Constant {
	@Autowired
	private UserDao userDao;
	@Autowired 
	private DyFinanceDao dyFinanceDao;
	@Autowired 
	private DyArticleService dyArticleService;

	@Cacheable(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#id)")
	public DyClient get(String id) {
		return super.get(id);
	}
	
	public List<DyClient> findList(DyClient dyClient) {
		return super.findList(dyClient);
	}
	
	public Page<DyClient> findPage(Page<DyClient> page, DyClient dyClient) {
		return super.findPage(page, dyClient);
	}
	
	public synchronized String getNewDyId() {
		return dao.getNewDyId();
	}
	

	/*
	 * 使用@CachePut只能更新缓存值类型跟本方法返回值相同的缓存，而对于缓存值类型不同的方法则无法更新其缓存，
	 * 比如本方法返回SystemConfig对象，通过@CachePut只能更新值为SystemConfig对象的缓存，而无法更新值为String或List的缓存，
	 * 这时可以通过@CacheEvict将其缓存清除，下次从缓存中查不到将直接从数据库查询最新数据。
	 */
	@Caching(evict = {
	// 清除querySimpleSingleConfigs方法的缓存
	// @CacheEvict(value = PlatformPrivateConstants.CACHE_NAME_CONFIG, key = "'SIMPLE_SINGLE_CONFIGS'"),
	// 清除queryMultiConfigs方法的缓存
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#dyClient.id)") },
	//--------------------------------------------------------------------------------------------------------------------
	put = {
			// 更新querySingleConfig(long configId)方法的缓存
			@CachePut(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#dyClient.id)")}
	)
	@Transactional(readOnly = false)
	public void save(DyClient dyClient) {
		if (dyClient.getIsNewRecord()){
			String dyId = getNewDyId();
			if (StringUtils.isBlank(dyId)) {
				dyId = "10000";
			} if (dyId.contains("4")) {
				dyId = dyId.replaceAll("4", "5");
			}
			dyClient.setDyid(dyId);
		}
		super.save(dyClient);
	}
	
	@Transactional(readOnly = false)
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#dyClient.id)")
	public void delete(DyClient dyClient) {
		super.delete(dyClient);
	}
	
	public DyClient getByOpenid(String openid) {
		return dao.getByOpenid(openid);
	}
	/**
	 * 根据米友号获取会员信息
	 * @param dyid 米友号
	 * @return
	 */
	public DyClient getByDyid(String dyid){
		return dao.getByDyid(dyid);
	}
	
	/**
	 * 修改会员姓名
	 * @param id会员id
	 * @param name需修改的会员姓名
	 * @return
	 */
	@Transactional(readOnly = false)
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#id)")
	public int changeName(String id,String name) {
		int flag = dao.updateName(id, name);
		return flag;
	}
	
	/**
	 * 保存用户email状态的更改
	 * @param id
	 * @param emailFlag
	 * @return
	 */
	@Transactional(readOnly = false)
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#id)")
	public int saveEmailFlag (String id,String emailFlag){
		int flag = dao.saveEmailFlag(id, emailFlag);
		return flag;
	}
	/**
	 * 修改会员微信号
	 * @param id会员id
	 * @param wx需修改的会员微信号
	 * @return
	 */
	@Transactional(readOnly = false)
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#id)")
	public int changeWx(String id,String wx) {
		int flag = dao.updateWx(id, wx);
		return flag;
	}	
	
	/**
	 * 修改会员QQ号
	 * @param id会员id
	 * @param qq需修改的会员QQ号
	 * @return
	 */
	@Transactional(readOnly = false)
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#id)")
	public int changeQQ(String id,String qq) {
		int flag = dao.updateQQ(id, qq);
		return flag;
	}
	
	/**
	 * 修改会员手机号
	 * @param id会员id
	 * @param mobile需修改的会员手机号
	 * @return
	 */
	@Transactional(readOnly = false)
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#id)")
	public int changeMobile(String id,String mobile) {
		int flag = dao.updateMobile(id, mobile);
		return flag;
	}	
	
	/**
	 * 修改会员email
	 * @param id会员id
	 * @param email需修改的会员email
	 * @return
	 */
	@Transactional(readOnly = false)
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#id)")
	public int changeEmail(String id,String email) {
		int flag = dao.updateEmail(id, email);
		return flag;
	}
	
	/**
	 * 修改会员默认支付方式
	 * @param id会员id
	 * @param 
	 * @return
	 */
	@Transactional(readOnly = false)
	@CacheEvict(value = PAGE_DATA_CACHE, key = "'dyclient_byid_'.concat(#id)")
	public int changeDefault_income_expense(String id,String default_income_expense) {
		int flag = dao.updateDefault_income_expense(id, default_income_expense);
		return flag;
	}
	
	/**
	 * 查询会员经济人信息
	 * @param id经纪人id
	 * @return
	 */
	@Transactional(readOnly = true)
	public User viewBroker(String id) {
		User broker = userDao.get(id);
		return broker;
	}
	
	/**
	 * 查询会员经济人信息
	 * @param id经纪人id
	 * @return
	 */
	@Transactional(readOnly = true)
	public User getRandomBroker() {
		User broker = dao.getRandomBroker();
		return broker;
	}
	/**
	 * 身份未认证的会员数量
	 * @return
	 */
	public int unmarkCount(){
		return dao.unmarkCount();
	}
	/**
	 * 根据经纪人Id，获取身份未认证的会员数量
	 * @param brokerId 经纪人Id
	 * @return
	 */
	public int unmarkCountBroker(String brokerId){
		return dao.unmarkCountBroker(brokerId);
	}
	/**
	 * 根据消息群组，获取关联的会员id
	 * @param dyMessageList 消息群组
	 * @return
	 */
	public List<DyClient> findLinkClientList(List<DyMessage> dyMessageList){
		List<String> domainIdList = new ArrayList<String>();	//域名ID列表
		for(DyMessage dyMessage : dyMessageList){
			if(StringUtils.equals(Constant.MESSAGE_URLTYPE_DOMAIN, dyMessage.getUrlType())){
				domainIdList.add(dyMessage.getUrlId());
			}else{
				DyArticle dyArticle = dyArticleService.get(dyMessage.getUrlId());
				if(StringUtils.isNotBlank(dyArticle.getDescription())){
					domainIdList.add(dyArticle.getDescription());
				}
			}
		}
		List<DyClient> list = new ArrayList<DyClient>();
		if(domainIdList.size() == 0){		//如果没有域名列表，则群发
			list = dao.findList(new DyClient());
		}else{
			list = dao.findLinkClientList(domainIdList);
		}
		return list;
	}
	/**
	 * 获取会员的所有相关信息（包括经济人信息）
	 * @param clientId	会员Id
	 * @return
	 */
	public DyClient getAllInfo(String clientId){
		DyClient dyClient = this.get(clientId);
		dyClient.setBroker(UserUtils.get(dyClient.getBrokerId()));
		DyFinance dyFinance = new DyFinance();
		dyFinance.setClientId(clientId);
		dyClient.setDyFinance(dyFinanceDao.get(dyFinance));
		return dyClient;
	}
	/**
	 * 查询数据库中是否存在米友号
	 * @param dyid 米友号
	 * @return
	 */
	public int hasDyid(String dyid){
		return dao.hasDyid(dyid);
	}
	
	/**检查用户的个人信息是否填写完善
	 * @param dyClient 
	 * @return true信息完善 false信息不完善
	 */
	public boolean checkPersonalInfo( DyClient dyClient){
		
		if(StringUtils.isBlank(dyClient.getName())){
			return false;
		}
		if(!EMAIL_FLAG_1.equals(dyClient.getEmailFlag()) || StringUtils.isBlank(dyClient.getEmail())){
			return false;
		}
		if(StringUtils.isBlank(dyClient.getMobile())){
			return false;
		}
		if(StringUtils.isBlank(dyClient.getQq()) && StringUtils.isBlank(dyClient.getWx())){
			return false;
		}
		if(StringUtils.isBlank(dyClient.getDefaultIncomeExpense())){
			return false;
		}
		if(!AUTHENTICATION_MARK_1.equals(dyClient.getAuthenticationMark()) || StringUtils.isBlank(dyClient.getAuthenticationPositiveImageUrl()) || StringUtils.isBlank(dyClient.getAuthenticationNegativeImageUrl())){
			return false;
		}
		if(StringUtils.isBlank(dyClient.getPayPassword())){
			return false;
		}
		return true;
	}
	/**
	 * 获取某个经纪人所属的所有会员
	 * @param brokerId
	 * @return
	 */
	public List<DyClient> getClientForBroker(String brokerId){
		return dao.getClientForBroker(brokerId);
	}
}
