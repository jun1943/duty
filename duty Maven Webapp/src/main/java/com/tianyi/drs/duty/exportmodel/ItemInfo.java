package com.tianyi.drs.duty.exportmodel;

public class ItemInfo<T> {
	private  T data;
	private  ExtShiftInfo shiftInfo;
	private Integer itemTypeId;

	private Integer dutyItemId;
	//private Integer level;
	
	public Integer getDutyItemId() {
		return dutyItemId;
	}

	public void setDutyItemId(Integer dutyItemId) {
		this.dutyItemId = dutyItemId;
	}

//	public Integer getLevel() {
//		return level;
//	}
//
//	public void setLevel(Integer level) {
//		this.level = level;
//	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public ExtShiftInfo getShiftInfo() {
		return shiftInfo;
	}

	public void setShiftInfo(ExtShiftInfo shiftInfo) {
		this.shiftInfo = shiftInfo;
	}
	
	
	
}
