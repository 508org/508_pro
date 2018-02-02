package com.tianyang.modules.pc.entity;

import com.tianyang.modules.sys.entity.Office;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tianyang.modules.sys.entity.User;
import com.tianyang.common.persistence.DataEntity;
import com.tianyang.common.utils.excel.annotation.ExcelField;

/**
 * 集团信息Entity
 * @author 刘笑林
 * @version 2017-06-08
 */
public class PcGroup extends DataEntity<PcGroup> {
	
	private static final long serialVersionUID = 1L;
	private Office organization;		// 区县
	private String groupNumber;		// 集团编号
	private String groupName;		// 集团名称
	private String groupState;		// 营业状态
	private String customerLevel;		// 客户级别
	private String firstIndustryCategory;		// 一级行业类别
	private String secondIndustryCategory;		// 二级行业类别     
	private String orgParentName;		// 上级单位名称
	
	private Office attributedGrid;		// 归属网格
	private String enterpriseType;		// 企业类型
	private String unitActualAddress;		// 单位实际地址
	private String identificationNumber;		// 证件号码
	private String registeredCapital;		// 注册资金
	private String taxRevenue;		// 税收
	private String numberOneName;		// 一把手姓名
	private String numberOnePhone;		// 一把手电话
	private String chargeKeyPersonName;		// 分管信息关键人姓名
	private String chargeKeyPersonPhone;		// 分管信息关键人手机号码
	private String informationKeyPersonName;		// 信息科关键人姓名
	private String informationKeyPersonPhone;		// 信息科关键人手机号码	
	private String otherKeyPersonName;		// 其他关键人姓名
	private String otherKeyPersonPhone;		// 其他关键人手机号码
	private String contactsName;		// 联系人姓名
	private String contactsPhone;		// 联系人手机号码
	private String phoneUsageEmployeesNumber;		// 员工数量
	private String phoneUsageMobileNumber;		// 号码数量
	private String phoneUsageMobileMonthlyIncome;		// 月收入
	private Date phoneUsageMobileExpirationDate;		// 我网协议到期时间
	private String unifiedPaymentNumber;		// 其中统一付费成员数量 
	private String unifiedPaymentIncome;		// 统一付费收入（元） 
	private String phoneUsageUnicomNumber;		// 号码数量
	private String phoneUsageUnicomMonthlyIncome;		// 月收入
	private Date phoneUsageUnicomExpirationDate;		// 联通协议到期时间
	private String phoneUsageTelecomNumber;		// 号码数量
	private String phoneUsageTelecomMonthlyIncome;		// 月收入
	private Date phoneUsageTelecomExpirationDate;		// 电信协议到期时间
	private String dataLineMobileNumber;		// 条数
	private String dataLineMobileMonthlyIncome;		// 月收入
	private Date dataLineMobileExpirationDate;		// 到期时间
	private String dataLineUnicomNumber;		// 条数
	private String dataLineUnicomMonthlyIncome;		// 月收入
	private Date dataLineUnicomExpirationDate;		// 到期时间
	private String dataLineTelecomNumber;		// 条数
	private String dataLineTelecomMonthlyIncome;		// 月收入
	private Date dataLineTelecomExpirationDate;		// 到期时间
	private String internetLineMobileNumber;		// 条数
	private String internetLineMobileMonthlyIncome;		// 月收入
	private Date internetLineMobileExpirationDate;		// 到期时间
	private String internetLineUnicomNumber;		// 条数
	private String internetLineUnicomMonthlyIncome;		// 月收入
	private Date internetLineUnicomExpirationDate;		// 到期时间
	private String internetLineTelecomNumber;		// 条数
	private String internetLineTelecomMonthlyIncome;		// 月收入
	private Date internetLineTelecomExpirationDate;		// 到期时间
	private String voiceLineMobileNumber;		// 条数
	private String voiceLineMobileMonthlyIncome;		// 月收入
	private Date voiceLineMobileExpirationDate;		// 到期时间
	private String voiceLineUnicomNumber;		// 条数
	private String voiceLineUnicomMonthlyIncome;		// 月收入
	private Date voiceLineUnicomExpirationDate;		// 到期时间
	private String voiceLineTelecomNumber;		// 条数
	private String voiceLineTelecomMonthlyIncome;		// 月收入
	private Date voiceLineTelecomExpirationDate;		// 到期时间
	
	private String imsMobileNumber;		// 门数
	private String imsMobileMonthlyIncome;		// 月收入
	private Date imsMobileExpirationDate;		// 到期时间
	private String imsUnicomNumber;		// 门数
	private String imsUnicomMonthlyIncome;		// 月收入
	private Date imsUnicomExpirationDate;		// 到期时间
	private String imsTelecomNumber;		// 门数
	private String imsTelecomMonthlyIncome;		// 月收入
	private Date imsTelecomExpirationDate;		// 到期时间
	
	private String otherProductsMobileBusinessName;		// 业务名称
	private String otherProductsMobileMonthlyIncome;		// 月收入
	private Date otherProductsMobileExpirationDate;		// 到期时间
	private String otherProductsUnicomBusinessName;		// 业务名称
	private String otherProductsUnicomMonthlyIncome;		// 月收入
	private Date otherProductsUnicomExpirationDate;		// 到期时间
	private String otherProductsTelecomBusinessName;		// 业务名称
	private String otherProductsTelecomMonthlyIncome;		// 月收入
	private Date otherProductsTelecomExpirationDate;		// 到期时间
	private String followUpBusinessName;		// 正在跟进或将来要跟进的业务名称
	private String followUpSituation;		// 具体跟进情况
	//private User customerManagerNumber;		// 客户经理
	private String customerManagerNumber;//客户经理工号
	private String customerManagerName;//客户经理姓名
	
	private String sort;		// 排序
	
