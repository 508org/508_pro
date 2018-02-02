package com.tianyang.modules.pc.entity;

import com.tianyang.modules.sys.entity.Office;
import com.tianyang.modules.sys.entity.User;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tianyang.common.persistence.DataEntity;
import com.tianyang.common.utils.excel.annotation.ExcelField;

/**
 * 聚类市场信息Entity
 * @author 刘笑林
 * @version 2017-06-08
 */
public class PcClusteringMarket extends DataEntity<PcClusteringMarket> {
	
	private static final long serialVersionUID = 1L;
	private Office organization;		// 区县
	private Office attributedGrid;		// 归属网格
	private String clusterMarketType;		// 聚类市场类型
	private String clusterMarketName;		// 聚类市场名称
	private String groupNumber;		// 集团编号
	private String shopName;		// 店铺名称
	private String address;		// 地址（街道、门牌号）
	private String contactName;		// 联系人姓名
	private String contactPhone;		// 联系人手机号码
	private String employeesNumber;		// 员工数量
	private String mobileNumber;		// 移动数量
	private String otherNumber;		// 他网数量
	private String isOverlayMobileNetwork;		// 是否覆盖移动网路
	private String isUseBroadband;		// 是否使用宽带
	private String broadbandOperators;		// 宽带运营商
	private Date broadbandExpirationDate;		// 宽带到期时间
	private String isUseTelephone;		// 是否使用固话
	private String telephoneOperators;		// 固话运营商
	private Date telephoneExpirationDate;		// 固话到期时间
	private String otherProductUsage;		// 其他产品使用情况
	private String customerDemand;		// 客户需求
	private User customerManagerNumber;		// 客户经理
	
	private String sort;		// 排序
	
	
	/**
	 * 查询或者统计使用
	 * @return
	 */
	private String marketShare;//市场占有率
	private String marketShareTo;//市场占有率
	private String employeesNumberTo;		// 员工数量
	private Date broadbandExpirationDateTo;		// 宽带到期时间
	private Date telephoneExpirationDateTo;		// 固话到期时间
	
	
	//统计
	private String groupCount;//集团数量
	private String employeesNumberCount;//员工数量
	private String marketShareCountOur;//市场占有率
	private String marketShareCountOther;//市场占有率
	private String broadbandPermeabilityOur;//宽带渗透率
	private String broadbandPermeabilityOther;//宽带渗透率
	private String telPermeabilityOur;//固话渗透率
	private String telPermeabilityOther;//固话渗透率
	private String customerWarningOur;//客户协议到期预警
	private String customerWarningOther;//客户协议到期预警
	private String broadbandWarningOur;//宽带协议到期预警
	private String broadbandWarningOther;//宽带协议到期预警
	private String telWarningOur;//固话协议到期预警
	private String telWarningOther;//固话协议到期预警
	
	public PcClusteringMarket() {
		super();
	}

	public PcClusteringMarket(String id){
		super(id);
	}
	@ExcelField(title="区县", align=2, sort=10)
	public Office getOrganization() {
		return organization;
	}

	public void setOrganization(Office organization) {
		this.organization = organization;
	}
	
	@ExcelField(title="归属网格", align=2, sort=20)
	public Office getAttributedGrid() {
		return attributedGrid;
	}

	public void setAttributedGrid(Office attributedGrid) {
		this.attributedGrid = attributedGrid;
	}

	
	@Length(min=0, max=50, message="聚类市场类型长度必须介于 0 和 50 之间")
	@ExcelField(title="聚类市场类型", align=2, sort=30, dictType="cluster_market_type")
	public String getClusterMarketType() {
		return clusterMarketType;
	}

	
	public void setClusterMarketType(String clusterMarketType) {
		this.clusterMarketType = clusterMarketType;
	}
	
	@Length(min=0, max=255, message="聚类市场名称长度必须介于 0 和 255 之间")
	@ExcelField(title="聚类市场名称", align=2, sort=40)
	public String getClusterMarketName() {
		return clusterMarketName;
	}

