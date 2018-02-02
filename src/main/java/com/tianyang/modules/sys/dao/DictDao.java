/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.sys.dao;

import java.util.List;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author tianyang
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
}
