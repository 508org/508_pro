/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.common.utils.excel.fieldtype;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianyang.common.utils.SpringContextHolder;
import com.tianyang.common.utils.StringUtils;
import com.tianyang.modules.sys.dao.UserDao;
import com.tianyang.modules.sys.entity.Office;
import com.tianyang.modules.sys.entity.User;
import com.tianyang.modules.sys.service.SystemService;
import com.tianyang.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author tianyang
 * @version 2013-03-10
 */
public class UserType {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		/*for (User e : UserUtils.getUser()){
			if (StringUtils.trimToEmpty(val).equals(e.getNo())){
				return e;
			}
		}*/
		User user =new User();
		val=val==null?"":val;
		if(!val.equals(""))
		{
			user=userDao.getByLoginName(new User(null, val));		
		}
		if(user==null)
		{
			user=UserUtils.getUser();
		}

		return user;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((User)val).getNo() != null){
			return ((User)val).getNo();
		}
		return "";
	}
}
