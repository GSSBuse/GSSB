/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyLevelSetting;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyLevelSettingDao;

/**
 * 加价与保证金增幅Service
 * @author quanyf.fnst
 * @version 2015-10-29
 */
@Service
@Transactional(readOnly = false)
public class DyLevelSettingService extends CrudService<DyLevelSettingDao, DyLevelSetting> {
	public void savelList(List<DyLevelSetting> dyLevelList){
		/*清除所有记录*/
		dao.deleteAll(dyLevelList.get(0).getType());
		for(DyLevelSetting dyLevelSetting : dyLevelList){
			super.save(dyLevelSetting);
		}
	}
	public DyLevelSetting get(String id) {
		return super.get(id);
	}
	
	public List<DyLevelSetting> findList(DyLevelSetting dyLevelSetting) {
		return super.findList(dyLevelSetting);
	}
	
	public Page<DyLevelSetting> findPage(Page<DyLevelSetting> page, DyLevelSetting dyLevelSetting) {
		return super.findPage(page, dyLevelSetting);
	}
	
	@Transactional(readOnly = false)
	public void save(DyLevelSetting dyLevelSetting) {
		super.save(dyLevelSetting);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyLevelSetting dyLevelSetting) {
		super.delete(dyLevelSetting);
	}
	/**
	 * 根据最高出价，获取保证金
	 * @param level  最高出价
	 * @param type	类型
	 * @return
	 */
	public long queryByLevel(long level , String type){
		return dao.queryByLevel(level , type);
	}
}