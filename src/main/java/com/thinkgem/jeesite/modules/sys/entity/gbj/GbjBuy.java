/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 买标信息Entity
 * 
 * @author snnu
 * @version 2017-12-17
 */
public class GbjBuy extends DataEntity<GbjBuy> {

	private static final long serialVersionUID = 1L;
	private GbjUser user; // 用户ID
	private String user_id; // user_id
	private String realname; // 真实姓名
	private String typeId; // 国标类型
	private String title; // 国标标题
	private String description; // 国标描述
	private Long price; // 预算价格
	private String mobile; // 联系人手机号
	private String tag; // 标签
	private Long upCounts; // 点赞数
	private Long lookCounts; // 查看数
	private Long commentsCounts; // 评论数
	private String frontDelFlag; // 买标信息撤回标记

	public GbjBuy() {
		super();
	}

	public GbjBuy(String id) {
		super(id);
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public GbjBuy(GbjUser userId) {
		this.user = userId;
	}

	/*
	 * @Length(min=1, max=200, message="用户ID长度必须介于 1 和 200 之间") public String
	 * getUser() { return user; }
	 * 
	 * public void setUser(String user) { this.user = user; }
	 */

	@Length(min = 1, max = 200, message = "真实姓名长度必须介于 1 和 200 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Length(min = 0, max = 200, message = "国标类型长度必须介于 0 和 200 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Length(min = 1, max = 200, message = "国标标题长度必须介于 1 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(min = 1, max = 500, message = "国标描述长度必须介于 1 和 500 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull(message = "预算价格不能为空")
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Length(min = 1, max = 200, message = "联系人手机号长度必须介于 1 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(min = 0, max = 50, message = "标签长度必须介于 0 和 50 之间")
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

	public Long getCommentsCounts() {
		return commentsCounts;
	}

	public void setCommentsCounts(Long commentsCounts) {
		this.commentsCounts = commentsCounts;
	}

	@Length(min = 0, max = 1, message = "买标信息撤回标记长度必须介于 0 和 1 之间")
	public String getFrontDelFlag() {
		return frontDelFlag;
	}

	public void setFrontDelFlag(String frontDelFlag) {
		this.frontDelFlag = frontDelFlag;
	}

	// 分页
	/**
	 * 总条数
	 */
	private int totalCount = 0;
	/**
	 * 当前页
	 */
	private int currentPage = 0;
	/**
	 * 每页数据条数
	 */
	private int pageSize = 10;

	public List<GbjBuy> list = new ArrayList<GbjBuy>();

	public List<GbjBuy> getList() {
		return list;
	}

	public void setList(List<GbjBuy> list) {
		this.list = list;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return (totalCount - 1) / pageSize + 1;
	}

}