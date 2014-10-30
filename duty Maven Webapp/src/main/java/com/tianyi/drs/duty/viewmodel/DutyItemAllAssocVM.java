package com.tianyi.drs.duty.viewmodel;

import java.util.Date;

import com.tianyi.drs.duty.model.DutyDesc;

public class DutyItemAllAssocVM extends DutyDesc{
	private String orgName;
	
	private String prepareName;
	
	private int dutyId;
	
	private int typeId;
	
	private int typeName;
	
	private int shiftId;
	
	private Date beginTime;
	
	private Date endTime;
	
	private int dutyItemId;
	
	private int itemTypeId;
	
	private String itemType;
	
	private int itemId;
	
	private String udName;
	
	private int itemParentId;
	
	private int itemLevel;
	
	private boolean isLeaf;
	
	private String full_id_path;

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

	public int getDutyId() {
		return dutyId;
	}

	public void setDutyId(int dutyId) {
		this.dutyId = dutyId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getTypeName() {
		return typeName;
	}

	public void setTypeName(int typeName) {
		this.typeName = typeName;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
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

	public int getDutyItemId() {
		return dutyItemId;
	}

	public void setDutyItemId(int dutyItemId) {
		this.dutyItemId = dutyItemId;
	}

	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getUdName() {
		return udName;
	}

	public void setUdName(String udName) {
		this.udName = udName;
	}

	public int getItemParentId() {
		return itemParentId;
	}

	public void setItemParentId(int itemParentId) {
		this.itemParentId = itemParentId;
	}

	public int getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getFull_id_path() {
		return full_id_path;
	}

	public void setFull_id_path(String full_id_path) {
		this.full_id_path = full_id_path;
	}
	
	
}
