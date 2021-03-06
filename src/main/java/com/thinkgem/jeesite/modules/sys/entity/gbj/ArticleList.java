package com.thinkgem.jeesite.modules.sys.entity.gbj;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class ArticleList  extends DataEntity<ArticleList>{
	private static final long serialVersionUID = 1L;
	
	private User user;		// 用户ID
	private Integer typeId;		// 国标类型
	private Long price;		// 预算价格
	//private Long realprice;		// 实际成交价格
	private String realname;		// 联系人
	private String mobile;		// 联系人手机号
	private String description;		// 国标描述
	private String title;		// 国标标题
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/*public Long getRealprice() {
		return realprice;
	}
	public void setRealprice(Long realprice) {
		this.realprice = realprice;
	}*/
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
