package com.tianyi.drs.duty.exportmodel;

import java.util.List;
 
import com.tianyi.drs.basedata.model.Police; 

public class PoliceExtItem {

	private Police data;
	
	private Integer dutyItemId;
	/**
	 * 项目类型
	 * 1 车辆
	 * 2 警员
	 * 3 武器
	 * 4 gps
	 * 101 班次
	 * 999 自定义
	 * 
	 */
	private Integer itemTypeId;

	
	/**
	 * 关联的定位设备子项目
	 */
	private List<Integer> gpsItems;
	
	/**
	 * 关联的武器子项目
	 */
	private List<Integer> weaponItems;
	/**
	 * 隶属的班次信息
	 */ 
	private ExtShiftInfo shiftInfo;
	/**
	 * 层级
	 */
	//private Integer level;
	public Integer getDutyItemId() {
		return dutyItemId;
	}
	public void setDutyItemId(Integer dutyItemId) {
		this.dutyItemId = dutyItemId;
	}
	public Integer getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public List<Integer> getGpsItems() {
		return gpsItems;
	}
	public void setGpsItems(List<Integer> gpsItems) {
		this.gpsItems = gpsItems;
	}
	public List<Integer> getWeaponItems() {
		return weaponItems;
	}
	public void setWeaponItems(List<Integer> weaponItems) {
		this.weaponItems = weaponItems;
	}
	public ExtShiftInfo getShiftInfo() {
		return shiftInfo;
	}
	public void setShiftInfo(ExtShiftInfo shiftInfo) {
		this.shiftInfo = shiftInfo;
	}
//	public Integer getLevel() {
//		return level;
//	}
//	public void setLevel(Integer level) {
//		this.level = level;
//	}
	public Police getData() {
		return data;
	}
	public void setData(Police data) {
		this.data = data;
	}
}
