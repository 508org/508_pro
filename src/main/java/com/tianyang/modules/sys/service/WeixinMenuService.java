/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.tianyang.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyang.common.persistence.Page;
import com.tianyang.common.service.CrudService;
import com.tianyang.modules.sys.entity.WeixinMenu;
import com.tianyang.modules.sys.dao.WeixinMenuDao;

/**
 * 微信菜单Service
 * @author konglq
 * @version 2017-05-10
 */
@Service
@Transactional(readOnly = true)
public class WeixinMenuService extends CrudService<WeixinMenuDao, WeixinMenu> {

	@Autowired
	private WeixinMenuDao weixinMenuDao;
	
	public WeixinMenu get(String id) {
		return super.get(id);
	}
	
	public List<WeixinMenu> findList(WeixinMenu weixinMenu) {
		return super.findList(weixinMenu);
	}
	public List<WeixinMenu> getParentIdAndName(WeixinMenu weixinMenu) {
		return weixinMenuDao.getParentIdAndName(weixinMenu);
	}
	
	public Page<WeixinMenu> findPage(Page<WeixinMenu> page, WeixinMenu weixinMenu) {
		return super.findPage(page, weixinMenu);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinMenu weixinMenu) {
		super.save(weixinMenu);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinMenu weixinMenu) {
		super.delete(weixinMenu);
	}
	
}