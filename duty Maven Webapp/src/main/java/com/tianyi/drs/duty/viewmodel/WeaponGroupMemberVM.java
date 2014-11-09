package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.basedata.viewmodel.WeaponVM;

public class WeaponGroupMemberVM extends WeaponVM  {
	private int groupId;
	private int weaponId;
	private String orgShortName;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getWeaponId() {
		return weaponId;
	}
	public void setWeaponId(int weaponId) {
		this.weaponId = weaponId;
	}
	public String getOrgShortName() {
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
}
