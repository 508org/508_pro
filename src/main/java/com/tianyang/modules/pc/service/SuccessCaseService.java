/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.pc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyang.common.persistence.Page;
import com.tianyang.common.service.CrudService;
import com.tianyang.modules.pc.dao.SuccessCaseDao;
import com.tianyang.modules.pc.dao.SuccessCaseRecordDao;
import com.tianyang.modules.pc.entity.SuccessCase;
import com.tianyang.modules.pc.entity.SuccessCaseRecord;

/**
 * 案例Service
 * @author tianyang
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class SuccessCaseService extends CrudService<SuccessCaseDao, SuccessCase> {

	@Autowired
	private SuccessCaseRecordDao successCaseRecordDao;

	public SuccessCase get(String id) {
		SuccessCase entity = dao.get(id);
		return entity;
	}
	
	/**
	 * 获取案例发送记录
	 * @param successCase
	 * @return
	 */
	public SuccessCase getRecordList(SuccessCase successCase) {
		successCase.setSuccessCaseRecordList(successCaseRecordDao.findList(new SuccessCaseRecord(successCase)));
		return successCase;
	}
	
	public Page<SuccessCase> find(Page<SuccessCase> page, SuccessCase successCase) {
		successCase.setPage(page);
		page.setList(dao.findList(successCase));
		return page;
	}
	
	/**
	 * 获取案例数目
	 * @param successCase
	 * @return
	 */
	public Long findCount(SuccessCase successCase) {
		return dao.findCount(successCase);
	}
	
	@Transactional(readOnly = false)
	public void save(SuccessCase successCase) {
		super.save(successCase);
		
	}
	
	@Transactional(readOnly = false)
	public void saveSuccessCaseRecord(SuccessCaseRecord successCaseRecord) {
		
		if (successCaseRecord.getIsNewRecord()){
			successCaseRecord.preInsert();
			successCaseRecordDao.insert(successCaseRecord);
			
		}else{
			successCaseRecord.preUpdate();
			successCaseRecordDao.update(successCaseRecord);
		}
		
	}
	
	/**
	 * 更新阅读状态
	 */
	@Transactional(readOnly = false)
	public void updateReadFlag(SuccessCase successCase) {
		SuccessCaseRecord successCaseRecord = new SuccessCaseRecord(successCase);
		successCaseRecord.setUser(successCaseRecord.getCurrentUser());
		successCaseRecord.setReadDate(new Date());
		successCaseRecord.setReadFlag("1");
		successCaseRecordDao.update(successCaseRecord);
	}
}