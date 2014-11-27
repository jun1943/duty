package com.tianyi.drs.duty.viewmodel;

public class UserObjectVM {
	private Integer id;
	private String loginName;
	private String loginType;
	private String password;
	private Integer orgId;
	private String orgPath;
	private String orgCode;
	private String orgName; 
	private Integer bindPoliceUserId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getBindPoliceUserId() {
		return bindPoliceUserId;
	}
	public void setBindPoliceUserId(Integer bindPoliceUserId) {
		this.bindPoliceUserId = bindPoliceUserId;
	}
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	} 
}
