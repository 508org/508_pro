/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.member.dao;

import com.tianyang.common.persistence.CrudDao;
import com.tianyang.common.persistence.annotation.MyBatisDao;
import com.tianyang.modules.member.entity.Member;

/**
 * 关注会员信息DAO接口
 * @author zbc
 * @version 2017-03-06
 */
@MyBatisDao
public interface MemberDao extends CrudDao<Member> {
	
}