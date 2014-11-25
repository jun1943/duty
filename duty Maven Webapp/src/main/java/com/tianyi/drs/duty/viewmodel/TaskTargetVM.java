package com.tianyi.drs.duty.viewmodel;

public class TaskTargetVM {
	private Integer targetType;
	private Integer targetId;
	private String name;
	private String areaName;
	private Integer count;
	private Integer stayTime;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getStayTime() {
		return stayTime;
	}
	public void setStayTime(Integer stayTime) {
		this.stayTime = stayTime;
	}
	public Integer getTargetType() {
		return targetType;
	}
	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}
	public Integer getTargetId() {
		return targetId;
	}
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}
