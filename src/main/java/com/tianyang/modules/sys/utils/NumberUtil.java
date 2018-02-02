package com.tianyang.modules.sys.utils;

import java.text.NumberFormat;

/**
 * String工具类
 * 
 * @author 孙宇
 * 
 */
public class NumberUtil {

	/**
	 * 补位操作
	 * 
	 * 例：fillNumber(1,4) = 0001
	 * 
	 * @param str
	 * @param params
	 * @return
	 */
	public static String fillNumber(Integer intParam, Integer number) {
		if(number == null) {
			return String.valueOf(intParam);
		}
		NumberFormat formatter = NumberFormat.getNumberInstance();     
		formatter.setMinimumIntegerDigits(number);     
		formatter.setGroupingUsed(false);     
		return formatter.format(intParam);
	}

}
