package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.basedata.viewmodel.PoliceVM;

public class PoliceGroupMemberVM extends PoliceVM{
	private int groupId;
	private int policeId;
	private String orgShortName;
	
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getPoliceId() {
		return policeId;
	}

	public void setPoliceId(int policeId) {
		this.policeId = policeId;
	}

	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	
}
