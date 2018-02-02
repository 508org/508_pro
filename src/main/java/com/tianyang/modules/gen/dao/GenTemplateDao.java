/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.gen.dao;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.gen.entity.GenTemplate;

/**
 * 代码模板DAO接口
 * @author tianyang
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTemplateDao extends CrudDao<GenTemplate> {
	
}
