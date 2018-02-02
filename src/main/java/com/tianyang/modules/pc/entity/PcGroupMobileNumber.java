package com.tianyang.modules.pc.entity;

import org.hibernate.validator.constraints.Length;

import com.tianyang.common.persistence.DataEntity;
import com.tianyang.common.utils.excel.annotation.ExcelField;

/**
 * ABC类集团手机号码Entity
 * @author 刘笑林
 * @version 2017-06-23
 */
public class PcGroupMobileNumber extends DataEntity<PcGroupMobileNumber> {
	
	private static final long serialVersionUID = 1L;
	private String mobileNumber;		// 手机号码
	private String operators;		// 运营商
	private String groupNumber;		// 集团编号
	
	public PcGroupMobileNumber() {
		super();
	}

	public PcGroupMobileNumber(String id){
		super(id);
	}
	@ExcelField(title="手机号码", align=2, sort=20)
	@Length(min=0, max=50, message="手机号码长度必须介于 0 和 50 之间")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@ExcelField(title="运营商", align=2, sort=30)
	@Length(min=0, max=64, message="运营商长度必须介于 0 和 64 之间")
	public String getOperators() {
		return operators;
	}

	public void setOperators(String operators) {
		this.operators = operators;
	}
	@ExcelField(title="集团编号", align=2, sort=10)
	@Length(min=0, max=64, message="集团编号长度必须介于 0 和 64 之间")
	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	
}