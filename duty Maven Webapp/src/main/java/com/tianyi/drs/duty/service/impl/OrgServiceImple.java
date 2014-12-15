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

/**
 * 组织机构逻辑接口实现
 * @author lq
 *
 */
@Service("orgService")
public class OrgServiceImple implements OrgService {

	@Resource(name = "orgMapper")
	private OrgMapper orgMapper;

	/**
	 * 根据编码、路径获取组织机构列表
	 */
	public List<Org> loadSubOrgList(String code, String path) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgCode", code);
		map.put("orgPath", path);

		List<Org> ls = orgMapper.loadSubOrgList(map);

		return ls;
	}

	/**
	 * 根据id，获取组织机构，以及警员成员，构建树状结构列表
	 */
	public List<OrgWithPoliceVM> loadOrgWithPoliceVMList(Integer id) {
		List<OrgWithPoliceVM> ls = orgMapper.loadOrgWithPoliceVMList(id);
		return ls;
	}

	/**
	 * 根据id，获取组织机构，以及车辆成员，构建树状结构列表
	 */
	public List<OrgWithVehicleVM> loadOrgWithVehicleVMList(Integer id) {
		List<OrgWithVehicleVM> ls = orgMapper.loadOrgWithVehicleVMList(id);
		return ls;
	}

	/**
	 * 根据id，获取组织机构，以及武器成员，构建树状结构列表
	 */
	public List<OrgWithWeaponVM> loadOrgWithWeaponVMList(Integer id) {
		List<OrgWithWeaponVM> ls = orgMapper.loadOrgWithWeaponVMList(id);
		return ls;
	}

	/**
	 * 根据id，获取组织机构，以及定位设备成员，构建树状结构列表
	 */
	public List<OrgWithGpsVM> loadOrgWithGpsVMList(Integer id) {
		List<OrgWithGpsVM> ls = orgMapper.loadOrgWithGpsVMList(id);
		return ls;
	}



}
