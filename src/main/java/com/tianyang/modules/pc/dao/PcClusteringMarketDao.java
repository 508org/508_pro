package com.tianyang.modules.pc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.pc.entity.PcClusteringMarket;

/**
 * 聚类市场信息DAO接口
 * @author 刘笑林
 * @version 2017-06-08
 */
@MyBatisDao
public interface PcClusteringMarketDao extends CrudDao<PcClusteringMarket> {
	public PcClusteringMarket getClusteringMarket(@Param("attributedGrid") String attributedGrid,@Param("clusterMarketType") String clusterMarketType,@Param("shopName") String shopName);
	
	public List<PcClusteringMarket> findOrgCount(PcClusteringMarket pcClusteringMarket);
	
	public List<PcClusteringMarket> findGridCount(PcClusteringMarket pcClusteringMarket);
	
	public List<PcClusteringMarket> findManagerCount(PcClusteringMarket pcClusteringMarket);
}