package com.tianyi.drs.duty.viewmodel;
/**
 * 关联任务model类，用做前台显示用
 * @author lq
 */
public class TaskTargetVM {
	/**
	 * 关联任务类型
	 */
	private Integer targetType;
	/**
	 * 关联任务id
	 */
	private Integer targetId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 经过次数
	 */
	private Integer count;
	/**
	 * 停留时间
	 */
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
