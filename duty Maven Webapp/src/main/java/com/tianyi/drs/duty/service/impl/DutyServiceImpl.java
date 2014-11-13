package com.tianyi.drs.duty.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.drs.duty.dao.DutyItemMapper;
import com.tianyi.drs.duty.dao.DutyMapper;
import com.tianyi.drs.duty.dao.PoliceTargetMapper;
import com.tianyi.drs.duty.model.Duty;
import com.tianyi.drs.duty.model.DutyProperty;
import com.tianyi.drs.duty.model.PoliceTarget;
import com.tianyi.drs.duty.service.DutyService;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;

@Service("dutyService")
public class DutyServiceImpl implements DutyService{

	@Resource(name = "dutyMapper")
	private DutyMapper dutyMapper;
	
	@Resource(name = "dutyItemMapper")
	private DutyItemMapper dutyItemMapper;
	
	@Resource(name = "policeTargetMapper")
	private PoliceTargetMapper policeTargetMapper;
	
	public List<DutyVM> loadVMList(Map<String, Object> map) {
		List<DutyVM> dvms=dutyMapper.loadDutyVMList(map);
		itemToTreeOfList(dvms);
		return dvms;
	}

	public DutyVM loadVMByOrgIdAndYmd(Integer orgId,Integer ymd){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("ymd", ymd);
		map.put("isTemplate", false);
		List<DutyVM> dvms=loadVMList(map);
		
		DutyVM dvm=null;
		
		if(dvms.size()>0){
			dvm=dvms.get(0);
		}
		return dvm;
	}
	
	public DutyVM loadById(Integer id){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
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
				if(ivm.getParentId()!=null && ivm.getParentId()!=0){
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

	public List<Duty> loadTemplatesWithOutItem(Integer orgId) {
		List<Duty> vms=  dutyMapper.loadTemplatesWithOutItem(orgId);
		return vms;
	}

	@Transactional
	public void save(DutyVM vm) {
		vm.setCreateTime(new Date());
		
		if(vm.getId()==0){
			dutyMapper.insert(vm);
		}else{
			dutyMapper.updateByPrimaryKey(vm);
		}
		
		dutyItemMapper.deleteByDutyId(vm.getId());
		policeTargetMapper.deleteByDutyId(vm.getId());
		
		for(DutyItemVM ivm:vm.getItems()){
			saveItem(ivm,null,vm);
		}
	}

	private void saveItem(DutyItemVM ivm,DutyItemVM pivm,DutyVM vm){
		
		ivm.setId(0);
		ivm.setDutyId(vm.getId());

		if(ivm.getItemTypeId()==100){
			ivm.setDutyTypeId(ivm.getItemId());
			ivm.setBeginTime(null);
			ivm.setEndTime(null);
		}else {
			ivm.setDutyTypeId(pivm.getDutyTypeId());
		}
		
		if(ivm.getItemTypeId() !=101 && pivm !=null){
			ivm.setBeginTime(pivm.getBeginTime());
			ivm.setEndTime(pivm.getEndTime());
		}
		
		if(pivm==null){
			ivm.setLevel(1);
			ivm.setParentId(null);
		}else{
			ivm.setLevel(pivm.getLevel()+1);
			ivm.setParentId(pivm.getId());
		}
		
		if(ivm.getChildren()!=null && ivm.getChildren().size()>0){
			ivm.setIsLeaf(false);
		}else{
			ivm.setIsLeaf(true);
		}
		
		dutyItemMapper.insert(ivm);
		ivm.setFullIdPath(ivm.getLevel()==1?ivm.getId().toString():pivm.getFullIdPath()+"."+ivm.getId());
		dutyItemMapper.updateByPrimaryKey(ivm);

		if(ivm.getItemTypeId()==2){
			if(ivm.getTargets()!=null ){
				for(PoliceTarget pt : ivm.getTargets()){
					pt.setDutyId(vm.getId());
					pt.setDutyItemId(ivm.getId());
					policeTargetMapper.insert(pt);
				}
			}
		}
		
		if(ivm.getChildren() !=null){
			
			for(DutyItemVM civm:ivm.getChildren()){
				saveItem(civm,ivm,vm);
			}
		}
	}

	public List<DutyItemCountVM> loadTotalPolice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dutyMapper.loadTotalPolice(map);
	}

	public List<DutyItemCountVM> loadTotalPolicedetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dutyMapper.loadTotalPolicedetail(map);
	}

	public List<DutyProperty> selectdutyProperty() {
		// TODO Auto-generated method stub
		return dutyMapper.selectdutyProperty();
	}
}
