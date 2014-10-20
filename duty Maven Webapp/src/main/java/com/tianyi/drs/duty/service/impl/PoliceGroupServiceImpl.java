package com.tianyi.drs.duty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.PoliceGroupMapper;
import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.service.PoliceGroupService;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

@Service("policeGroupService")
public class PoliceGroupServiceImpl implements PoliceGroupService{

	@Resource(name="policeGroupMapper")
	private PoliceGroupMapper policeGroupMapper;
	
	public int loadVMCount(Map<String, Object> map) {
		
		int count=policeGroupMapper.countByVM(map);
		return count;
	}

	public List<PoliceGroupVM> loadVMList(Map<String, Object> map) {
		List<PoliceGroupVM> ls=policeGroupMapper.loadVMList(map);
		return ls;
	}

}
