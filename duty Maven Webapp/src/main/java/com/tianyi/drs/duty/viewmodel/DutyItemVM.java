package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.DutyItem;

public class DutyItemVM  extends DutyItem{
	
	private String itemType;
	private String displayName;//显示名称，车辆显示车牌号，人员显示名称
	private String name;
	private String number;
	private List<DutyItemVM> children;
	
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public List<DutyItemVM> getChildren() {
		return children;
	}
	public void setChildren(List<DutyItemVM> children) {
		this.children = children;
	}

	
	
}
