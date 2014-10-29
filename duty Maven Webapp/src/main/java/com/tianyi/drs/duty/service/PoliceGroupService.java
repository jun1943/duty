package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.model.PoliceGroupMember;
import com.tianyi.drs.duty.model.PoliceGroupOrg;
import com.tianyi.drs.duty.viewmodel.PoliceGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

public interface PoliceGroupService {

	int loadVMCountByOrgId(Map<String,Object> map);

	List<PoliceGroupVM> loadVMListByOrgId(Map<String,Object> map);
	
	List<PoliceGroupOrg> loadShareOrgList(int pgid);
	
	void savePoliceGroup(PoliceGroup pg,Object[] orgIds);
	
	PoliceGroup loadById(Integer id);
	
	void deleteById(Integer id);
	
	int countMemberByGroupId(Integer groupId);
	
	List<PoliceGroupMemberVM> loadMemberByGroupId(Map<String,Object> map);
	
	void appendMemeber(List<PoliceGroupMember> ls);
	
	void delMemberById(Integer id);
	
	void cleanMember(Integer groupId);

	java.util.List<PoliceGroupVM> loadVMListByOrgIdShared(
			Map<String, Object> map);

}
