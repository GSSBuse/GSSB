/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.wx.dao.WxTagDao;
import com.thinkgem.jeesite.modules.wx.entity.WxTag;

/**
 * 企业微信标签管理Service
 * @author shenzb.fnst
 * @version 2015-08-17
 */
@Service
@Transactional(readOnly = true)
public class WxTagService extends CrudService<WxTagDao, WxTag> {

	@Autowired
	private WxTagDao wxTagDao;
	
	@Autowired
	private UserDao userDao;

	public WxTag get(String id) {
		return super.get(id);
	}
	
	public List<WxTag> findList(WxTag wxTag) {
		return super.findList(wxTag);
	}
	
	public Page<WxTag> findPage(Page<WxTag> page, WxTag wxTag) {
		return super.findPage(page, wxTag);
	}
	
	@Transactional(readOnly = false)
	public void save(WxTag wxTag) {
		super.save(wxTag);
	}
	
	@Transactional(readOnly = false)
	public void delete(WxTag wxTag) {
		super.delete(wxTag);
	}

	/**
	 * 无分页查询成员列表
	 * @param user
	 * @return
	 */
	public List<User> findUser(WxTag wxTag) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		wxTag.getSqlMap().put("dsf", dataScopeFilter(wxTag.getCurrentUser(), "o", "a"));
		List<User> list = userDao.findListByTag(wxTag);
		return list;
	}

	/**
	 * 标签分配 -- 从标签中移除用户
	 * @param wxTag
	 * @param user
	 * @return Boolean
	 */
	@Transactional(readOnly = false)
	public Boolean outUserInTag(WxTag wxTag, User user) {
		
		int delCnt = wxTagDao.outUserInTag(wxTag.getId(), user.getId());
		return delCnt > 0;
	}

	/**
	 * 标签分配
	 * @param wxTag
	 * @param user
	 * @return User
	 */
	@Transactional(readOnly = false)
	public User assignUserToTag(WxTag wxTag, User user) {
		if (user == null){
			return null;
		}
//		List<String> roleIds = user.getRoleIdList();
//		if (roleIds.contains(wxTag.getId())) {
//			return null;
//		}
//		user.getRoleList().add(role);
		wxTagDao.assignUserToTag(wxTag.getId(), user.getId());
		return user;
	}
	
}