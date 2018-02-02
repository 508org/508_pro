/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.pc.dao;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.pc.entity.SuccessCase;

/**
 * 通知通告DAO接口
 * @author tianyang
 * @version 2014-05-16
 */
@MyBatisDao
public interface SuccessCaseDao extends CrudDao<SuccessCase> {
	
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(SuccessCase oaNotify);
	
}