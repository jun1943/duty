package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.viewmodel.OrgVM;
import com.tianyi.drs.duty.viewmodel.OrgWithPoliceVM;

public interface OrgService {

	List<Org> loadSubOrgList(String code,String path);
	
	List<OrgWithPoliceVM> loadOrgWithPoliceVMList(Integer id);
}
