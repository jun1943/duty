package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.DutyItem;

public class DutyItemVM extends DutyItem{
	private String dutyTypeName;
	private String itemTypeName;
	
	private String itemName;
	
	private List<DutyItemVM> children;

	public String getDutyTypeName() {
		return dutyTypeName;
	}

	public void setDutyTypeName(String dutyTypeName) {
		this.dutyTypeName = dutyTypeName;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<DutyItemVM> getChildren() {
		return children;
	}

	public void setChildren(List<DutyItemVM> children) {
		this.children = children;
	}

}