	/**
	 * 查询或者统计使用
	 * @return
	 */
	private String registeredCapitalTo;		// 注册资金
	private String taxRevenueTo;		// 税收
	private String marketShare;//市场占有率
	private String marketShareTo;//市场占有率
	private String phoneUsageEmployeesNumberTo;		// 员工数量
	private String phoneUsageMobileMonthlyIncomeTo;		// 月收入
	private Date phoneUsageMobileExpirationDateTo;		// 我网协议到期时间
	private String phoneUsageUnicomMonthlyIncomeTo;		// 月收入
	private Date phoneUsageUnicomExpirationDateTo;		// 联通协议到期时间
	private String phoneUsageTelecomMonthlyIncomeTo;		// 月收入
	private Date phoneUsageTelecomExpirationDateTo;		// 电信协议到期时间
	private String dataLineMobileMonthlyIncomeTo;		// 月收入
	private Date dataLineMobileExpirationDateTo;		// 到期时间
	private String dataLineUnicomMonthlyIncomeTo;		// 月收入
	private Date dataLineUnicomExpirationDateTo;		// 到期时间
	private String dataLineTelecomMonthlyIncomeTo;		// 月收入
	private Date dataLineTelecomExpirationDateTo;		// 到期时间
	private String internetLineMobileMonthlyIncomeTo;		// 月收入
	private Date internetLineMobileExpirationDateTo;		// 到期时间
	private String internetLineUnicomMonthlyIncomeTo;		// 月收入
	private Date internetLineUnicomExpirationDateTo;		// 到期时间
	private String internetLineTelecomMonthlyIncomeTo;		// 月收入
	private Date internetLineTelecomExpirationDateTo;		// 到期时间
	private String voiceLineMobileMonthlyIncomeTo;		// 月收入
	private Date voiceLineMobileExpirationDateTo;		// 到期时间
	private String voiceLineUnicomMonthlyIncomeTo;		// 月收入
	private Date voiceLineUnicomExpirationDateTo;		// 到期时间
	private String voiceLineTelecomMonthlyIncomeTo;		// 月收入
	private Date voiceLineTelecomExpirationDateTo;		// 到期时间
	private String imsMobileMonthlyIncomeTo;		// 月收入
	private Date imsMobileExpirationDateTo;		// 到期时间
	private String imsUnicomMonthlyIncomeTo;		// 月收入
	private Date imsUnicomExpirationDateTo;		// 到期时间
	private String imsTelecomMonthlyIncomeTo;		// 月收入
	private Date imsTelecomExpirationDateTo;		// 到期时间
	private String otherProductsMobileMonthlyIncomeTo;		// 月收入
	private Date otherProductsMobileExpirationDateTo;		// 到期时间
	private String otherProductsUnicomMonthlyIncomeTo;		// 月收入
	private Date otherProductsUnicomExpirationDateTo;		// 到期时间
	private String otherProductsTelecomMonthlyIncomeTo;		// 月收入
	private Date otherProductsTelecomExpirationDateTo;		// 到期时间
	//统计
	private String groupCount;//集团数量
	private String employeesNumberCount;//员工数量
	private String marketShareCountOur;//市场占有率
	private String marketShareCountOther;//市场占有率
	private String linePermeabilityOur;//专线渗透率
	private String linePermeabilityOther;//专线渗透率
	private String imsPermeabilityOur;//IMS渗透率
	private String imsPermeabilityOther;//IMS渗透率
	private String customerWarningOur;//客户协议到期预警
	private String customerWarningOther;//客户协议到期预警
	private String lineWarningOur;//专线协议到期预警
	private String lineWarningOther;//专线协议到期预警
	private String imsWarningOur;//IMS协议到期预警
	private String imsWarningOther;//IMS协议到期预警
	private String otherWarningOur;//其他产品协议到期预警
	private String otherWarningOther;//其他产品协议到期预警
	
	private String incomeType;//月收入类型
	private String operators;//运营商
	private String allMonthlyIncome;//月收入
	private String allMonthlyIncomeTo;//月收入
	
	/**
	 * add--klq 
	 */
	//物联网卡
	//移动
	private String wlwkYdBusinessName;//业务名称
	private String wlwkYdIncome;      //月收入
	private String wlwkYdIncomeTo;    //月收入至-虚拟值--传值用
	private Date   wlwkYdDateTo;      //到期时间
	//联通
	private String wlwkLtBusinessName;//业务名称
	private String wlwkLtIncome;      //月收入
	private String wlwkLtIncomeTo;    //月收入至-虚拟值--传值用
	private Date   wlwkLtDateTo;      //到期时间
	//电信
	private String wlwkDxBusinessName;//业务名称
	private String wlwkDxIncome;      //月收入
	private String wlwkDxIncomeTo;    //月收入至-虚拟值--传值用
	private Date   wlwkDxDateTo;      //到期时间
	
	
	//IDC业务使用情况
	//移动
	private String iDCYdBusinessName;//业务名称
	private String iDCYdZf;          //资费
	private String iDCYdZfTo;        //资费至-虚拟值--传值用
	private Date   iDCYdDateTo;      //到期时间
	//联通
	private String iDCLtBusinessName;//业务名称
	private String iDCLtZf;          //资费
	private String iDCLtZfTo;        //资费至-虚拟值--传值用
	private Date   iDCLtDateTo;      //到期时间
	//其他单位（如电信、广电、华为、英特力等）
	private String iDCQtBusinessName;//业务名称
	private String iDCQtAddr;        //IDC提供单位
	private String iDCQtZf;          //资费
	private String iDCQtZfTo;        //资费至-虚拟值--传值用
	private Date   iDCQtDateTo;      //到期时间
	
	

	

	public PcGroup() {
		super();
	}

	public PcGroup(String id){
		super(id);
	}
	@ExcelField(title="区县", align=2, sort=10)
	public Office getOrganization() {
		return organization;
	}

	public void setOrganization(Office organization) {
		this.organization = organization;
	}
	
	@Length(min=0, max=50, message="集团编号长度必须介于 0 和 50 之间")
	@ExcelField(title="集团编号", align=2, sort=20)
	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	
	@Length(min=0, max=50, message="集团名称长度必须介于 0 和 50 之间")
	@ExcelField(title="集团名称", align=2, sort=30)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Length(min=0, max=50, message="客户级别长度必须介于 0 和 50 之间")
	@ExcelField(title="客户级别", align=2, sort=40, dictType="customer_level")
	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	
	@Length(min=0, max=50, message="行业类别长度必须介于 0 和 50 之间")
	@ExcelField(title="行业类别", align=2, sort=50, dictType="first_industry_category")
	public String getFirstIndustryCategory() {
		return firstIndustryCategory;
	}

	public void setFirstIndustryCategory(String firstIndustryCategory) {
		this.firstIndustryCategory = firstIndustryCategory;
	}
	
	@Length(min=0, max=50, message="二级行业类别长度必须介于 0 和 50 之间")
	public String getSecondIndustryCategory() {
		return secondIndustryCategory;
	}

	public void setSecondIndustryCategory(String secondIndustryCategory) {
		this.secondIndustryCategory = secondIndustryCategory;
	}
	
	@ExcelField(title="归属网格", align=2, sort=70)
	public Office getAttributedGrid() {
		return attributedGrid;
	}

	public void setAttributedGrid(Office attributedGrid) {
		this.attributedGrid = attributedGrid;
	}
	
	@Length(min=0, max=50, message="企业类型长度必须介于 0 和 50 之间")
	@ExcelField(title="企业类型", align=2, sort=80, dictType="enterprise_type")
	public String getEnterpriseType() {
		return enterpriseType;
	}

	

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	
	@Length(min=0, max=255, message="单位实际地址长度必须介于 0 和 255 之间")
	@ExcelField(title="单位实际地址", align=2, sort=90)
	public String getUnitActualAddress() {
		return unitActualAddress;
	}

	public void setUnitActualAddress(String unitActualAddress) {
		this.unitActualAddress = unitActualAddress;
	}
	
	@Length(min=0, max=50, message="证件号码长度必须介于 0 和 50 之间")
	@ExcelField(title="证件号码", align=2, sort=100)
	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	
	@Length(min=0, max=50, message="注册资金长度必须介于 0 和 50 之间")
	@ExcelField(title="注册资金", align=2, sort=110)
	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	
	@Length(min=0, max=50, message="税收长度必须介于 0 和 50 之间")
	@ExcelField(title="税收", align=2, sort=120)
	public String getTaxRevenue() {
		return taxRevenue;
	}

	public void setTaxRevenue(String taxRevenue) {
		this.taxRevenue = taxRevenue;
	}
	
	@Length(min=0, max=50, message="一把手姓名长度必须介于 0 和 50 之间")
	@ExcelField(title="一把手姓名", align=2, sort=130)
	public String getNumberOneName() {
		return numberOneName;
	}

	public void setNumberOneName(String numberOneName) {
		this.numberOneName = numberOneName;
	}
	
	@Length(min=0, max=50, message="一把手电话长度必须介于 0 和 50 之间")
	@ExcelField(title="一把手电话", align=2, sort=140)
	public String getNumberOnePhone() {
		return numberOnePhone;
	}

	public void setNumberOnePhone(String numberOnePhone) {
		this.numberOnePhone = numberOnePhone;
	}
	
	@Length(min=0, max=50, message="分管信息关键人姓名长度必须介于 0 和 50 之间")
	@ExcelField(title="分管信息关键人姓名", align=2, sort=150)
	public String getChargeKeyPersonName() {
		return chargeKeyPersonName;
	}

