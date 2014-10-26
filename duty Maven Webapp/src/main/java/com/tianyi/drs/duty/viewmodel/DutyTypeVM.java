package com.tianyi.drs.duty.viewmodel;

import java.util.List;

/**
 * 勤务类型视图model
 * 
 */
import com.tianyi.drs.duty.model.DutyType;

public class DutyTypeVM extends DutyType {
	private List<DutyTypePropertyVM> properties;
	private String parentName;
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
