package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.duty.model.Police;

public class PoliceViewModel extends Police {
	 
    private String typeName;
  
    private String orgName;

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
}
