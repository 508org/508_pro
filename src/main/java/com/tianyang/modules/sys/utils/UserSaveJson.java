package com.tianyang.modules.sys.utils;

public class UserSaveJson {
	
	/*{
	   "userid": "zhangsan",
	   "name": "张三",
	   "department": [1, 2],
	   "position": "产品经理",
	   "mobile": "15913215421",
	   "gender": "1",
	   "email": "zhangsan@gzdev.com",
	   "weixinid": "zhangsan4dev",
	   "avatar_mediaid": "2-G6nrLmr5EC3MNb_-zL1dDdzkd0p7cNliYu9V5w7o8K0",
	   "extattr": {"attrs":[{"name":"爱好","value":"旅游"},{"name":"卡号","value":"1234567234"}]}
	}*/
	
	private String userid;
	private String name;
	private int[] department;
	private String position;
	private String mobile;
	private String gender;
	private String email;
	private String weixinid;
	private String avatar_mediaid;
	private String extattr;

	
	public int[] getDepartment() {
		return department;
	}
	public void setDepartment(int[] department) {
		this.department = department;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}
	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}
	public String getExtattr() {
		return extattr;
	}
	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}

}
