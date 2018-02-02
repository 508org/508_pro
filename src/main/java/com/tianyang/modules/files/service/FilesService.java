/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.files.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyang.common.persistence.Page;
import com.tianyang.common.service.CrudService;
import com.tianyang.modules.files.entity.Files;
import com.tianyang.modules.files.dao.FilesDao;

/**
 * 上传文件Service
 * @author zbc
 * @version 2017-03-06
 */
@Service
@Transactional(readOnly = true)
public class FilesService extends CrudService<FilesDao, Files> {

	public Files get(String id) {
		return super.get(id);
	}
	
	public List<Files> findList(Files files) {
		return super.findList(files);
	}
	
	public Page<Files> findPage(Page<Files> page, Files files) {
		return super.findPage(page, files);
	}
	
	@Transactional(readOnly = false)
	public void save(Files files) {
		super.save(files);
	}
	
	@Transactional(readOnly = false)
	public void delete(Files files) {
		super.delete(files);
	}
	
}