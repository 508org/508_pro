package com.tianyang.modules.pc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyang.common.persistence.Page;
import com.tianyang.common.service.CrudService;
import com.tianyang.modules.pc.entity.PcGroupMobileNumber;
import com.tianyang.modules.pc.dao.PcGroupDao;
import com.tianyang.modules.pc.dao.PcGroupMobileNumberDao;

/**
 * ABC类集团手机号码Service
 * @author 刘笑林
 * @version 2017-06-23
 */
@Service
@Transactional(readOnly = true)
public class PcGroupMobileNumberService extends CrudService<PcGroupMobileNumberDao, PcGroupMobileNumber> {

	
	@Autowired
	private PcGroupMobileNumberDao cGroupMobileNumberDao;
	
	public PcGroupMobileNumber get(String id) {
		return super.get(id);
	}
	
	public List<PcGroupMobileNumber> findList(PcGroupMobileNumber pcGroupMobileNumber) {
		return super.findList(pcGroupMobileNumber);
	}
	
	public Page<PcGroupMobileNumber> findPage(Page<PcGroupMobileNumber> page, PcGroupMobileNumber pcGroupMobileNumber) {
		return super.findPage(page, pcGroupMobileNumber);
	}
	
	@Transactional(readOnly = false)
	public void save(PcGroupMobileNumber pcGroupMobileNumber) {
		super.save(pcGroupMobileNumber);
	}
	
	@Transactional(readOnly = false)
	public void delete(PcGroupMobileNumber pcGroupMobileNumber) {
		super.delete(pcGroupMobileNumber);
	}
	public PcGroupMobileNumber getMobileByMobileNumber(String mobileNumber) {
		return cGroupMobileNumberDao.getMobileByMobileNumber(mobileNumber);
	}
}