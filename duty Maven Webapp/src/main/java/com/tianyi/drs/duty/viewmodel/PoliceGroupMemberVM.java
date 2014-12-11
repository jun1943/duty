package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.basedata.viewmodel.PoliceVM;
/**
 * 警员分组成员model类，继承警员model类，用于前台操作显示使用
 * @author lq
 */
public class PoliceGroupMemberVM extends PoliceVM{
	/**
	 * 警员分组id
	 */
	private int groupId;
	/**
	 * 警员id
	 */
	private int policeId;
	/**
	 * 组织机构名称缩写
	 */
	private String orgShortName;
	
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getPoliceId() {
		return policeId;
	}

	public void setPoliceId(int policeId) {
		this.policeId = policeId;
	}

	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	
}
