package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.GpsGroup;
import com.tianyi.drs.duty.model.GpsGroupMember;
import com.tianyi.drs.duty.model.GpsGroupOrg;
import com.tianyi.drs.duty.viewmodel.GpsGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.GpsGroupVM;

public interface GpsGroupService {
	int loadVMCountByOrgId(Map<String,Object> map);

	List<GpsGroupVM> loadVMListByOrgId(Map<String,Object> map);
	
	List<GpsGroupOrg> loadShareOrgList(int pgid);
	
	void saveGpsGroup(GpsGroup pg,Object[] orgIds);
	
	GpsGroup loadById(Integer id);
	
	void deleteById(Integer id);
	
	int countMemberByGroupId(Integer groupId);
	
	List<GpsGroupMemberVM> loadMemberByGroupId(Map<String,Object> map);
	
	void appendMemeber(List<GpsGroupMember> ls);
	
	void delMemberById(Integer id);
	
	void cleanMember(Integer groupId);

	java.util.List<GpsGroupVM> loadVMListByOrgIdShared(
			Map<String, Object> map);
}
