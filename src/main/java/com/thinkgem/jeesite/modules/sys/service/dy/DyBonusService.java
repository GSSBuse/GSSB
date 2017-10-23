/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBonus;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyBonusDao;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BonusClient;
import com.thinkgem.jeesite.modules.wx.entity.domainname.ShareOrSecondBonus;

/**
 * 红包佣金信息Service
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class DyBonusService extends CrudService<DyBonusDao, DyBonus> {
	/**
	 * 根据域名Id，获取该域名的所有红包记录
	 * @param domainnameId 域名Id
	 * @return
	 */
	public List<BonusClient> getBonusListByDomainId(String domainnameId){
		return dao.getBonusListByDomainId(domainnameId);
	}
	public DyBonus get(String id) {
		return super.get(id);
	}
	
	public List<DyBonus> findList(DyBonus dyBonus) {
		return super.findList(dyBonus);
	}
	
	public List<ShareOrSecondBonus> findShareOrsecondBonusList(DyBonus dyBonus) {
		return dao.findShareOrsecondBonusList(dyBonus);
	}
	
	public Page<DyBonus> findPage(Page<DyBonus> page, DyBonus dyBonus) {
		return super.findPage(page, dyBonus);
	}
	
	@Transactional(readOnly = false)
	public void save(DyBonus dyBonus) {
		super.save(dyBonus);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyBonus dyBonus) {
		super.delete(dyBonus);
	}
	
}