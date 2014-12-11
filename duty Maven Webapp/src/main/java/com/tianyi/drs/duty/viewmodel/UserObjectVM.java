package com.tianyi.drs.duty.viewmodel;

/**
 * 用户model类，用作地址栏有效用户信息验证
 * @author lq
 */

public class UserObjectVM {
	/**
	 * 标识id
	 */
	private Integer id;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 登录类型
	 */
	private String loginType;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 所属组织机构id
	 */
	private Integer orgId;
	/**
	 * 组织机构路径
	 */
	private String orgPath;
	/**
	 * 组织机构编码
	 */
	private String orgCode;
	/**
	 * 组织机构名称
	 */
	private String orgName;
	/**
	 * 绑定警员id
	 */
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
