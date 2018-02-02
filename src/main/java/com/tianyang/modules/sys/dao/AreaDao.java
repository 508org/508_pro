/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.sys.dao;

import com.tianyang.common.persistence.TreeDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author tianyang
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
