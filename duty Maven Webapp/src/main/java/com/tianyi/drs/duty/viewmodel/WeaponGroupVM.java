package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.duty.model.WeaponGroup;

public class WeaponGroupVM extends WeaponGroup  {
	private String orgName;
	private String orgCode;
	private String orgPath;
	private String shareTypeDesc;
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	public String getShareTypeDesc() {
		return shareTypeDesc;
	}
	public void setShareTypeDesc(String shareTypeDesc) {
		this.shareTypeDesc = shareTypeDesc;
	}
}