	public void setChargeKeyPersonName(String chargeKeyPersonName) {
		this.chargeKeyPersonName = chargeKeyPersonName;
	}
	
	@Length(min=0, max=50, message="分管信息关键人手机号码长度必须介于 0 和 50 之间")
	@ExcelField(title="分管信息关键人手机号码", align=2, sort=160)
	public String getChargeKeyPersonPhone() {
		return chargeKeyPersonPhone;
	}

	public void setChargeKeyPersonPhone(String chargeKeyPersonPhone) {
		this.chargeKeyPersonPhone = chargeKeyPersonPhone;
	}
	@ExcelField(title="信息科关键人姓名", align=2, sort=161)
	public String getInformationKeyPersonName() {
		return informationKeyPersonName;
	}

	public void setInformationKeyPersonName(String informationKeyPersonName) {
		this.informationKeyPersonName = informationKeyPersonName;
	}
	@ExcelField(title="信息科关键人手机号码", align=2, sort=162)
	public String getInformationKeyPersonPhone() {
		return informationKeyPersonPhone;
	}

	public void setInformationKeyPersonPhone(String informationKeyPersonPhone) {
		this.informationKeyPersonPhone = informationKeyPersonPhone;
	}
	@Length(min=0, max=50, message="其他关键人姓名长度必须介于 0 和 50 之间")
	@ExcelField(title="其他关键人姓名", align=2, sort=170)
	public String getOtherKeyPersonName() {
		return otherKeyPersonName;
	}

	

	public void setOtherKeyPersonName(String otherKeyPersonName) {
		this.otherKeyPersonName = otherKeyPersonName;
	}
	
	@Length(min=0, max=50, message="其他关键人手机号码长度必须介于 0 和 50 之间")
	@ExcelField(title="其他关键人手机号码", align=2, sort=180)
	public String getOtherKeyPersonPhone() {
		return otherKeyPersonPhone;
	}

	public void setOtherKeyPersonPhone(String otherKeyPersonPhone) {
		this.otherKeyPersonPhone = otherKeyPersonPhone;
	}
	
	@Length(min=0, max=50, message="联系人姓名长度必须介于 0 和 50 之间")
	@ExcelField(title="联系人姓名", align=2, sort=190)
	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	
	@Length(min=0, max=50, message="联系人手机号码长度必须介于 0 和 50 之间")
	@ExcelField(title="联系人手机号码", align=2, sort=200)
	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	
	@Length(min=0, max=50, message="员工数量长度必须介于 0 和 50 之间")
	@ExcelField(title="员工数量", align=2, sort=210)
	public String getPhoneUsageEmployeesNumber() {
		return phoneUsageEmployeesNumber;
	}

	public void setPhoneUsageEmployeesNumber(String phoneUsageEmployeesNumber) {
		this.phoneUsageEmployeesNumber = phoneUsageEmployeesNumber;
	}
	
	@Length(min=0, max=50, message="号码数量长度必须介于 0 和 50 之间")
	@ExcelField(title="号码数量", align=2, sort=220)
	public String getPhoneUsageMobileNumber() {
		return phoneUsageMobileNumber;
	}

	public void setPhoneUsageMobileNumber(String phoneUsageMobileNumber) {
		this.phoneUsageMobileNumber = phoneUsageMobileNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=230)
	public String getPhoneUsageMobileMonthlyIncome() {
		return phoneUsageMobileMonthlyIncome;
	}

	public void setPhoneUsageMobileMonthlyIncome(String phoneUsageMobileMonthlyIncome) {
		this.phoneUsageMobileMonthlyIncome = phoneUsageMobileMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="我网协议到期时间", align=2, sort=240)
	public Date getPhoneUsageMobileExpirationDate() {
		return phoneUsageMobileExpirationDate;
	}

	public void setPhoneUsageMobileExpirationDate(Date phoneUsageMobileExpirationDate) {
		this.phoneUsageMobileExpirationDate = phoneUsageMobileExpirationDate;
	}
	@ExcelField(title="统一付费成员数量", align=2, sort=241)
	

	public String getUnifiedPaymentNumber() {
		return unifiedPaymentNumber;
	}
	public void setUnifiedPaymentNumber(String unifiedPaymentNumber) {
		this.unifiedPaymentNumber = unifiedPaymentNumber;
	}
	@ExcelField(title="统一付费收入", align=2, sort=242)
	

	public String getUnifiedPaymentIncome() {
		return unifiedPaymentIncome;
	}
	public void setUnifiedPaymentIncome(String unifiedPaymentIncome) {
		this.unifiedPaymentIncome = unifiedPaymentIncome;
	}
	@Length(min=0, max=50, message="号码数量长度必须介于 0 和 50 之间")
	@ExcelField(title="号码数量", align=2, sort=250)
	public String getPhoneUsageUnicomNumber() {
		return phoneUsageUnicomNumber;
	}

	public void setPhoneUsageUnicomNumber(String phoneUsageUnicomNumber) {
		this.phoneUsageUnicomNumber = phoneUsageUnicomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=260)
	public String getPhoneUsageUnicomMonthlyIncome() {
		return phoneUsageUnicomMonthlyIncome;
	}

	public void setPhoneUsageUnicomMonthlyIncome(String phoneUsageUnicomMonthlyIncome) {
		this.phoneUsageUnicomMonthlyIncome = phoneUsageUnicomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="联通协议到期时间", align=2, sort=270)
	public Date getPhoneUsageUnicomExpirationDate() {
		return phoneUsageUnicomExpirationDate;
	}

	public void setPhoneUsageUnicomExpirationDate(Date phoneUsageUnicomExpirationDate) {
		this.phoneUsageUnicomExpirationDate = phoneUsageUnicomExpirationDate;
	}
	
	@Length(min=0, max=50, message="号码数量长度必须介于 0 和 50 之间")
	@ExcelField(title="号码数量", align=2, sort=280)
	public String getPhoneUsageTelecomNumber() {
		return phoneUsageTelecomNumber;
	}

	public void setPhoneUsageTelecomNumber(String phoneUsageTelecomNumber) {
		this.phoneUsageTelecomNumber = phoneUsageTelecomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=290)
	public String getPhoneUsageTelecomMonthlyIncome() {
		return phoneUsageTelecomMonthlyIncome;
	}

	public void setPhoneUsageTelecomMonthlyIncome(String phoneUsageTelecomMonthlyIncome) {
		this.phoneUsageTelecomMonthlyIncome = phoneUsageTelecomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="电信协议到期时间", align=2, sort=300)
	public Date getPhoneUsageTelecomExpirationDate() {
		return phoneUsageTelecomExpirationDate;
	}

	public void setPhoneUsageTelecomExpirationDate(Date phoneUsageTelecomExpirationDate) {
		this.phoneUsageTelecomExpirationDate = phoneUsageTelecomExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=310)
	public String getDataLineMobileNumber() {
		return dataLineMobileNumber;
	}

	public void setDataLineMobileNumber(String dataLineMobileNumber) {
		this.dataLineMobileNumber = dataLineMobileNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=320)
	public String getDataLineMobileMonthlyIncome() {
		return dataLineMobileMonthlyIncome;
	}

	public void setDataLineMobileMonthlyIncome(String dataLineMobileMonthlyIncome) {
		this.dataLineMobileMonthlyIncome = dataLineMobileMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=330)
	public Date getDataLineMobileExpirationDate() {
		return dataLineMobileExpirationDate;
	}

