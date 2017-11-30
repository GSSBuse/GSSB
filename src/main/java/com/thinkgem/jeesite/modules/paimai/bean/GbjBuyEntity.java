package com.thinkgem.jeesite.modules.paimai.bean;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;

public class GbjBuyEntity extends DataEntity<GbjBuyEntity>{
	
	
	
	private String user = null;		// 用户ID
	
	private Integer typeId = null;		// 国标类型
	private Long price = null;		// 预算价格
	private Long realprice = null;		// 实际成交价格
	private String linkman = null;		// 联系人
	private String mobile = null;		// 联系人手机号
	private String description = null;		// 国标描述
	private String title = null;		// 国标标题
	
	
	private static final long serialVersionUID = 1L;
	
	public GbjBuyEntity() {
		super();
	}

	public GbjBuyEntity(String id){
		super(id);
	}

	
	/*@Override
	public String toString() {
		return "GbjBuyEntity [user=" + user + ", typeId=" + typeId + ", price=" + price + ", realprice=" + realprice
				+ ", linkman=" + linkman + ", mobile=" + mobile + ", description=" + description + ", title=" + title
				+ "]";
	}*/
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getRealprice() {
		return realprice;
	}
	public void setRealprice(Long realprice) {
		this.realprice = realprice;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
