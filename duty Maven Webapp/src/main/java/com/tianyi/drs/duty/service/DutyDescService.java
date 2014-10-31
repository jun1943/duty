package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.DutyDesc;
import com.tianyi.drs.duty.viewmodel.DutyDescVM;

public interface DutyDescService {

	DutyDescVM loadDutyDescVM(Integer id);
	
	DutyDescVM loadDutyDescVMByOrgIdAndYMD(Integer orgId,Integer ymd);
	
	void save(DutyDescVM ddvm);
	
	List<DutyDesc> loadTemplateByOrgId(Integer orgId);
}
