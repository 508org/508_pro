/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.tianyang.modules.sys.dao;

import java.util.List;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.sys.entity.WeixinMenu;

/**
 * 微信菜单DAO接口
 * @author konglq
 * @version 2017-05-10
 */
@MyBatisDao
public interface WeixinMenuDao extends CrudDao<WeixinMenu> {
	
	public List<WeixinMenu> getParentIdAndName(WeixinMenu weixinMenu);
	
}