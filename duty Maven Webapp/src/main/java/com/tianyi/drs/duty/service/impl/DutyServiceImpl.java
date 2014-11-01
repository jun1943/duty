package com.tianyi.drs.duty.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.DutyMapper;
import com.tianyi.drs.duty.service.DutyService;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;

@Service("dutyService")
public class DutyServiceImpl implements DutyService{

	@Resource(name = "dutyMapper")
	private DutyMapper dutyMapper;
	
	public List<DutyVM> loadVMList(Map<String, Object> map) {
		List<DutyVM> dvms=dutyMapper.loadDutyVMList(map);
		itemToTreeOfList(dvms);
		return dvms;
	}

	public DutyVM loadVMByOrgIdAndYmd(Integer orgId,Integer ymd){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("ymd", ymd);
		List<DutyVM> dvms=loadVMList(map);
		
		DutyVM dvm=null;
		
		if(dvms.size()>0){
			dvm=dvms.get(0);
		}
		return dvm;
	}
	
	private void itemToTreeOfList(List<DutyVM> dvms){
		for(DutyVM dvm : dvms){
			Map<Integer,DutyItemVM> map=new HashMap<Integer,DutyItemVM>();
			List<DutyItemVM> ls=dvm.getItems();
			dvm.setItems(new ArrayList<DutyItemVM>());
			for(DutyItemVM ivm:ls){
				if(ivm.getParentId()!=0){
					DutyItemVM pivm=map.get(ivm.getParentId());
					if(pivm.getChildren()==null)
						pivm.setChildren(new ArrayList<DutyItemVM>());
					map.get(ivm.getParentId()).getChildren().add(ivm);
				}else{
					dvm.getItems().add(ivm);
				}
				map.put(ivm.getId(), ivm);
			}
		}
		
	}

	public List<DutyVM> loadTemplatesWithOutItem(Integer orgId) {
		
		return null;
	}

}
