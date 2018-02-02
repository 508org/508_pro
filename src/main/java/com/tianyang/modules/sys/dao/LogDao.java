/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.sys.dao;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author tianyang
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
