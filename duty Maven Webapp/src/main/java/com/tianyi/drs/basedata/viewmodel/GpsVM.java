package com.tianyi.drs.basedata.viewmodel;

import com.tianyi.drs.basedata.model.Gps;
/**
 * GPS虚拟类，集成gps实体类，用于前台显示
 * @author lq
 *
 */
public class GpsVM extends Gps {
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 组织结构名称
	 */
	private String orgName;
	/**
	 * 组织结构编码
	 */
	private String orgCode;
	/**
	 * 组织机构路径
	 */
	private String orgPath; 
	/**
	 * 组织机构路径
	 */
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
