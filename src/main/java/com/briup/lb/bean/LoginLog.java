package com.briup.lb.bean;

import java.util.Date;

public class LoginLog {
	private String id;
	private String loginName;
	private String ipAddress;
	private Date loginTime;

	public LoginLog() {
		
	}

	public LoginLog(String id, String loginName, String ipAddress, Date loginTime) {
		this.id = id;
		this.loginName = loginName;
		this.ipAddress = ipAddress;
		this.loginTime = loginTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