	public void setDataLineMobileExpirationDate(Date dataLineMobileExpirationDate) {
		this.dataLineMobileExpirationDate = dataLineMobileExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=340)
	public String getDataLineUnicomNumber() {
		return dataLineUnicomNumber;
	}

	public void setDataLineUnicomNumber(String dataLineUnicomNumber) {
		this.dataLineUnicomNumber = dataLineUnicomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=350)
	public String getDataLineUnicomMonthlyIncome() {
		return dataLineUnicomMonthlyIncome;
	}

	public void setDataLineUnicomMonthlyIncome(String dataLineUnicomMonthlyIncome) {
		this.dataLineUnicomMonthlyIncome = dataLineUnicomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=360)
	public Date getDataLineUnicomExpirationDate() {
		return dataLineUnicomExpirationDate;
	}

	public void setDataLineUnicomExpirationDate(Date dataLineUnicomExpirationDate) {
		this.dataLineUnicomExpirationDate = dataLineUnicomExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=370)
	public String getDataLineTelecomNumber() {
		return dataLineTelecomNumber;
	}

	public void setDataLineTelecomNumber(String dataLineTelecomNumber) {
		this.dataLineTelecomNumber = dataLineTelecomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=380)
	public String getDataLineTelecomMonthlyIncome() {
		return dataLineTelecomMonthlyIncome;
	}

	public void setDataLineTelecomMonthlyIncome(String dataLineTelecomMonthlyIncome) {
		this.dataLineTelecomMonthlyIncome = dataLineTelecomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=390)
	public Date getDataLineTelecomExpirationDate() {
		return dataLineTelecomExpirationDate;
	}

	public void setDataLineTelecomExpirationDate(Date dataLineTelecomExpirationDate) {
		this.dataLineTelecomExpirationDate = dataLineTelecomExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=400)
	public String getInternetLineMobileNumber() {
		return internetLineMobileNumber;
	}

	public void setInternetLineMobileNumber(String internetLineMobileNumber) {
		this.internetLineMobileNumber = internetLineMobileNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=410)
	public String getInternetLineMobileMonthlyIncome() {
		return internetLineMobileMonthlyIncome;
	}

	public void setInternetLineMobileMonthlyIncome(String internetLineMobileMonthlyIncome) {
		this.internetLineMobileMonthlyIncome = internetLineMobileMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=420)
	public Date getInternetLineMobileExpirationDate() {
		return internetLineMobileExpirationDate;
	}

	public void setInternetLineMobileExpirationDate(Date internetLineMobileExpirationDate) {
		this.internetLineMobileExpirationDate = internetLineMobileExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=430)
	public String getInternetLineUnicomNumber() {
		return internetLineUnicomNumber;
	}

	public void setInternetLineUnicomNumber(String internetLineUnicomNumber) {
		this.internetLineUnicomNumber = internetLineUnicomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=440)
	public String getInternetLineUnicomMonthlyIncome() {
		return internetLineUnicomMonthlyIncome;
	}

	public void setInternetLineUnicomMonthlyIncome(String internetLineUnicomMonthlyIncome) {
		this.internetLineUnicomMonthlyIncome = internetLineUnicomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=450)
	public Date getInternetLineUnicomExpirationDate() {
		return internetLineUnicomExpirationDate;
	}

	public void setInternetLineUnicomExpirationDate(Date internetLineUnicomExpirationDate) {
		this.internetLineUnicomExpirationDate = internetLineUnicomExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=460)
	public String getInternetLineTelecomNumber() {
		return internetLineTelecomNumber;
	}

	public void setInternetLineTelecomNumber(String internetLineTelecomNumber) {
		this.internetLineTelecomNumber = internetLineTelecomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=470)
	public String getInternetLineTelecomMonthlyIncome() {
		return internetLineTelecomMonthlyIncome;
	}

	public void setInternetLineTelecomMonthlyIncome(String internetLineTelecomMonthlyIncome) {
		this.internetLineTelecomMonthlyIncome = internetLineTelecomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=480)
	public Date getInternetLineTelecomExpirationDate() {
		return internetLineTelecomExpirationDate;
	}

	public void setInternetLineTelecomExpirationDate(Date internetLineTelecomExpirationDate) {
		this.internetLineTelecomExpirationDate = internetLineTelecomExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=490)
	public String getVoiceLineMobileNumber() {
		return voiceLineMobileNumber;
	}

	public void setVoiceLineMobileNumber(String voiceLineMobileNumber) {
		this.voiceLineMobileNumber = voiceLineMobileNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=500)
	public String getVoiceLineMobileMonthlyIncome() {
		return voiceLineMobileMonthlyIncome;
	}

	public void setVoiceLineMobileMonthlyIncome(String voiceLineMobileMonthlyIncome) {
		this.voiceLineMobileMonthlyIncome = voiceLineMobileMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=510)
	public Date getVoiceLineMobileExpirationDate() {
		return voiceLineMobileExpirationDate;
	}

	public void setVoiceLineMobileExpirationDate(Date voiceLineMobileExpirationDate) {
		this.voiceLineMobileExpirationDate = voiceLineMobileExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=520)
	public String getVoiceLineUnicomNumber() {
		return voiceLineUnicomNumber;
	}

	public void setVoiceLineUnicomNumber(String voiceLineUnicomNumber) {
		this.voiceLineUnicomNumber = voiceLineUnicomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=530)
	public String getVoiceLineUnicomMonthlyIncome() {
		return voiceLineUnicomMonthlyIncome;
	}

	public void setVoiceLineUnicomMonthlyIncome(String voiceLineUnicomMonthlyIncome) {
		this.voiceLineUnicomMonthlyIncome = voiceLineUnicomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=540)
	public Date getVoiceLineUnicomExpirationDate() {
		return voiceLineUnicomExpirationDate;
	}

	public void setVoiceLineUnicomExpirationDate(Date voiceLineUnicomExpirationDate) {
		this.voiceLineUnicomExpirationDate = voiceLineUnicomExpirationDate;
	}
	
	@Length(min=0, max=50, message="条数长度必须介于 0 和 50 之间")
	@ExcelField(title="条数", align=2, sort=550)
	public String getVoiceLineTelecomNumber() {
		return voiceLineTelecomNumber;
	}

	public void setVoiceLineTelecomNumber(String voiceLineTelecomNumber) {
		this.voiceLineTelecomNumber = voiceLineTelecomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=560)
	public String getVoiceLineTelecomMonthlyIncome() {
		return voiceLineTelecomMonthlyIncome;
	}

	public void setVoiceLineTelecomMonthlyIncome(String voiceLineTelecomMonthlyIncome) {
		this.voiceLineTelecomMonthlyIncome = voiceLineTelecomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=570)
	public Date getVoiceLineTelecomExpirationDate() {
		return voiceLineTelecomExpirationDate;
	}

	public void setVoiceLineTelecomExpirationDate(Date voiceLineTelecomExpirationDate) {
		this.voiceLineTelecomExpirationDate = voiceLineTelecomExpirationDate;
	}
	
	@Length(min=0, max=50, message="门数长度必须介于 0 和 50 之间")
	@ExcelField(title="门数", align=2, sort=580)
	public String getImsMobileNumber() {
		return imsMobileNumber;
	}

	public void setImsMobileNumber(String imsMobileNumber) {
		this.imsMobileNumber = imsMobileNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=590)
	public String getImsMobileMonthlyIncome() {
		return imsMobileMonthlyIncome;
	}

	public void setImsMobileMonthlyIncome(String imsMobileMonthlyIncome) {
		this.imsMobileMonthlyIncome = imsMobileMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=600)
	public Date getImsMobileExpirationDate() {
		return imsMobileExpirationDate;
	}