	public void setClusterMarketName(String clusterMarketName) {
		this.clusterMarketName = clusterMarketName;
	}
	

	@Length(min=0, max=50, message="店铺名称长度必须介于 0 和 50 之间")
	@ExcelField(title="店铺名称", align=2, sort=50)
	public String getShopName() {
		return shopName;
	}

	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Length(min=0, max=50, message="集团编号长度必须介于 0 和 50 之间")
	@ExcelField(title="集团编号", align=2, sort=55)
	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	@Length(min=0, max=255, message="地址（街道、门牌号）长度必须介于 0 和 255 之间")
	@ExcelField(title="地址（街道、门牌号）", align=2, sort=60)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=50, message="联系人姓名长度必须介于 0 和 50 之间")
	@ExcelField(title="联系人姓名", align=2, sort=70)
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Length(min=0, max=50, message="联系人手机号码长度必须介于 0 和 50 之间")
	@ExcelField(title="联系人手机号码", align=2, sort=80)
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Length(min=0, max=50, message="员工数量长度必须介于 0 和 50 之间")
	@ExcelField(title="员工数量", align=2, sort=90)
	public String getEmployeesNumber() {
		return employeesNumber;
	}

	public void setEmployeesNumber(String employeesNumber) {
		this.employeesNumber = employeesNumber;
	}
	
	@Length(min=0, max=50, message="移动数量长度必须介于 0 和 50 之间")
	@ExcelField(title="移动数量", align=2, sort=100)
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Length(min=0, max=50, message="他网数量长度必须介于 0 和 50 之间")
	@ExcelField(title="他网数量", align=2, sort=110)
	public String getOtherNumber() {
		return otherNumber;
	}

	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}
	
	@Length(min=0, max=50, message="是否覆盖移动网路长度必须介于 0 和 50 之间")
	@ExcelField(title="是否覆盖移动网路", align=2, sort=120, dictType="is_or_no")
	public String getIsOverlayMobileNetwork() {
		return isOverlayMobileNetwork;
	}

	public void setIsOverlayMobileNetwork(String isOverlayMobileNetwork) {
		this.isOverlayMobileNetwork = isOverlayMobileNetwork;
	}
	
	@Length(min=0, max=50, message="是否使用宽带长度必须介于 0 和 50 之间")
	@ExcelField(title="是否使用宽带", align=2, sort=130, dictType="is_or_no")
	public String getIsUseBroadband() {
		return isUseBroadband;
	}

	public void setIsUseBroadband(String isUseBroadband) {
		this.isUseBroadband = isUseBroadband;
	}
	
	@Length(min=0, max=50, message="宽带运营商长度必须介于 0 和 50 之间")
	@ExcelField(title="宽带运营商", align=2, sort=140, dictType="broadband_operators")
	public String getBroadbandOperators() {
		return broadbandOperators;
	}

	public void setBroadbandOperators(String broadbandOperators) {
		this.broadbandOperators = broadbandOperators;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="宽带到期时间", align=2, sort=150)
	public Date getBroadbandExpirationDate() {
		return broadbandExpirationDate;
	}

	public void setBroadbandExpirationDate(Date broadbandExpirationDate) {
		this.broadbandExpirationDate = broadbandExpirationDate;
	}
	
	@Length(min=0, max=50, message="是否使用固话长度必须介于 0 和 50 之间")
	@ExcelField(title="是否使用固话", align=2, sort=160, dictType="is_or_no")
	public String getIsUseTelephone() {
		return isUseTelephone;
	}

	public void setIsUseTelephone(String isUseTelephone) {
		this.isUseTelephone = isUseTelephone;
	}
	
	@Length(min=0, max=50, message="固话运营商长度必须介于 0 和 50 之间")
	@ExcelField(title="固话运营商", align=2, sort=170, dictType="telephone_operators")
	public String getTelephoneOperators() {
		return telephoneOperators;
	}

	public void setTelephoneOperators(String telephoneOperators) {
		this.telephoneOperators = telephoneOperators;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="固话到期时间", align=2, sort=180)
	public Date getTelephoneExpirationDate() {
		return telephoneExpirationDate;
	}

	public void setTelephoneExpirationDate(Date telephoneExpirationDate) {
		this.telephoneExpirationDate = telephoneExpirationDate;
	}
	
	@Length(min=0, max=2000, message="其他产品使用情况长度必须介于 0 和 2000 之间")
	@ExcelField(title="其他产品使用情况", align=2, sort=190)
	public String getOtherProductUsage() {
		return otherProductUsage;
	}

	public void setOtherProductUsage(String otherProductUsage) {
		this.otherProductUsage = otherProductUsage;
	}
	
	@Length(min=0, max=2000, message="客户需求长度必须介于 0 和 2000 之间")
	@ExcelField(title="客户需求", align=2, sort=200)
	public String getCustomerDemand() {
		return customerDemand;
	}

	public void setCustomerDemand(String customerDemand) {
		this.customerDemand = customerDemand;
	}

	@ExcelField(title="客户经理工号", align=2, sort=210)
	public User getCustomerManagerNumber() {
		return customerManagerNumber;
	}

	public void setCustomerManagerNumber(User customerManagerNumber) {
		this.customerManagerNumber = customerManagerNumber;
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

	public String getEmployeesNumberTo() {
		return employeesNumberTo;
	}

	public void setEmployeesNumberTo(String employeesNumberTo) {
		this.employeesNumberTo = employeesNumberTo;
	}

	public Date getBroadbandExpirationDateTo() {
		return broadbandExpirationDateTo;
	}

	public void setBroadbandExpirationDateTo(Date broadbandExpirationDateTo) {
		this.broadbandExpirationDateTo = broadbandExpirationDateTo;
	}

	public Date getTelephoneExpirationDateTo() {
		return telephoneExpirationDateTo;
	}

	public void setTelephoneExpirationDateTo(Date telephoneExpirationDateTo) {
		this.telephoneExpirationDateTo = telephoneExpirationDateTo;
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

	public String getBroadbandPermeabilityOur() {
		return broadbandPermeabilityOur;
	}

	public void setBroadbandPermeabilityOur(String broadbandPermeabilityOur) {
		this.broadbandPermeabilityOur = broadbandPermeabilityOur;
	}

	public String getBroadbandPermeabilityOther() {
		return broadbandPermeabilityOther;
	}

	public void setBroadbandPermeabilityOther(String broadbandPermeabilityOther) {
		this.broadbandPermeabilityOther = broadbandPermeabilityOther;
	}

	public String getTelPermeabilityOur() {
		return telPermeabilityOur;
	}

	public void setTelPermeabilityOur(String telPermeabilityOur) {
		this.telPermeabilityOur = telPermeabilityOur;
	}

	public String getTelPermeabilityOther() {
		return telPermeabilityOther;
	}

	public void setTelPermeabilityOther(String telPermeabilityOther) {
		this.telPermeabilityOther = telPermeabilityOther;
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

	public String getBroadbandWarningOur() {
		return broadbandWarningOur;
	}

	public void setBroadbandWarningOur(String broadbandWarningOur) {
		this.broadbandWarningOur = broadbandWarningOur;
	}

	public String getBroadbandWarningOther() {
		return broadbandWarningOther;
	}

	public void setBroadbandWarningOther(String broadbandWarningOther) {
		this.broadbandWarningOther = broadbandWarningOther;
	}

	public String getTelWarningOur() {
		return telWarningOur;
	}

	public void setTelWarningOur(String telWarningOur) {
		this.telWarningOur = telWarningOur;
	}

	public String getTelWarningOther() {
		return telWarningOther;
	}

	public void setTelWarningOther(String telWarningOther) {
		this.telWarningOther = telWarningOther;
	}
	
	
	
	
}