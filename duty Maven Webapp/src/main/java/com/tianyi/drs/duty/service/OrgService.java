package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.model.Org; 
import com.tianyi.drs.duty.viewmodel.OrgWithGpsVM;
import com.tianyi.drs.duty.viewmodel.OrgWithPoliceVM;
import com.tianyi.drs.duty.viewmodel.OrgWithVehicleVM;
import com.tianyi.drs.duty.viewmodel.OrgWithWeaponVM;

public interface OrgService {

	List<Org> loadSubOrgList(String code,String path);
	
	List<OrgWithPoliceVM> loadOrgWithPoliceVMList(Integer id);
	
	List<OrgWithVehicleVM> loadOrgWithVehicleVMList(Integer id);
	
	List<OrgWithWeaponVM> loadOrgWithWeaponVMList(Integer id);
	
	List<OrgWithGpsVM> loadOrgWithGpsVMList(Integer id);
}
