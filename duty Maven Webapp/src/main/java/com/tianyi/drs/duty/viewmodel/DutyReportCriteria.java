package com.tianyi.drs.duty.viewmodel;

import java.util.Date;
import java.util.List;

/**
 * 报表查询对象
 * @author Owen
 *
 */
public class DutyReportCriteria {
	
	private List<Integer> orgIds;
	private Integer ymd;
	private Date beginTime;
	private Date endTime;
	private List<Integer> taskProperyIds;
	private List<Integer> attireTypeIds;
	private List<Integer> policeTypeIds;
	private List<Integer> armamentTypeIds;
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
		return taskProperyIds;
	}
	public void setTaskPropertyIds(List<Integer> taskPropertyIds) {
		this.taskProperyIds = taskPropertyIds;
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
