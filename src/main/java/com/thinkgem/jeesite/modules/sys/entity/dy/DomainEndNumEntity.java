package com.thinkgem.jeesite.modules.sys.entity.dy;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 记录某天域名的结拍数量是否到达上限
 * @author wufl.fnst
 *
 */
public class DomainEndNumEntity extends DataEntity<TransactionInformation>{

	Boolean flag = null;//true 已到达域名结拍数量上限
	String date = null;//结拍日期  2016-01-03
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
