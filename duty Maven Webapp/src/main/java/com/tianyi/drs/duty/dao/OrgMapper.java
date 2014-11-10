package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.viewmodel.OrgVM;
import com.tianyi.drs.duty.viewmodel.OrgWithGpsVM;
import com.tianyi.drs.duty.viewmodel.OrgWithPoliceVM;
import com.tianyi.drs.duty.viewmodel.OrgWithVehicleVM;
import com.tianyi.drs.duty.viewmodel.OrgWithWeaponVM;

@MyBatisRepository
public interface OrgMapper {

	List<Org> loadSubOrgList(Map<String,Object> map);
	
	List<OrgWithPoliceVM> loadOrgWithPoliceVMList(Integer id);

	List<OrgWithVehicleVM> loadOrgWithVehicleVMList(Integer id);

	List<OrgWithWeaponVM> loadOrgWithWeaponVMList(Integer id);

	List<OrgWithGpsVM> loadOrgWithGpsVMList(Integer id);
}
