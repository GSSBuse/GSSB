/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 卖标信息评论Entity
 * @author 管理员
 * @version 2017-12-18
 */
public class GbjUserSoldComments extends DataEntity<GbjUserSoldComments> {
	
	private static final long serialVersionUID = 1L;
	private String user;		// 用户ID
	private String parentId;		// 父ID
	private String childId;		// 子ID
	private String comment;		// 评论内容
	private Date commentTime;		// 评论时间
	
	public GbjUserSoldComments() {
		super();
	}

	public GbjUserSoldComments(String id){
		super(id);
	}

	@Length(min=1, max=64, message="用户ID长度必须介于 1 和 64 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="父ID长度必须介于 0 和 64 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=0, max=64, message="子ID长度必须介于 0 和 64 之间")
	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}
	
	@Length(min=1, max=200, message="评论内容长度必须介于 1 和 200 之间")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="评论时间不能为空")
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
}