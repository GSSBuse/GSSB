/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 悬赏信息管理Entity
 * @author snnu
 * @version 2017-12-17
 */
public class GbjReward extends DataEntity<GbjReward> {
	
	private static final long serialVersionUID = 1L;
	private GbjUser user;		// 用户名ID
	private String realname;		// 真实姓名
	private String typeId;		// 国标类型
	private String title;		// 标题
	private String description;		// 起名需求
	private Long price;		// 打赏金额
	private String mobile;		// 手机号码
	private String tag;		// 标签
	private Long upCounts;		// 点赞数
	private Long lookCounts;		// 查看数
	private String frontDelFlag;		// 撤回标记
	private Long commentsCounts;		// 评论数
	
	public GbjReward() {
		super();
	}

	public GbjReward(String id){
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
	
	
	public GbjReward(GbjUser userId) {
		this.user = userId;
	}

	/*@Length(min=1, max=64, message="用户名ID长度必须介于 1 和 64 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}*/
	
	@Length(min=1, max=200, message="真实姓名长度必须介于 1 和 200 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=64, message="国标类型长度必须介于 0 和 64 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=1, max=500, message="标题长度必须介于 1 和 500 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotNull(message="打赏金额不能为空")
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	@Length(min=1, max=200, message="手机号码长度必须介于 1 和 200 之间")
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
	
	@Length(min=0, max=1, message="撤回标记长度必须介于 0 和 1 之间")
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