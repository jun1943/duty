package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.Duty;

public class DutyVM extends Duty{
	
	private String orgName;
	private String prepareName;
	private List<DutyItemVM> items;
	
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
	public List<DutyItemVM> getItems() {
		return items;
	}
	public void setItems(List<DutyItemVM> items) {
		this.items = items;
	}
	
	
	
}
