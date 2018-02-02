/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.test.dao;

import com.tianyang.common.persistence.TreeDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * @author tianyang
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}