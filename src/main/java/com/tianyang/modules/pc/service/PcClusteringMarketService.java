package com.tianyang.modules.pc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyang.common.persistence.Page;
import com.tianyang.common.service.CrudService;
import com.tianyang.modules.pc.entity.PcClusteringMarket;
import com.tianyang.modules.pc.dao.PcClusteringMarketDao;
import com.tianyang.modules.pc.dao.PcGroupDao;
import com.tianyang.modules.sys.entity.User;
import com.tianyang.modules.sys.utils.UserUtils;

/**
 * 聚类市场信息Service
 * @author 刘笑林
 * @version 2017-06-08
 */
@Service
@Transactional(readOnly = true)
public class PcClusteringMarketService extends CrudService<PcClusteringMarketDao, PcClusteringMarket> {

	@Autowired
	private PcClusteringMarketDao pcClusteringMarketDao;
	
	public PcClusteringMarket get(String id) {
		return super.get(id);
	}
	
	public List<PcClusteringMarket> findList(PcClusteringMarket pcClusteringMarket) {
		return super.findList(pcClusteringMarket);
	}
	
	public Page<PcClusteringMarket> findPage(Page<PcClusteringMarket> page, PcClusteringMarket pcClusteringMarket) {
		return super.findPage(page, pcClusteringMarket);
	}
	
	@Transactional(readOnly = false)
	public void save(PcClusteringMarket pcClusteringMarket) {
		super.save(pcClusteringMarket);
	}
	
	@Transactional(readOnly = false)
	public void delete(PcClusteringMarket pcClusteringMarket) {
		super.delete(pcClusteringMarket);
	}
	public PcClusteringMarket getClusteringMarket(String attributedGrid,String clusterMarketType,String shopName) {
		return pcClusteringMarketDao.getClusteringMarket(attributedGrid,clusterMarketType,shopName);
	}
	public List<PcClusteringMarket> findOrgCount(PcClusteringMarket pcClusteringMarket) {
		return pcClusteringMarketDao.findOrgCount(pcClusteringMarket);
	}
	public List<PcClusteringMarket> findGridCount(PcClusteringMarket pcClusteringMarket) {
		return pcClusteringMarketDao.findGridCount(pcClusteringMarket);
	}
	public List<PcClusteringMarket> findManagerCount(PcClusteringMarket pcClusteringMarket) {
		return pcClusteringMarketDao.findManagerCount(pcClusteringMarket);
	}
}