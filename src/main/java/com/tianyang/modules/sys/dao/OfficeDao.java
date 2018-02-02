/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.sys.dao;

import java.util.List;

import com.tianyang.common.persistence.TreeDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author tianyang
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	/**
	 * 通过父id得到对象
	 * @param entity
	 * @return
	 */
	public Office getByParentId(Office office);
	/**
	 * 通过微信id得到对象
	 * @param entity
	 * @return
	 */
	public Office getOrganizationByweixinId(Office office);
	/**
	 * 获取最大机构weixinID加上1
	 */
	public List getOrganizationId();
}
