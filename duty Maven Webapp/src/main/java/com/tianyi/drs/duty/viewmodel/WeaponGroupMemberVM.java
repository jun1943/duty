package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.basedata.viewmodel.WeaponVM;
/**
 * 武器分组成员model类，继承武器model类，用于前台操作显示使用
 */
public class WeaponGroupMemberVM extends WeaponVM  {
	/**
	 * 武器分组id
	 */
	private int groupId;
	/**
	 * 武器id
	 */
	private int weaponId;
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
