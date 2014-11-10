package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.WeaponGroup;
import com.tianyi.drs.duty.model.WeaponGroupMember;
import com.tianyi.drs.duty.model.WeaponGroupOrg;
import com.tianyi.drs.duty.viewmodel.WeaponGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.WeaponGroupVM;

public interface WeaponGroupService {
	int loadVMCountByOrgId(Map<String,Object> map);

	List<WeaponGroupVM> loadVMListByOrgId(Map<String,Object> map);
	
	List<WeaponGroupOrg> loadShareOrgList(int pgid);
	
	void saveWeaponGroup(WeaponGroup pg,Object[] orgIds);
	
	WeaponGroup loadById(Integer id);
	
	void deleteById(Integer id);
	
	int countMemberByGroupId(Integer groupId);
	
	List<WeaponGroupMemberVM> loadMemberByGroupId(Map<String,Object> map);
	
	void appendMemeber(List<WeaponGroupMember> ls);
	
	void delMemberById(Integer id);
	
	void cleanMember(Integer groupId);

	java.util.List<WeaponGroupVM> loadVMListByOrgIdShared(
			Map<String, Object> map);
}
