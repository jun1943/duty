package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.viewmodel.OrgVM;

public interface OrgService {

	List<Org> loadSubOrgList(String code,String path);
	
	List<OrgVM> loadSubOrgVMList(String code,String path);
}
