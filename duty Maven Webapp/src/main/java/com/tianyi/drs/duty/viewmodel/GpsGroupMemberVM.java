package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.basedata.viewmodel.GpsVM;

public class GpsGroupMemberVM extends GpsVM {
	private int groupId;
	private int gpsId;
	private String orgShortName;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getGpsId() {
		return gpsId;
	}
	public void setGpsId(int gpsId) {
		this.gpsId = gpsId;
	}
	public String getOrgShortName() {
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	
}
