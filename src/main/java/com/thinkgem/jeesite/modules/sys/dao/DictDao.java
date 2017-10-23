/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	/**
	 * 查找以dy开头的字典
	 * @param dict 字典实体
	 * @return 字典列表
	 */
	public List<Dict> findConstantPage(Dict dict);
	/**
	 * 根据类型获取设定值(常量读取用)
	 * @param type
	 * @return 设定的常量
	 */
	public String getValueByType(String type);
}
