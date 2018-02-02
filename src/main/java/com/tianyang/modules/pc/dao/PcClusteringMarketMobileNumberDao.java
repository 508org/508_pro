package com.tianyang.modules.pc.dao;

import org.apache.ibatis.annotations.Param;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.pc.entity.PcClusteringMarketMobileNumber;

/**
 * 聚类市场手机号码DAO接口
 * @author 刘笑林
 * @version 2017-06-23
 */
@MyBatisDao
public interface PcClusteringMarketMobileNumberDao extends CrudDao<PcClusteringMarketMobileNumber> {
	public PcClusteringMarketMobileNumber getMobileByMobileNumber(@Param("mobileNumber") String mobileNumber);
}