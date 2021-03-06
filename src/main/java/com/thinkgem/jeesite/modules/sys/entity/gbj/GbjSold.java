/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 卖标信息管理Entity
 * @author snnu
 * @version 2017-12-16
 */
public class GbjSold extends DataEntity<GbjSold> {
	
	private static final long serialVersionUID = 1L;
	private GbjUser user;		// 用户ID
	private String user_id;		// user_id
	private String realname;		// 真实姓名
	private String typeId;		// 国标类型
	private String title;		// 国标标题
	private String description;		// 国标描述
	private Long price;		// 预算价格
	private String mobile;		// 联系人手机号
	private String tag;		// 标签
	private Long upCounts;		// 点赞数
	private Long lookCounts;		// 查看数
	private String frontDelFlag;		// 卖标信息撤回标记
	private Long commentsCounts;		// 评论数
	
	public GbjSold() {
		super();
	}

	public GbjSold(String id){
		super(id);
	}
	
	public GbjUser getUser() {
		return user;
	}
	public void getUser(GbjUser user) {
		this.user = user;
	}
	
	public void setUser(GbjUser user) {
		this.user = user;
	}
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	
	public GbjSold(GbjUser userId) {
		this.user = userId;
	}

	/*@NotNull(message="用户ID不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
	
	@Length(min=1, max=200, message="真实姓名长度必须介于 1 和 200 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=11, message="国标类型长度必须介于 0 和 11 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=1, max=200, message="国标标题长度必须介于 1 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=500, message="国标描述长度必须介于 1 和 500 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotNull(message="预算价格不能为空")
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	@Length(min=1, max=200, message="联系人手机号长度必须介于 1 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=200, message="标签长度必须介于 0 和 200 之间")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Long getUpCounts() {
		return upCounts;
	}

	public void setUpCounts(Long upCounts) {
		this.upCounts = upCounts;
	}
	
	public Long getLookCounts() {
		return lookCounts;
	}

	public void setLookCounts(Long lookCounts) {
		this.lookCounts = lookCounts;
	}
	
	@Length(min=0, max=1, message="卖标信息撤回标记长度必须介于 0 和 1 之间")
	public String getFrontDelFlag() {
		return frontDelFlag;
	}

	public void setFrontDelFlag(String frontDelFlag) {
		this.frontDelFlag = frontDelFlag;
	}
	
	public Long getCommentsCounts() {
		return commentsCounts;
	}

	public void setCommentsCounts(Long commentsCounts) {
		this.commentsCounts = commentsCounts;
	}
	
}