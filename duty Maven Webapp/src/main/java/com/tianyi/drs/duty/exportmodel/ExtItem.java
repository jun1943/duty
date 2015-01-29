package com.tianyi.drs.duty.exportmodel;

import java.util.List;

/**
 * 导出的基础资料信息
 * 如果在设定范围内存在备勤信息，就关联备勤的相关资料，已经关联的下级项目
 * 管理的下级项目不包括 shiftInfo, 此事shiftInfo=null
 * @author Owen
 *
 * @param <T>  
 * 包括
 */
public class ExtItem<T> {
	

	private Integer dutyItemId;
	/**
	 * 项目类型
	 * 1 车辆
	 * 2 警员
	 * 3 武器
	 * 4 gps
	 * 101 班次
	 * 999 自定义
	 * 
	 */
	private Integer itemTypeId;
	/**
	 * 基础类型
	 * 包括 警员，车辆，武器，gps
	 */
	private T data;
	/**
	 * 隶属的班次信息
	 */
	private ExtShiftInfo shiftInfo;
	/**
	 * 关联的子项目
	 */
	private List<ExtItem<?>> items;
	/**
	 * 层级
	 */
	private Integer level;
	
	public Integer getDutyItemId() {
		return dutyItemId;
	}
	public void setDutyItemId(Integer dutyItemId) {
		this.dutyItemId = dutyItemId;
	}
	public Integer getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ExtShiftInfo getShiftInfo() {
		return shiftInfo;
	}
	public void setShiftInfo(ExtShiftInfo shiftInfo) {
		this.shiftInfo = shiftInfo;
	}
	public List<ExtItem<?>> getItems() {
		return items;
	}
	public void setItems(List<ExtItem<?>> items) {
		this.items = items;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
