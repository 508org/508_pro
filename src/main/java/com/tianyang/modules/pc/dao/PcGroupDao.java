package com.tianyang.modules.pc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.pc.entity.PcGroup;
import com.tianyang.modules.sys.entity.User;

/**
 * 集团信息DAO接口
 * @author 刘笑林
 * @version 2017-06-08
 */
@MyBatisDao
public interface PcGroupDao extends CrudDao<PcGroup> {
	public PcGroup getGroupByGroupNumber(@Param("groupNumber") String groupNumber);
	
	public List<PcGroup> findOrgCount(PcGroup pcGroup);
	
	public List<PcGroup> findGridCount(PcGroup pcGroup);
	
	public List<PcGroup> findManagerCount(PcGroup pcGroup);
}