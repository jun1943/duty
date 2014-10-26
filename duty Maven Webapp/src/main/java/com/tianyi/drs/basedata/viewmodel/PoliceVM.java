package com.tianyi.drs.basedata.viewmodel;

import com.tianyi.drs.basedata.model.Police;

public class PoliceVM extends Police {
	 
    private String typeName;
  
    private String orgName;

	private String orgCode;
	
	private String orgPath;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

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
}
