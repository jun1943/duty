package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;

public interface DutyService {

	List<DutyVM> loadVMList(Map<String,Object> map);
	
	DutyVM loadVMByOrgIdAndYmd(Integer orgId,Integer ymd);
	
	List<DutyVM> loadTemplatesWithOutItem(Integer orgId);
	
	
	void save(DutyVM vm);

	List<DutyItemCountVM> loadTotalPolice(Map<String, Object> map);
}
