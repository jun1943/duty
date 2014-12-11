package com.tianyi.drs.duty.viewmodel;


/**
 * 报备数据导出虚拟类，用作前台显示使用
 */
public class DutyExportVM {
	/**
	 * 报备类型名称
	 */
	private String dutyName;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 班次时间范围
	 */
	private String timeArea;
	/**
	 * 车辆统计汇总数目
	 */
	private Integer vehicleCount;
	/**
	 * 警员统计汇总数目
	 */
	private Integer policeCount;
	/**
	 * 武器统计汇总数目
	 */
	private Integer weaponCount;
	/**
	 * 定位设备统计汇总数据
	 */
	private Integer gpsdeviceCount;
	
	public String getDutyName() {
		return dutyName;
	}
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTimeArea() {
		return timeArea;
	}
	public void setTimeArea(String timeArea) {
		this.timeArea = timeArea;
	}
	public Integer getVehicleCount() {
		return vehicleCount;
	}
	public void setVehicleCount(Integer vehicleCount) {
		this.vehicleCount = vehicleCount;
	}
	public Integer getPoliceCount() {
		return policeCount;
	}
	public void setPoliceCount(Integer policeCount) {
		this.policeCount = policeCount;
	}
	public Integer getWeaponCount() {
		return weaponCount;
	}
	public void setWeaponCount(Integer weaponCount) {
		this.weaponCount = weaponCount;
	}
	public Integer getGpsdeviceCount() {
		return gpsdeviceCount;
	}
	public void setGpsdeviceCount(Integer gpsdeviceCount) {
		this.gpsdeviceCount = gpsdeviceCount;
	} 
}
