/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Tag;

/**
 * 角色DAO接口
 * @author ThinkGem
 * @version 2013-12-05
 */
@MyBatisDao
public interface TagDao extends CrudDao<Tag> {

	public Tag getByTag(Tag tag);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteTagMenu(Tag tag);

	public int insertTagMenu(Tag tag);

	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return
	 */
	public int deleteTagOffice(Tag tag);

	public int insertTagOffice(Tag tag);

}
