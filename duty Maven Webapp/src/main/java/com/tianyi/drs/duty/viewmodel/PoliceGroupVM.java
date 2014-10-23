package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.duty.model.PoliceGroup;

public class PoliceGroupVM extends PoliceGroup{
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
		
		switch(this.getShareType())
		{
		case 0:
			this.shareTypeDesc="不共享";
			break;
		case 1:
			this.shareTypeDesc="共享到下级";
			break;
		}

		return shareTypeDesc;
	}
	public void setShareTypeDesc(String shareTypeDesc) {
		this.shareTypeDesc = shareTypeDesc;
	}
	
	
}
