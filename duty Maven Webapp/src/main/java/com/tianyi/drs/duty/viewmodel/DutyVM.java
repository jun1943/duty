package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.Duty;
/**
 * 报备model类，集成报备实体类，用于前台显示操作使用
 * @author lq
 */
public class DutyVM extends Duty{
	/**
	 * 组织机构名称
	 */
	private String orgName;
	/**
	 * 节点名称
	 */
	private String prepareName;
	
	/**
	 * 报备明细列表
	 */
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
