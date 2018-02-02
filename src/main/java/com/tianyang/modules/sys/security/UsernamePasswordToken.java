/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.tianyang.modules.sys.security;

/**
 * 用户和密码（包含验证码）令牌类
 * @author ThinkGem
 * @version 2013-5-19
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;
	private boolean mobileLogin;
	private boolean weiWxin = false;
	
	

	public UsernamePasswordToken() {
		super();
	}
	
	public UsernamePasswordToken(String username, String password) {
		super(username, password);
	}

	public UsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String captcha, boolean mobileLogin) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.mobileLogin = mobileLogin;
	}
	public UsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String captcha, boolean mobileLogin,boolean weiWxin) {
		super(username, password, rememberMe, host);
		this.captcha     = captcha;
		this.mobileLogin = mobileLogin;
		this.weiWxin     = weiWxin;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public boolean isMobileLogin() {
		return mobileLogin;
	}
	
	public boolean getWeiWxin() {
		return weiWxin;
	}

	public void setWeiWxin(boolean weiWxin) {
		this.weiWxin = weiWxin;
	}
	
}