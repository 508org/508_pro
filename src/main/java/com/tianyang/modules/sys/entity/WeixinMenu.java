/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.tianyang.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tianyang.common.persistence.TreeEntity;

/**
 * 微信菜单Entity
 * @author konglq
 * @version 2017-05-10
 */
public class WeixinMenu extends TreeEntity<WeixinMenu>  {
	
	private static final long serialVersionUID = 1L;
	private String actionType;		// action_type
	private Date createdatetime;		// createdatetime
	private String iconcls;		// iconcls
	private String keywx;		// keywx
	private String menuType;		// menu_type
	private String name;		// name
	private Date updatedatetime;		// updatedatetime
	private String url;		// url
	//private WeixinMenu parent;		// parent_id
	private String selectid;		// selectid
	
	public WeixinMenu() {
		super();
	}

	public WeixinMenu(String id){
		super(id);
	}

	@Length(min=0, max=50, message="action_type长度必须介于 0 和 50 之间")
	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	
	@Length(min=0, max=100, message="iconcls长度必须介于 0 和 100 之间")
	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	
	@Length(min=0, max=128, message="keywx长度必须介于 0 和 128 之间")
	public String getKeywx() {
		return keywx;
	}

	public void setKeywx(String keywx) {
		this.keywx = keywx;
	}
	
	@Length(min=0, max=50, message="menu_type长度必须介于 0 和 50 之间")
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	@Length(min=0, max=40, message="name长度必须介于 0 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedatetime() {
		return updatedatetime;
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}
	
	@Length(min=0, max=256, message="url长度必须介于 0 和 256 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonBackReference
	public WeixinMenu getParent() {
		return parent;
	}

	public void setParent(WeixinMenu parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=36, message="selectid长度必须介于 0 和 36 之间")
	public String getSelectid() {
		return selectid;
	}

	public void setSelectid(String selectid) {
		this.selectid = selectid;
	}
	
}