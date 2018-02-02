/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.files.entity;

import org.hibernate.validator.constraints.Length;

import com.tianyang.common.persistence.DataEntity;

/**
 * 上传文件Entity
 * @author zbc
 * @version 2017-03-06
 */
public class Files extends DataEntity<Files> {
	
	private static final long serialVersionUID = 1L;
	private String url;		// 文件链接
	
	public Files() {
		super();
	}

	public Files(String id){
		super(id);
	}

	@Length(min=0, max=255, message="文件链接长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}