	public void setImsMobileExpirationDate(Date imsMobileExpirationDate) {
		this.imsMobileExpirationDate = imsMobileExpirationDate;
	}
	
	@Length(min=0, max=50, message="门数长度必须介于 0 和 50 之间")
	@ExcelField(title="门数", align=2, sort=610)
	public String getImsUnicomNumber() {
		return imsUnicomNumber;
	}

	public void setImsUnicomNumber(String imsUnicomNumber) {
		this.imsUnicomNumber = imsUnicomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=620)
	public String getImsUnicomMonthlyIncome() {
		return imsUnicomMonthlyIncome;
	}

	public void setImsUnicomMonthlyIncome(String imsUnicomMonthlyIncome) {
		this.imsUnicomMonthlyIncome = imsUnicomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=630)
	public Date getImsUnicomExpirationDate() {
		return imsUnicomExpirationDate;
	}

	public void setImsUnicomExpirationDate(Date imsUnicomExpirationDate) {
		this.imsUnicomExpirationDate = imsUnicomExpirationDate;
	}
	
	@Length(min=0, max=50, message="门数长度必须介于 0 和 50 之间")
	@ExcelField(title="门数", align=2, sort=640)
	public String getImsTelecomNumber() {
		return imsTelecomNumber;
	}

	public void setImsTelecomNumber(String imsTelecomNumber) {
		this.imsTelecomNumber = imsTelecomNumber;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=650)
	public String getImsTelecomMonthlyIncome() {
		return imsTelecomMonthlyIncome;
	}

	public void setImsTelecomMonthlyIncome(String imsTelecomMonthlyIncome) {
		this.imsTelecomMonthlyIncome = imsTelecomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=660)
	public Date getImsTelecomExpirationDate() {
		return imsTelecomExpirationDate;
	}

