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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessage;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessagegroup;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyMessagegroupDao;

/**
 * 群发消息列表管理Service
 * @author quanyf.fnst
 * @version 2015-10-19
 */
@Service
@Transactional(readOnly = true)
public class DyMessagegroupService extends CrudService<DyMessagegroupDao, DyMessagegroup> {
	@Autowired
	private DyMessageService dyMessageService;   //推送消息管理Service
	
	/**
	 * 更新消息群组
	 */
	@Transactional(readOnly = false)
	public void saveMessageGroup(DyMessagegroup dyMessagegroup){
		//首先保存图文消息下的所有消息
		List<DyMessage> dyMessageList = dyMessagegroup.getDyMessageList();
		List<DyMessage> newMessageList = Lists.newArrayList();
		for (DyMessage dyMessage : dyMessageList) {
			//新消息
			if (StringUtils.isNotBlank(dyMessage.getId()) && StringUtils.isNotBlank(dyMessage.getTitle())
					&& StringUtils.isNoneBlank(dyMessage.getUrlId())&& StringUtils.isNoneBlank(dyMessage.getPicture())) {
				newMessageList.add(dyMessage);
			}
		}
		String dyMessageIds = dyMessageService.saveList(newMessageList);
		dyMessagegroup.setMessageList(dyMessageIds);
		dyMessagegroup.setDyMessageList(newMessageList);
		//保存群发消息列表
		this.save(dyMessagegroup);
	}
	
	/**
	 * 将发送状态改为已发送
	 * @param id
	 */
	public void updateTypeSend(String id){
		dao.updateTypeSend(id);
	}
	
	public DyMessagegroup get(String id) {
		return super.get(id);
	}
	
	public List<DyMessagegroup> findList(DyMessagegroup dyMessagegroup) {
		return super.findList(dyMessagegroup);
	}
	
	public Page<DyMessagegroup> findPage(Page<DyMessagegroup> page, DyMessagegroup dyMessagegroup) {
		return super.findPage(page, dyMessagegroup);
	}
	
	@Transactional(readOnly = false)
	public void save(DyMessagegroup dyMessagegroup) {
		super.save(dyMessagegroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyMessagegroup dyMessagegroup) {
		super.delete(dyMessagegroup);
	}
	
}