/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformAccount;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyPlatformAccountDao;

/**
 * 平台总账户管理Service
 * @author quanyf.fnst
 * @version 2015-12-15
 */
@Service
@Transactional(readOnly = true)
public class DyPlatformAccountService extends CrudService<DyPlatformAccountDao, DyPlatformAccount> {
	/**
	 * 获取平台当前账户总额
	 */
	public long getFinanceAccount(){
		return dao.getFinanceAccount();
	}
	/**
	 * 获取平台当前账户总额
	 */
	public long getIncomeAccount(){
		return dao.getIncomeAccount();
	}
	/**
	 * 更新平台账户总额
	 * @param operate  操作金额
	 * @param type	操作类型
	 * @return
	 */
	public Long updateFinanceAccount(long operate,String type){
		DyPlatformAccount dyPlatformAccount = dao.get(Constant.PLATFORM_ACCOUNT_FINANCE_ID);
		if(StringUtils.equals(Constant.CASHFLOW_OPERATE_WITHDRAW, type)){
			dyPlatformAccount.setTotalFinance(dyPlatformAccount.getTotalFinance() - operate);
		}else if(StringUtils.equals(Constant.CASHFLOW_OPERATE_RECHARGE_ONLINE, type) ||
				StringUtils.equals(Constant.CASHFLOW_OPERATE_RECHARGE_OUTLINE, type)){
			dyPlatformAccount.setTotalFinance(dyPlatformAccount.getTotalFinance() + operate);
		}
		if(dao.updateAccount(dyPlatformAccount) > 0){
			return dyPlatformAccount.getTotalFinance();
		}else{
			return null;
		}
	}
	
	/**
	 * 更新平台收入总额
	 * @param operate  操作金额
	 * @return
	 */
	public Long updateIncomeAccount(long operate){
		DyPlatformAccount dyPlatformAccount = dao.get(Constant.PLATFORM_ACCOUNT_INCOME_ID);
		dyPlatformAccount.setTotalFinance(dyPlatformAccount.getTotalFinance() + operate);
		if(dao.updateAccount(dyPlatformAccount) > 0){
			return dyPlatformAccount.getTotalFinance();
		}else{
			return null;
		}
	}
	
	public DyPlatformAccount get(String id) {
		return super.get(id);
	}
	
	public List<DyPlatformAccount> findList(DyPlatformAccount dyPlatformAccount) {
		return super.findList(dyPlatformAccount);
	}
	
	public Page<DyPlatformAccount> findPage(Page<DyPlatformAccount> page, DyPlatformAccount dyPlatformAccount) {
		return super.findPage(page, dyPlatformAccount);
	}
	
	@Transactional(readOnly = false)
	public void save(DyPlatformAccount dyPlatformAccount) {
		super.save(dyPlatformAccount);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyPlatformAccount dyPlatformAccount) {
		super.delete(dyPlatformAccount);
	}
	
}