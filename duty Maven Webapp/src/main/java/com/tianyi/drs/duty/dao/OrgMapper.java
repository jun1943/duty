package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.viewmodel.OrgVM;
import com.tianyi.drs.duty.viewmodel.OrgWithPoliceVM;

@MyBatisRepository
public interface OrgMapper {

	List<Org> loadSubOrgList(Map<String,Object> map);
	
	List<OrgWithPoliceVM> loadOrgWithPoliceVMList(Integer id);
}
