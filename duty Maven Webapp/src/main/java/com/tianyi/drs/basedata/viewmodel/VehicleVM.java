package com.tianyi.drs.basedata.viewmodel;

import com.tianyi.drs.basedata.model.Vehicle;

public class VehicleVM extends Vehicle {

	private String typeName;
	private String orgName;
 
	private String orgCode;
	private String orgPath;
	private String gpsNumber;
	private String gpsName;
	private String iconUrl;
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	
	public String getGpsNumber() {
		return gpsNumber;
	}

	public void setGpsNumber(String gpsNumber) {
		this.gpsNumber = gpsNumber;
	}

	public String getGpsName() {
		return gpsName;
	}

	public void setGpsName(String gpsName) {
		this.gpsName = gpsName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	

}
