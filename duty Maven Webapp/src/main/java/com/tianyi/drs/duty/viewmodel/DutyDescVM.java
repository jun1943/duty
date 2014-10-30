package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.DutyDesc;

public class DutyDescVM extends DutyDesc{
	private String  orgName;
	private String prepareName;
	private List<DutyVM> duties;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getPrepareName() {
		return prepareName;
	}
	public void setPrepareName(String prepareName) {
		this.prepareName = prepareName;
	}
	public List<DutyVM> getDuties() {
		return duties;
	}
	public void setDuties(List<DutyVM> duties) {
		this.duties = duties;
	}
	
	
	
}
