/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.files.dao;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.files.entity.Files;

/**
 * 上传文件DAO接口
 * @author zbc
 * @version 2017-03-06
 */
@MyBatisDao
public interface FilesDao extends CrudDao<Files> {
	
}