/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wx.entity.WxTag;

/**
 * 企业微信标签管理DAO接口
 * @author shenzb.fnst
 * @version 2015-08-17
 */
@MyBatisDao
public interface WxTagDao extends CrudDao<WxTag> {

	/**
	 * 标签分配 -- 从标签中移除用户
	 * @param tagId
	 * @param userId
	 * @return Boolean
	 */
	int outUserInTag(@Param("tagId")String tagId, @Param("userId")String userId);

	/**
	 * 标签分配
	 * @param tagId
	 * @param userId
	 * @return User
	 */
	void assignUserToTag(@Param("tagId")String tagId, @Param("userId")String userId);

}