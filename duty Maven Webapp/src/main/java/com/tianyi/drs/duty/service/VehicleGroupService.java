package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.VehicleGroup;
import com.tianyi.drs.duty.model.VehicleGroupMember;
import com.tianyi.drs.duty.model.VehicleGroupOrg;
import com.tianyi.drs.duty.viewmodel.VehicleGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.VehicleGroupVM;
 

public interface VehicleGroupService {

	int loadVMCountByOrgId(Map<String,Object> map);

	List<VehicleGroupVM> loadVMListByOrgId(Map<String,Object> map);
	
	List<VehicleGroupOrg> loadShareOrgList(int pgid);
	
	void saveVehicleGroup(VehicleGroup pg,Object[] orgIds);
	
	VehicleGroup loadById(Integer id);
	
	void deleteById(Integer id);
	
	int countMemberByGroupId(Integer groupId);
	
	List<VehicleGroupMemberVM> loadMemberByGroupId(Map<String,Object> map);
	
	void appendMemeber(List<VehicleGroupMember> ls);
	
	void delMemberById(Integer id);
	
	void cleanMember(Integer groupId);

	java.util.List<VehicleGroupVM> loadVMListByOrgIdShared(
			Map<String, Object> map);

}
