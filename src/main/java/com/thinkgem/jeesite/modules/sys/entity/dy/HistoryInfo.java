package com.thinkgem.jeesite.modules.sys.entity.dy;

import java.util.Date;
import com.thinkgem.jeesite.common.persistence.DataEntity;

public class HistoryInfo extends DataEntity<HistoryInfo> {
	String domainnameId = null;
	String status = null;
	String name = null;
	String sellClientId = null;
	String sellNickName = null;
	Date sellDate = null;
	String bidAmount = null;
	String buyClientId = null;
	String buyNickName = null;
	public String getDomainnameId() {
		return domainnameId;
	}
	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSellClientId() {
		return sellClientId;
	}
	public void setSellClientId(String sellClientId) {
		this.sellClientId = sellClientId;
	}
	public String getSellNickName() {
		return sellNickName;
	}
	public void setSellNickName(String sellNickName) {
		this.sellNickName = sellNickName;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public String getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(String bidAmount) {
		this.bidAmount = bidAmount;
	}
	public String getBuyClientId() {
		return buyClientId;
	}
	public void setBuyClientId(String buyClientId) {
		this.buyClientId = buyClientId;
	}
	public String getBuyNickName() {
		return buyNickName;
	}
	public void setBuyNickName(String buyNickName) {
		this.buyNickName = buyNickName;
	}
	
}
