package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.basedata.viewmodel.VehicleVM;
 
/**
 * 车辆分组成员model类，继承车辆model类，用于前台显示操作
 * @author lq
 */
public class VehicleGroupMemberVM extends VehicleVM {
	/**
	 * 车辆分组id
	 */
	private int groupId;
	/**
	 * 车辆id
	 */
	private int vehicleId;
	/**
	 * 组织机构名称缩写
	 */
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