	public void setImsTelecomExpirationDate(Date imsTelecomExpirationDate) {
		this.imsTelecomExpirationDate = imsTelecomExpirationDate;
	}
	
	
	//add-----klq
	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=661)
	public String getWlwkYdBusinessName() {
		return wlwkYdBusinessName;
	}

	public void setWlwkYdBusinessName(String wlwkYdBusinessName) {
		this.wlwkYdBusinessName = wlwkYdBusinessName;
	}

	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=662)
	public String getWlwkYdIncome() {
		return wlwkYdIncome;
	}

	public void setWlwkYdIncome(String wlwkYdIncome) {
		this.wlwkYdIncome = wlwkYdIncome;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=663)
	public Date getWlwkYdDateTo() {
		return wlwkYdDateTo;
	}

	public void setWlwkYdDateTo(Date wlwkYdDateTo) {
		this.wlwkYdDateTo = wlwkYdDateTo;
	}

	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=664)
	public String getWlwkLtBusinessName() {
		return wlwkLtBusinessName;
	}

	public void setWlwkLtBusinessName(String wlwkLtBusinessName) {
		this.wlwkLtBusinessName = wlwkLtBusinessName;
	}

	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=665)
	public String getWlwkLtIncome() {
		return wlwkLtIncome;
	}

	public void setWlwkLtIncome(String wlwkLtIncome) {
		this.wlwkLtIncome = wlwkLtIncome;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=666)
	public Date getWlwkLtDateTo() {
		return wlwkLtDateTo;
	}

	public void setWlwkLtDateTo(Date wlwkLtDateTo) {
		this.wlwkLtDateTo = wlwkLtDateTo;
	}

	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=667)
	public String getWlwkDxBusinessName() {
		return wlwkDxBusinessName;
	}

	public void setWlwkDxBusinessName(String wlwkDxBusinessName) {
		this.wlwkDxBusinessName = wlwkDxBusinessName;
	}

	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=668)
	public String getWlwkDxIncome() {
		return wlwkDxIncome;
	}

	public void setWlwkDxIncome(String wlwkDxIncome) {
		this.wlwkDxIncome = wlwkDxIncome;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=669)
	public Date getWlwkDxDateTo() {
		return wlwkDxDateTo;
	}

	public void setWlwkDxDateTo(Date wlwkDxDateTo) {
		this.wlwkDxDateTo = wlwkDxDateTo;
	}

	
	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=670)
	public String getiDCYdBusinessName() {
		return iDCYdBusinessName;
	}

	public void setiDCYdBusinessName(String iDCYdBusinessName) {
		this.iDCYdBusinessName = iDCYdBusinessName;
	}

	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="资费", align=2, sort=671)
	public String getiDCYdZf() {
		return iDCYdZf;
	}

	public void setiDCYdZf(String iDCYdZf) {
		this.iDCYdZf = iDCYdZf;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=672)
	public Date getiDCYdDateTo() {
		return iDCYdDateTo;
	}

	public void setiDCYdDateTo(Date iDCYdDateTo) {
		this.iDCYdDateTo = iDCYdDateTo;
	}

	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=673)
	public String getiDCLtBusinessName() {
		return iDCLtBusinessName;
	}

	public void setiDCLtBusinessName(String iDCLtBusinessName) {
		this.iDCLtBusinessName = iDCLtBusinessName;
	}

	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="资费", align=2, sort=674)
	public String getiDCLtZf() {
		return iDCLtZf;
	}

	public void setiDCLtZf(String iDCLtZf) {
		this.iDCLtZf = iDCLtZf;
	}

	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=675)
	public Date getiDCLtDateTo() {
		return iDCLtDateTo;
	}

	public void setiDCLtDateTo(Date iDCLtDateTo) {
		this.iDCLtDateTo = iDCLtDateTo;
	}

	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=676)
	public String getiDCQtBusinessName() {
		return iDCQtBusinessName;
	}

	public void setiDCQtBusinessName(String iDCQtBusinessName) {
		this.iDCQtBusinessName = iDCQtBusinessName;
	}

	
	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="IDC提供单位", align=2, sort=677)
	public String getiDCQtAddr() {
		return iDCQtAddr;
	}

	public void setiDCQtAddr(String iDCQtAddr) {
		this.iDCQtAddr = iDCQtAddr;
	}

	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="资费", align=2, sort=678)
	public String getiDCQtZf() {
		return iDCQtZf;
	}

	public void setiDCQtZf(String iDCQtZf) {
		this.iDCQtZf = iDCQtZf;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=679)
	public Date getiDCQtDateTo() {
		return iDCQtDateTo;
	}

	public void setiDCQtDateTo(Date iDCQtDateTo) {
		this.iDCQtDateTo = iDCQtDateTo;
	}
	
	//addend--klq
	
	
	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=680)
	public String getOtherProductsMobileBusinessName() {
		return otherProductsMobileBusinessName;
	}

	public void setOtherProductsMobileBusinessName(String otherProductsMobileBusinessName) {
		this.otherProductsMobileBusinessName = otherProductsMobileBusinessName;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=681)
	public String getOtherProductsMobileMonthlyIncome() {
		return otherProductsMobileMonthlyIncome;
	}

	public void setOtherProductsMobileMonthlyIncome(String otherProductsMobileMonthlyIncome) {
		this.otherProductsMobileMonthlyIncome = otherProductsMobileMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=690)
	public Date getOtherProductsMobileExpirationDate() {
		return otherProductsMobileExpirationDate;
	}

	public void setOtherProductsMobileExpirationDate(Date otherProductsMobileExpirationDate) {
		this.otherProductsMobileExpirationDate = otherProductsMobileExpirationDate;
	}
	
	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=700)
	public String getOtherProductsUnicomBusinessName() {
		return otherProductsUnicomBusinessName;
	}

	public void setOtherProductsUnicomBusinessName(String otherProductsUnicomBusinessName) {
		this.otherProductsUnicomBusinessName = otherProductsUnicomBusinessName;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=710)
	public String getOtherProductsUnicomMonthlyIncome() {
		return otherProductsUnicomMonthlyIncome;
	}

	public void setOtherProductsUnicomMonthlyIncome(String otherProductsUnicomMonthlyIncome) {
		this.otherProductsUnicomMonthlyIncome = otherProductsUnicomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=720)
	public Date getOtherProductsUnicomExpirationDate() {
		return otherProductsUnicomExpirationDate;
	}

	public void setOtherProductsUnicomExpirationDate(Date otherProductsUnicomExpirationDate) {
		this.otherProductsUnicomExpirationDate = otherProductsUnicomExpirationDate;
	}
	
	@Length(min=0, max=50, message="业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="业务名称", align=2, sort=730)
	public String getOtherProductsTelecomBusinessName() {
		return otherProductsTelecomBusinessName;
	}

	public void setOtherProductsTelecomBusinessName(String otherProductsTelecomBusinessName) {
		this.otherProductsTelecomBusinessName = otherProductsTelecomBusinessName;
	}
	
	@Length(min=0, max=50, message="月收入长度必须介于 0 和 50 之间")
	@ExcelField(title="月收入", align=2, sort=740)
	public String getOtherProductsTelecomMonthlyIncome() {
		return otherProductsTelecomMonthlyIncome;
	}

	public void setOtherProductsTelecomMonthlyIncome(String otherProductsTelecomMonthlyIncome) {
		this.otherProductsTelecomMonthlyIncome = otherProductsTelecomMonthlyIncome;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到期时间", align=2, sort=750)
	public Date getOtherProductsTelecomExpirationDate() {
		return otherProductsTelecomExpirationDate;
	}

	public void setOtherProductsTelecomExpirationDate(Date otherProductsTelecomExpirationDate) {
		this.otherProductsTelecomExpirationDate = otherProductsTelecomExpirationDate;
	}
	
	@Length(min=0, max=50, message="正在跟进或将来要跟进的业务名称长度必须介于 0 和 50 之间")
	@ExcelField(title="正在跟进或将来要跟进的业务名称", align=2, sort=760)
	public String getFollowUpBusinessName() {
		return followUpBusinessName;
	}

	public void setFollowUpBusinessName(String followUpBusinessName) {
		this.followUpBusinessName = followUpBusinessName;
	}
	
	@Length(min=0, max=2000, message="具体跟进情况长度必须介于 0 和 2000 之间")
	@ExcelField(title="具体跟进情况", align=2, sort=770)
	public String getFollowUpSituation() {
		return followUpSituation;
	}

	public void setFollowUpSituation(String followUpSituation) {
		this.followUpSituation = followUpSituation;
	}
	@ExcelField(title="客户经理工号", align=2, sort=780)
	/*public User getCustomerManagerNumber() {
		return customerManagerNumber;
	}

	public void setCustomerManagerNumber(User customerManagerNumber) {
		this.customerManagerNumber = customerManagerNumber;
	}*/

	public String getCustomerManagerNumber() {
		return customerManagerNumber;
	}

	public void setCustomerManagerNumber(String customerManagerNumber) {
		this.customerManagerNumber = customerManagerNumber;
	}
	@ExcelField(title="客户经理姓名", align=2, sort=790)
	public String getCustomerManagerName() {
		return customerManagerName;
	}

	public void setCustomerManagerName(String customerManagerName) {
		this.customerManagerName = customerManagerName;
	}
	
	
	
	public String getSort() {
		return sort;
	}

	

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 查询或者统计使用
	 * @return
	 */
	public String getTaxRevenueTo() {
		return taxRevenueTo;
	}

	public void setTaxRevenueTo(String taxRevenueTo) {
		this.taxRevenueTo = taxRevenueTo;
	}

	public String getMarketShare() {
		return marketShare;
	}
	public void setMarketShare(String marketShare) {
		this.marketShare = marketShare;
	}
	public String getMarketShareTo() {
		return marketShareTo;
	}

	public void setMarketShareTo(String marketShareTo) {
		this.marketShareTo = marketShareTo;
	}
	public String getPhoneUsageEmployeesNumberTo() {
		return phoneUsageEmployeesNumberTo;
	}

	public void setPhoneUsageEmployeesNumberTo(String phoneUsageEmployeesNumberTo) {
		this.phoneUsageEmployeesNumberTo = phoneUsageEmployeesNumberTo;
	}

	public String getPhoneUsageMobileMonthlyIncomeTo() {
		return phoneUsageMobileMonthlyIncomeTo;
	}

	public void setPhoneUsageMobileMonthlyIncomeTo(
			String phoneUsageMobileMonthlyIncomeTo) {
		this.phoneUsageMobileMonthlyIncomeTo = phoneUsageMobileMonthlyIncomeTo;
	}

	public Date getPhoneUsageMobileExpirationDateTo() {
		return phoneUsageMobileExpirationDateTo;
	}

	public void setPhoneUsageMobileExpirationDateTo(
			Date phoneUsageMobileExpirationDateTo) {
		this.phoneUsageMobileExpirationDateTo = phoneUsageMobileExpirationDateTo;
	}

	public String getPhoneUsageUnicomMonthlyIncomeTo() {
		return phoneUsageUnicomMonthlyIncomeTo;
	}

	public void setPhoneUsageUnicomMonthlyIncomeTo(
			String phoneUsageUnicomMonthlyIncomeTo) {
		this.phoneUsageUnicomMonthlyIncomeTo = phoneUsageUnicomMonthlyIncomeTo;
	}

	public Date getPhoneUsageUnicomExpirationDateTo() {
		return phoneUsageUnicomExpirationDateTo;
	}

	public void setPhoneUsageUnicomExpirationDateTo(
			Date phoneUsageUnicomExpirationDateTo) {
		this.phoneUsageUnicomExpirationDateTo = phoneUsageUnicomExpirationDateTo;
	}

	public String getPhoneUsageTelecomMonthlyIncomeTo() {
		return phoneUsageTelecomMonthlyIncomeTo;
	}

	public void setPhoneUsageTelecomMonthlyIncomeTo(
			String phoneUsageTelecomMonthlyIncomeTo) {
		this.phoneUsageTelecomMonthlyIncomeTo = phoneUsageTelecomMonthlyIncomeTo;
	}

	public Date getPhoneUsageTelecomExpirationDateTo() {
		return phoneUsageTelecomExpirationDateTo;
	}

	public void setPhoneUsageTelecomExpirationDateTo(
			Date phoneUsageTelecomExpirationDateTo) {
		this.phoneUsageTelecomExpirationDateTo = phoneUsageTelecomExpirationDateTo;
	}

	public String getDataLineMobileMonthlyIncomeTo() {
		return dataLineMobileMonthlyIncomeTo;
	}

	public void setDataLineMobileMonthlyIncomeTo(
			String dataLineMobileMonthlyIncomeTo) {
		this.dataLineMobileMonthlyIncomeTo = dataLineMobileMonthlyIncomeTo;
	}

	public Date getDataLineMobileExpirationDateTo() {
		return dataLineMobileExpirationDateTo;
	}

	public void setDataLineMobileExpirationDateTo(
			Date dataLineMobileExpirationDateTo) {
		this.dataLineMobileExpirationDateTo = dataLineMobileExpirationDateTo;
	}

	public String getDataLineUnicomMonthlyIncomeTo() {
		return dataLineUnicomMonthlyIncomeTo;
	}

	public void setDataLineUnicomMonthlyIncomeTo(
			String dataLineUnicomMonthlyIncomeTo) {
		this.dataLineUnicomMonthlyIncomeTo = dataLineUnicomMonthlyIncomeTo;
	}

	public Date getDataLineUnicomExpirationDateTo() {
		return dataLineUnicomExpirationDateTo;
	}

	public void setDataLineUnicomExpirationDateTo(
			Date dataLineUnicomExpirationDateTo) {
		this.dataLineUnicomExpirationDateTo = dataLineUnicomExpirationDateTo;
	}

	public String getDataLineTelecomMonthlyIncomeTo() {
		return dataLineTelecomMonthlyIncomeTo;
	}

	public void setDataLineTelecomMonthlyIncomeTo(
			String dataLineTelecomMonthlyIncomeTo) {
		this.dataLineTelecomMonthlyIncomeTo = dataLineTelecomMonthlyIncomeTo;
	}

	public Date getDataLineTelecomExpirationDateTo() {
		return dataLineTelecomExpirationDateTo;
	}

	public void setDataLineTelecomExpirationDateTo(
			Date dataLineTelecomExpirationDateTo) {
		this.dataLineTelecomExpirationDateTo = dataLineTelecomExpirationDateTo;
	}

	public String getInternetLineMobileMonthlyIncomeTo() {
		return internetLineMobileMonthlyIncomeTo;
	}

	public void setInternetLineMobileMonthlyIncomeTo(
			String internetLineMobileMonthlyIncomeTo) {
		this.internetLineMobileMonthlyIncomeTo = internetLineMobileMonthlyIncomeTo;
	}

	public Date getInternetLineMobileExpirationDateTo() {
		return internetLineMobileExpirationDateTo;
	}

	public void setInternetLineMobileExpirationDateTo(
			Date internetLineMobileExpirationDateTo) {
		this.internetLineMobileExpirationDateTo = internetLineMobileExpirationDateTo;
	}

	public String getInternetLineUnicomMonthlyIncomeTo() {
		return internetLineUnicomMonthlyIncomeTo;
	}

	public void setInternetLineUnicomMonthlyIncomeTo(
			String internetLineUnicomMonthlyIncomeTo) {
		this.internetLineUnicomMonthlyIncomeTo = internetLineUnicomMonthlyIncomeTo;
	}

	public Date getInternetLineUnicomExpirationDateTo() {
		return internetLineUnicomExpirationDateTo;
	}

	public void setInternetLineUnicomExpirationDateTo(
			Date internetLineUnicomExpirationDateTo) {
		this.internetLineUnicomExpirationDateTo = internetLineUnicomExpirationDateTo;
	}

	public String getInternetLineTelecomMonthlyIncomeTo() {
		return internetLineTelecomMonthlyIncomeTo;
	}

	public void setInternetLineTelecomMonthlyIncomeTo(
			String internetLineTelecomMonthlyIncomeTo) {
		this.internetLineTelecomMonthlyIncomeTo = internetLineTelecomMonthlyIncomeTo;
	}

	public Date getInternetLineTelecomExpirationDateTo() {
		return internetLineTelecomExpirationDateTo;
	}

	public void setInternetLineTelecomExpirationDateTo(
			Date internetLineTelecomExpirationDateTo) {
		this.internetLineTelecomExpirationDateTo = internetLineTelecomExpirationDateTo;
	}

	public String getVoiceLineMobileMonthlyIncomeTo() {
		return voiceLineMobileMonthlyIncomeTo;
	}

	public void setVoiceLineMobileMonthlyIncomeTo(
			String voiceLineMobileMonthlyIncomeTo) {
		this.voiceLineMobileMonthlyIncomeTo = voiceLineMobileMonthlyIncomeTo;
	}

	public Date getVoiceLineMobileExpirationDateTo() {
		return voiceLineMobileExpirationDateTo;
	}

	public void setVoiceLineMobileExpirationDateTo(
			Date voiceLineMobileExpirationDateTo) {
		this.voiceLineMobileExpirationDateTo = voiceLineMobileExpirationDateTo;
	}

	public String getVoiceLineUnicomMonthlyIncomeTo() {
		return voiceLineUnicomMonthlyIncomeTo;
	}

	public void setVoiceLineUnicomMonthlyIncomeTo(
			String voiceLineUnicomMonthlyIncomeTo) {
		this.voiceLineUnicomMonthlyIncomeTo = voiceLineUnicomMonthlyIncomeTo;
	}

	public Date getVoiceLineUnicomExpirationDateTo() {
		return voiceLineUnicomExpirationDateTo;
	}

	public void setVoiceLineUnicomExpirationDateTo(
			Date voiceLineUnicomExpirationDateTo) {
		this.voiceLineUnicomExpirationDateTo = voiceLineUnicomExpirationDateTo;
	}

	public String getVoiceLineTelecomMonthlyIncomeTo() {
		return voiceLineTelecomMonthlyIncomeTo;
	}

	public void setVoiceLineTelecomMonthlyIncomeTo(
			String voiceLineTelecomMonthlyIncomeTo) {
		this.voiceLineTelecomMonthlyIncomeTo = voiceLineTelecomMonthlyIncomeTo;
	}

	public Date getVoiceLineTelecomExpirationDateTo() {
		return voiceLineTelecomExpirationDateTo;
	}

	public void setVoiceLineTelecomExpirationDateTo(
			Date voiceLineTelecomExpirationDateTo) {
		this.voiceLineTelecomExpirationDateTo = voiceLineTelecomExpirationDateTo;
	}

	public String getImsMobileMonthlyIncomeTo() {
		return imsMobileMonthlyIncomeTo;
	}

	public void setImsMobileMonthlyIncomeTo(String imsMobileMonthlyIncomeTo) {
		this.imsMobileMonthlyIncomeTo = imsMobileMonthlyIncomeTo;
	}

	public Date getImsMobileExpirationDateTo() {
		return imsMobileExpirationDateTo;
	}

	public void setImsMobileExpirationDateTo(Date imsMobileExpirationDateTo) {
		this.imsMobileExpirationDateTo = imsMobileExpirationDateTo;
	}

	public String getImsUnicomMonthlyIncomeTo() {
		return imsUnicomMonthlyIncomeTo;
	}

	public void setImsUnicomMonthlyIncomeTo(String imsUnicomMonthlyIncomeTo) {
		this.imsUnicomMonthlyIncomeTo = imsUnicomMonthlyIncomeTo;
	}

	public Date getImsUnicomExpirationDateTo() {
		return imsUnicomExpirationDateTo;
	}

	public void setImsUnicomExpirationDateTo(Date imsUnicomExpirationDateTo) {
		this.imsUnicomExpirationDateTo = imsUnicomExpirationDateTo;
	}

	public String getImsTelecomMonthlyIncomeTo() {
		return imsTelecomMonthlyIncomeTo;
	}

	public void setImsTelecomMonthlyIncomeTo(String imsTelecomMonthlyIncomeTo) {
		this.imsTelecomMonthlyIncomeTo = imsTelecomMonthlyIncomeTo;
	}

	public Date getImsTelecomExpirationDateTo() {
		return imsTelecomExpirationDateTo;
	}

	public void setImsTelecomExpirationDateTo(Date imsTelecomExpirationDateTo) {
		this.imsTelecomExpirationDateTo = imsTelecomExpirationDateTo;
	}

	public String getOtherProductsMobileMonthlyIncomeTo() {
		return otherProductsMobileMonthlyIncomeTo;
	}

	public void setOtherProductsMobileMonthlyIncomeTo(
			String otherProductsMobileMonthlyIncomeTo) {
		this.otherProductsMobileMonthlyIncomeTo = otherProductsMobileMonthlyIncomeTo;
	}

	public Date getOtherProductsMobileExpirationDateTo() {
		return otherProductsMobileExpirationDateTo;
	}

	public void setOtherProductsMobileExpirationDateTo(
			Date otherProductsMobileExpirationDateTo) {
		this.otherProductsMobileExpirationDateTo = otherProductsMobileExpirationDateTo;
	}

	public String getOtherProductsUnicomMonthlyIncomeTo() {
		return otherProductsUnicomMonthlyIncomeTo;
	}

	public void setOtherProductsUnicomMonthlyIncomeTo(
			String otherProductsUnicomMonthlyIncomeTo) {
		this.otherProductsUnicomMonthlyIncomeTo = otherProductsUnicomMonthlyIncomeTo;
	}

	public Date getOtherProductsUnicomExpirationDateTo() {
		return otherProductsUnicomExpirationDateTo;
	}

	public void setOtherProductsUnicomExpirationDateTo(
			Date otherProductsUnicomExpirationDateTo) {
		this.otherProductsUnicomExpirationDateTo = otherProductsUnicomExpirationDateTo;
	}

	public String getOtherProductsTelecomMonthlyIncomeTo() {
		return otherProductsTelecomMonthlyIncomeTo;
	}

	public void setOtherProductsTelecomMonthlyIncomeTo(
			String otherProductsTelecomMonthlyIncomeTo) {
		this.otherProductsTelecomMonthlyIncomeTo = otherProductsTelecomMonthlyIncomeTo;
	}

	public Date getOtherProductsTelecomExpirationDateTo() {
		return otherProductsTelecomExpirationDateTo;
	}

	public void setOtherProductsTelecomExpirationDateTo(
			Date otherProductsTelecomExpirationDateTo) {
		this.otherProductsTelecomExpirationDateTo = otherProductsTelecomExpirationDateTo;
	}

	
	public String getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(String groupCount) {
		this.groupCount = groupCount;
	}

	public String getEmployeesNumberCount() {
		return employeesNumberCount;
	}

	public void setEmployeesNumberCount(String employeesNumberCount) {
		this.employeesNumberCount = employeesNumberCount;
	}

	public String getMarketShareCountOur() {
		return marketShareCountOur;
	}

	public void setMarketShareCountOur(String marketShareCountOur) {
		this.marketShareCountOur = marketShareCountOur;
	}

	public String getMarketShareCountOther() {
		return marketShareCountOther;
	}

	public void setMarketShareCountOther(String marketShareCountOther) {
		this.marketShareCountOther = marketShareCountOther;
	}

	public String getLinePermeabilityOur() {
		return linePermeabilityOur;
	}

	public void setLinePermeabilityOur(String linePermeabilityOur) {
		this.linePermeabilityOur = linePermeabilityOur;
	}

	public String getLinePermeabilityOther() {
		return linePermeabilityOther;
	}

	public void setLinePermeabilityOther(String linePermeabilityOther) {
		this.linePermeabilityOther = linePermeabilityOther;
	}

	public String getImsPermeabilityOur() {
		return imsPermeabilityOur;
	}

	public void setImsPermeabilityOur(String imsPermeabilityOur) {
		this.imsPermeabilityOur = imsPermeabilityOur;
	}

	public String getImsPermeabilityOther() {
		return imsPermeabilityOther;
	}

	public void setImsPermeabilityOther(String imsPermeabilityOther) {
		this.imsPermeabilityOther = imsPermeabilityOther;
	}

	public String getCustomerWarningOur() {
		return customerWarningOur;
	}

	public void setCustomerWarningOur(String customerWarningOur) {
		this.customerWarningOur = customerWarningOur;
	}

	public String getCustomerWarningOther() {
		return customerWarningOther;
	}

	public void setCustomerWarningOther(String customerWarningOther) {
		this.customerWarningOther = customerWarningOther;
	}

	public String getImsWarningOur() {
		return imsWarningOur;
	}

	public void setImsWarningOur(String imsWarningOur) {
		this.imsWarningOur = imsWarningOur;
	}

	public String getImsWarningOther() {
		return imsWarningOther;
	}

	public void setImsWarningOther(String imsWarningOther) {
		this.imsWarningOther = imsWarningOther;
	}

	public String getOtherWarningOur() {
		return otherWarningOur;
	}

	public void setOtherWarningOur(String otherWarningOur) {
		this.otherWarningOur = otherWarningOur;
	}

	public String getOtherWarningOther() {
		return otherWarningOther;
	}

	public void setOtherWarningOther(String otherWarningOther) {
		this.otherWarningOther = otherWarningOther;
	}

	public String getLineWarningOur() {
		return lineWarningOur;
	}

	public void setLineWarningOur(String lineWarningOur) {
		this.lineWarningOur = lineWarningOur;
	}

	public String getLineWarningOther() {
		return lineWarningOther;
	}

	public void setLineWarningOther(String lineWarningOther) {
		this.lineWarningOther = lineWarningOther;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public String getOperators() {
		return operators;
	}

	public void setOperators(String operators) {
		this.operators = operators;
	}

	public String getAllMonthlyIncome() {
		return allMonthlyIncome;
	}

	public void setAllMonthlyIncome(String allMonthlyIncome) {
		this.allMonthlyIncome = allMonthlyIncome;
	}

	public String getAllMonthlyIncomeTo() {
		return allMonthlyIncomeTo;
	}

	public void setAllMonthlyIncomeTo(String allMonthlyIncomeTo) {
		this.allMonthlyIncomeTo = allMonthlyIncomeTo;
	}

	public String getRegisteredCapitalTo() {
		return registeredCapitalTo;
	}

	public void setRegisteredCapitalTo(String registeredCapitalTo) {
		this.registeredCapitalTo = registeredCapitalTo;
	}
	
	@Length(min=0, max=50, message="营业状态必须介于 0 和 50 之间")
	@ExcelField(title="营业状态", align=2, sort=35, dictType="group_state")
	public String getGroupState() {
		return groupState;
	}

	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}

	@Length(min=0, max=50, message="上级单位长度必须介于 0 和 50 之间")
	@ExcelField(title="上级单位名称", align=2, sort=60, dictType="org_parent_name")
	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}
	
	public String getWlwkYdIncomeTo() {
		return wlwkYdIncomeTo;
	}

	public void setWlwkYdIncomeTo(String wlwkYdIncomeTo) {
		this.wlwkYdIncomeTo = wlwkYdIncomeTo;
	}

	public String getWlwkLtIncomeTo() {
		return wlwkLtIncomeTo;
	}

	public void setWlwkLtIncomeTo(String wlwkLtIncomeTo) {
		this.wlwkLtIncomeTo = wlwkLtIncomeTo;
	}

	public String getWlwkDxIncomeTo() {
		return wlwkDxIncomeTo;
	}

	public void setWlwkDxIncomeTo(String wlwkDxIncomeTo) {
		this.wlwkDxIncomeTo = wlwkDxIncomeTo;
	}

	public String getiDCYdZfTo() {
		return iDCYdZfTo;
	}

	public void setiDCYdZfTo(String iDCYdZfTo) {
		this.iDCYdZfTo = iDCYdZfTo;
	}

	public String getiDCLtZfTo() {
		return iDCLtZfTo;
	}

	public void setiDCLtZfTo(String iDCLtZfTo) {
		this.iDCLtZfTo = iDCLtZfTo;
	}

	public String getiDCQtZfTo() {
		return iDCQtZfTo;
	}

	public void setiDCQtZfTo(String iDCQtZfTo) {
		this.iDCQtZfTo = iDCQtZfTo;
	}
	
}