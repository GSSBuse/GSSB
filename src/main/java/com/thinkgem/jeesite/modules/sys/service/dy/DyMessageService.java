/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessage;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessagegroup;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyArticleDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyDomainnameDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyMessageDao;

/**
 * 推送消息管理Service
 * @author quanyf.fnst
 * @version 2015-10-20
 */
@Service
@Transactional(readOnly = false)
public class DyMessageService extends CrudService<DyMessageDao, DyMessage> {
	@Autowired
	private DyArticleDao dyArticleDao;
	@Autowired
	private DyDomainnameDao dyDomainnameDao;
	/**
	 * 插入一组消息
	 * @param dyMessageList  消息列表
	 */
	@Transactional(readOnly = false)
	public String saveList(List<DyMessage> dyMessageList){
		StringBuffer dyMessageIds = new StringBuffer();
		for(DyMessage dyMessage : dyMessageList){
			super.save(dyMessage);
			dyMessageIds.append(dyMessage.getId()).append(",");
		}
		return dyMessageIds.toString();
	}
	/**
	 * 根据一组id获取实体，id以逗号分隔开
	 * @param dyMessageIds
	 * @return
	 */
	public List<DyMessage> getListByIds(String dyMessageIds){
		List<DyMessage> dyMessageList= new ArrayList<DyMessage>();
		if(dyMessageIds.contains(",")){
			String[] dyMessages = dyMessageIds.split(",");
			for(String dyMessageId : dyMessages){
				if (StringUtils.isNotBlank(dyMessageId)) {
					DyMessage d = new DyMessage();
					d = super.get(dyMessageId);
					if(StringUtils.equals(Constant.MESSAGE_URL_ARTILE, d.getUrlType())){
						d.setUrlName(dyArticleDao.get(d.getUrlId()).getTitle());
					}else{
						d.setUrlName(dyDomainnameDao.get(d.getUrlId()).getName());
					}
					dyMessageList.add(d);
				}
			}
		}else{
			dyMessageList.add(super.get(dyMessageIds));
		}
		return dyMessageList;
	}
	public DyMessage get(String id) {
		return super.get(id);
	}
	
	public List<DyMessage> findList(DyMessage dyMessage) {
		return super.findList(dyMessage);
	}
	
	public Page<DyMessage> findPage(Page<DyMessage> page, DyMessage dyMessage) {
		return super.findPage(page, dyMessage);
	}
	
	@Transactional(readOnly = false)
	public void save(DyMessage dyMessage) {
		super.save(dyMessage);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyMessage dyMessage) {
		super.delete(dyMessage);
	}
	
}