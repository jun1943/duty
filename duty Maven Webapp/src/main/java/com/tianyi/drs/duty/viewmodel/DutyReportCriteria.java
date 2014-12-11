package com.tianyi.drs.duty.viewmodel;

import java.util.Date;
import java.util.List;

/**
 * 报表查询对象
 * @author Owen
 *
 */
public class DutyReportCriteria {
	
	/**
	 * 组织机构id集
	 */
	private List<Integer> orgIds;
	/**
	 * 时间日期
	 */
	private Integer ymd;
	/**
	 * 开始时间
	 */
	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 勤务类型属性id集
	 */
	private List<Integer> taskPropertyIds;
	/**
	 * 衣着方式
	 */
	private List<Integer> attireTypeIds;
	/**
	 * 警员类型id集
	 */
	private List<Integer> policeTypeIds;
	/**
	 * 武装方式
	 */
	private List<Integer> armamentTypeIds;
	/**
	 * 勤务类型id集
	 */
	private List<Integer> dutyTypeIds;
	
	public List<Integer> getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(List<Integer> orgIds) {
		this.orgIds = orgIds;
	}
	public Integer getYmd() {
		return ymd;
	}
	public void setYmd(Integer ymd) {
		this.ymd = ymd;
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
	public List<Integer> getTaskPropertyIds() {
		return taskPropertyIds;
	}
	public void setTaskPropertyIds(List<Integer> taskPropertyIds) {
		this.taskPropertyIds = taskPropertyIds;
	}
	public List<Integer> getAttireTypeIds() {
		return attireTypeIds;
	}
	public void setAttireTypeIds(List<Integer> attireTypeIds) {
		this.attireTypeIds = attireTypeIds;
	}
	public List<Integer> getPoliceTypeIds() {
		return policeTypeIds;
	}
	public void setPoliceTypeIds(List<Integer> policeTypeIds) {
		this.policeTypeIds = policeTypeIds;
	}
	public List<Integer> getArmamentTypeIds() {
		return armamentTypeIds;
	}
	public void setArmamentTypeIds(List<Integer> armamentTypeIds) {
		this.armamentTypeIds = armamentTypeIds;
	}
	public List<Integer> getDutyTypeIds() {
		return dutyTypeIds;
	}
	public void setDutyTypeIds(List<Integer> dutyTypeIds) {
		this.dutyTypeIds = dutyTypeIds;
	}

	
}
