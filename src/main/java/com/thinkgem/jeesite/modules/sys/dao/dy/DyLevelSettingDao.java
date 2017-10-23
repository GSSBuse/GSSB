/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;


import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyLevelSetting;

/**
 * 加价与保证金增幅DAO接口
 * @author quanyf.fnst
 * @version 2015-10-29
 */
@MyBatisDao
public interface DyLevelSettingDao extends CrudDao<DyLevelSetting> {
	/**
	 * 清除所有记录
	 * @return
	 */
	public int deleteAll(@Param("type")String type);
	/**
	 * 根据最高出价，获取保证金
	 * @param level  最高出价
	 * @param type	类型
	 * @return
	 */
	public long queryByLevel(long level , String type);
}