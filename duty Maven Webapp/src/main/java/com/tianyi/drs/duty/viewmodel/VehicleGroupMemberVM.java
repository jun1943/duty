package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.basedata.viewmodel.VehicleVM;
 

public class VehicleGroupMemberVM extends VehicleVM {
	private int groupId;
	private int vehicleId;
	private String orgShortName;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getOrgShortName() {
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	
}
