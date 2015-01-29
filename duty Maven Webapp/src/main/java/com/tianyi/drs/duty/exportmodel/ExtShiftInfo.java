package com.tianyi.drs.duty.exportmodel;

import java.util.Date;



/**
 * 班次信息
 * 包括备勤类型，班次信息
 * 
 * @author Owen
 *
 */
public class ExtShiftInfo {

	/**
	 * 备勤类型id
	 */
	private Integer DutyTypeId;
	/**
	 * 备勤类型名称
	 */
	private String DutyTypeName;
	/**
	 * 班次id
	 */
	private Integer shiftId;
	/**
	 * 班次名称
	 */
	private String shiftName;
	/**
	 * 开始时间
	 */
	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	public Integer getDutyTypeId() {
		return DutyTypeId;
	}
	public void setDutyTypeId(Integer dutyTypeId) {
		DutyTypeId = dutyTypeId;
	}
	public String getDutyTypeName() {
		return DutyTypeName;
	}
	public void setDutyTypeName(String dutyTypeName) {
		DutyTypeName = dutyTypeName;
	}
	public Integer getShiftId() {
		return shiftId;
	}
	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
}
