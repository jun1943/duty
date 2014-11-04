package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.DutyType;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyTypePropertyVM;
import com.tianyi.drs.duty.viewmodel.DutyTypeVM;

public interface DutyTypeService {
	

	List<DutyTypePropertyVM>  loadProperties();
	
	List<DutyTypeVM> loadDutyTypeVM(Boolean isUsed);
	
	void save(DutyTypeVM vm);
	
	void updateUseStateByFullPath(Integer id,Boolean isUsed);
	
	boolean deleteNode(Integer id,String msg);
 
	List<DutyType> loadDutyType(Map<String, Object> map);

	List<DutyItemCountVM> loadDutyItemCount(Map<String, Object> map);
 
}
