package com.tianyang.modules.pc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyang.common.persistence.Page;
import com.tianyang.common.service.CrudService;
import com.tianyang.modules.pc.entity.PcClusteringMarketMobileNumber;
import com.tianyang.modules.pc.entity.PcGroupMobileNumber;
import com.tianyang.modules.pc.dao.PcClusteringMarketMobileNumberDao;
import com.tianyang.modules.pc.dao.PcGroupMobileNumberDao;

/**
 * 聚类市场手机号码Service
 * @author 刘笑林
 * @version 2017-06-23
 */
@Service
@Transactional(readOnly = true)
public class PcClusteringMarketMobileNumberService extends CrudService<PcClusteringMarketMobileNumberDao, PcClusteringMarketMobileNumber> {

	
	@Autowired
	private PcClusteringMarketMobileNumberDao pcClusteringMarketMobileNumberDao;
	
	public PcClusteringMarketMobileNumber get(String id) {
		return super.get(id);
	}
	
	public List<PcClusteringMarketMobileNumber> findList(PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber) {
		return super.findList(pcClusteringMarketMobileNumber);
	}
	
	public Page<PcClusteringMarketMobileNumber> findPage(Page<PcClusteringMarketMobileNumber> page, PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber) {
		return super.findPage(page, pcClusteringMarketMobileNumber);
	}
	
	@Transactional(readOnly = false)
	public void save(PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber) {
		super.save(pcClusteringMarketMobileNumber);
	}
	
	@Transactional(readOnly = false)
	public void delete(PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber) {
		super.delete(pcClusteringMarketMobileNumber);
	}
	public PcClusteringMarketMobileNumber getMobileByMobileNumber(String mobileNumber) {
		return pcClusteringMarketMobileNumberDao.getMobileByMobileNumber(mobileNumber);
	}
}