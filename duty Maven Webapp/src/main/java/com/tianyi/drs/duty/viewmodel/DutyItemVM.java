package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.DutyItem;
import com.tianyi.drs.duty.model.PoliceTarget;
/**
 * 报备明细model类，继承报备明细实体类，用作前台显示使用
 * @author lq
 *
 */
public class DutyItemVM extends DutyItem{
	/**
	 * 勤务类型名称
	 */
	private String dutyTypeName;
	/**
	 * 明细类型名称
	 */
	private String itemTypeName;
	/**
	 * 报备明细显示名称
	 */
	private String displayName;
	/**
	 * 报备明细名称
	 */
	private String itemName;
	/**
	 * 报备明细内容名称
	 */
	private String itemInnerTypeName;
	/**
	 * 报备类型关联任务类型名称
	 */
	private Integer taskType;
	/**
	 * 报备子节点，内循环
	 */
	private List<DutyItemVM> children;

	/**
	 * 警员关联任务
	 */
	private List<PoliceTarget> targets;
	
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

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public List<PoliceTarget> getTargets() {
		return targets;
	}

	public void setTargets(List<PoliceTarget> targets) {
		this.targets = targets;
	}
	
	
}
