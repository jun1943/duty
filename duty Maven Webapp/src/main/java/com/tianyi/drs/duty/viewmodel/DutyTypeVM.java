package com.tianyi.drs.duty.viewmodel;

import java.util.List;

/**
 * 勤务类型视图model
 * 
 */
import com.tianyi.drs.duty.model.DutyType;
/**
 * 勤务类型model类，集成勤务类型实体类，用于前台显示操作
 * @author lq
 */
public class DutyTypeVM extends DutyType {
	/**
	 * 属性列表
	 */
	private List<DutyTypePropertyVM> properties;
	/**
	 * 上级节点名称
	 */
	private String parentName;
	/**
	 * 上级节点全路径
	 */
	private String parentFullPath;
	
	public List<DutyTypePropertyVM> getProperties() {
		return properties;
	}

	public void setProperties(List<DutyTypePropertyVM> properties) {
		this.properties = properties;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentFullPath() {
		return parentFullPath;
	}

	public void setParentFullPath(String parentFullPath) {
		this.parentFullPath = parentFullPath;
	}

	
	
	
}
