package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.OrgMapper;
import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.service.OrgService; 
import com.tianyi.drs.duty.viewmodel.OrgWithGpsVM;
import com.tianyi.drs.duty.viewmodel.OrgWithPoliceVM;
import com.tianyi.drs.duty.viewmodel.OrgWithVehicleVM;
import com.tianyi.drs.duty.viewmodel.OrgWithWeaponVM;

@Service("orgService")
public class OrgServiceImple implements OrgService {

	@Resource(name = "orgMapper")
	private OrgMapper orgMapper;

	public List<Org> loadSubOrgList(String code, String path) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgCode", code);
		map.put("orgPath", path);

		List<Org> ls = orgMapper.loadSubOrgList(map);

		return ls;
	}

	public List<OrgWithPoliceVM> loadOrgWithPoliceVMList(Integer id) {
		List<OrgWithPoliceVM> ls = orgMapper.loadOrgWithPoliceVMList(id);
		return ls;
	}

	public List<OrgWithVehicleVM> loadOrgWithVehicleVMList(Integer id) {
		List<OrgWithVehicleVM> ls = orgMapper.loadOrgWithVehicleVMList(id);
		return ls;
	}

	public List<OrgWithWeaponVM> loadOrgWithWeaponVMList(Integer id) {
		List<OrgWithWeaponVM> ls = orgMapper.loadOrgWithWeaponVMList(id);
		return ls;
	}

	public List<OrgWithGpsVM> loadOrgWithGpsVMList(Integer id) {
		List<OrgWithGpsVM> ls = orgMapper.loadOrgWithGpsVMList(id);
		return ls;
	}



}
