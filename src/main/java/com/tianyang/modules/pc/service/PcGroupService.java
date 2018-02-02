package com.tianyang.modules.pc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyang.common.persistence.Page;
import com.tianyang.common.service.CrudService;
import com.tianyang.modules.pc.entity.PcClusteringMarket;
import com.tianyang.modules.pc.entity.PcGroup;
import com.tianyang.modules.pc.dao.PcGroupDao;
import com.tianyang.modules.sys.dao.UserDao;

/**
 * 集团信息Service
 * @author 刘笑林
 * @version 2017-06-08
 */
@Service
@Transactional(readOnly = true)
public class PcGroupService extends CrudService<PcGroupDao, PcGroup> {

	@Autowired
	private PcGroupDao pcGroupDao;
	
	public PcGroup get(String id) {
		return super.get(id);
	}
	
	public List<PcGroup> findList(PcGroup pcGroup) {
		return super.findList(pcGroup);
	}
	
	public Page<PcGroup> findPage(Page<PcGroup> page, PcGroup pcGroup) {
		return super.findPage(page, pcGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(PcGroup pcGroup) {
		super.save(pcGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(PcGroup pcGroup) {
		super.delete(pcGroup);
	}
	public PcGroup getGroupByGroupNumber(String groupNumber) {
		return pcGroupDao.getGroupByGroupNumber(groupNumber);
	}
	public List<PcGroup> findOrgCount(PcGroup pcGroup) {
		return pcGroupDao.findOrgCount(pcGroup);
	}
	public List<PcGroup> findGridCount(PcGroup pcGroup) {
		return pcGroupDao.findGridCount(pcGroup);
	}
	public List<PcGroup> findManagerCount(PcGroup pcGroup) {
		return pcGroupDao.findManagerCount(pcGroup);
	}
}