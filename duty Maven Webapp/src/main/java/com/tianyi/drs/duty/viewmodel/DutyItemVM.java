package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.DutyItem;

public class DutyItemVM extends DutyItem{
	private String dutyTypeName;
	private String itemTypeName;
	private String displayName;
	private String itemName;
	private String itemInnerTypeName;
	
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<DutyItemVM> getChildren() {
		return children;
	}

	public void setChildren(List<DutyItemVM> children) {
		this.children = children;
	}

	public String getItemInnerTypeName() {
		return itemInnerTypeName;
	}

	public void setItemInnerTypeName(String itemInnerTypeName) {
		this.itemInnerTypeName = itemInnerTypeName;
	}

}
