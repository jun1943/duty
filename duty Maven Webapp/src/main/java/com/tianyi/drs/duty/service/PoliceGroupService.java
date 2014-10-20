package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

public interface PoliceGroupService {

	public int loadVMCount(Map<String,Object> map);

	public List<PoliceGroupVM> loadVMList(Map<String,Object> map);
	
}
