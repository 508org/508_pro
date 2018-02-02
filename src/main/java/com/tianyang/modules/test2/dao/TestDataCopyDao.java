/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.test2.dao;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.test2.entity.TestDataCopy;

/**
 * 测试DAO接口
 * @author zbc
 * @version 2017-02-06
 */
@MyBatisDao
public interface TestDataCopyDao extends CrudDao<TestDataCopy> {
	
}