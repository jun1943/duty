package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.basedata.viewmodel.GpsVM;
/**
 * 定位设备分组成员model类，继承定位设备model类，用于前台显示操作使用
 * @author lq
 */
public class GpsGroupMemberVM extends GpsVM {
	/**
	 * 定位设备分组id
	 */
	private int groupId;
	/**
	 * 定位设备id
	 */
	private int gpsId;
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
