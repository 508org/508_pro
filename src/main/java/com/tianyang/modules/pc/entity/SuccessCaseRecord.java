/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.pc.entity;

import org.hibernate.validator.constraints.Length;

import com.tianyang.modules.oa.entity.OaNotify;
import com.tianyang.modules.sys.entity.User;

import java.util.Date;

import com.tianyang.common.persistence.DataEntity;

/**
 * 案例查看记录Entity
 * @author tianyang
 * @version 2014-05-16
 */
public class SuccessCaseRecord extends DataEntity<SuccessCaseRecord> {
	
	private static final long serialVersionUID = 1L;
	private SuccessCase successCase;		// 案例通告ID
	private User user;		    // 接受人
	private String readFlag;    // 阅读标记（0：未读；1：已读）
	private Date readDate;		// 阅读时间
	
	private String likeFlag;    // 点赞    已点赞 未点赞
	private String comment;	    // 评论区
	
	
	
	public SuccessCaseRecord() {
		super();
	}

	public SuccessCaseRecord(String id){
		super(id);
	}
	
	public SuccessCaseRecord(SuccessCase successCase){
		this.successCase = successCase;
	}
	
	
	
	public String getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(String likeFlag) {
		this.likeFlag = likeFlag;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public SuccessCase getSuccessCase() {
		return successCase;
	}

	public void setSuccessCase(SuccessCase successCase) {
		this.successCase = successCase;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1, message="阅读标记（0：未读；1：已读）长度必须介于 0 和 1 之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
}