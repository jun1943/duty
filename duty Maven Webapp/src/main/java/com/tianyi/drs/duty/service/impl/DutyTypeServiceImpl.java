package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.drs.duty.dao.DutyTypeMapper;
import com.tianyi.drs.duty.dao.DutyTypePropertyRelateMapper;
import com.tianyi.drs.duty.model.DutyType;
import com.tianyi.drs.duty.model.DutyTypePropertyRelate;
import com.tianyi.drs.duty.service.DutyTypeService;
import com.tianyi.drs.duty.util.TreeHelper;
import com.tianyi.drs.duty.viewmodel.DutyTypePropertyVM;
import com.tianyi.drs.duty.viewmodel.DutyTypeVM;


@Service("dutyTypeService")
public class DutyTypeServiceImpl implements DutyTypeService {

	@Resource(name = "dutyTypeMapper")
	private DutyTypeMapper dutyTypeMapper;
	
	@Resource(name = "dutyTypePropertyRelateMapper")
	private DutyTypePropertyRelateMapper dtprMapper;
	

	public List<DutyTypePropertyVM> loadProperties() {
		
		return dutyTypeMapper.loadProperties();
	}

	public List<DutyTypeVM> loadDutyTypeVM(Boolean isUsed) {
		return dutyTypeMapper.loadDutyTypeVM(isUsed);
	}

	@Transactional
	public void save(DutyTypeVM vm) {
		DutyType parent=null;
		DutyType target=new DutyType();
		
		if(vm.getParentId() !=null && vm.getParentId() !=0)
			parent=dutyTypeMapper.selectByPrimaryKey(vm.getParentId());
		else
			parent=new DutyType();
		//
		target.setId(vm.getId());
		target.setName(vm.getName());
		target.setParentId(vm.getParentId());
		target.setFullpath(parent.getFullpath()==null?target.getName():parent.getFullpath()+"."+target.getName());
		target.setLevel(parent.getLevel()+1);
		target.setIsLeaf(vm.getIsLeaf());
		target.setMaxPolice(vm.getMaxPolice());
		target.setAssoTaskType(vm.getAssoTaskType());
		target.setAttireType(vm.getAttireType());
		target.setArmamentType(vm.getArmamentType());
		target.setIsShowname(vm.getIsShowname());
		target.setIsUsed(vm.getIsUsed());
		
		if(target.getId()==0){
			dutyTypeMapper.insert(target);
			vm.setId(target.getId());
		}else{
			dutyTypeMapper.updateByPrimaryKey(target);
		}
		
		//修改父节点的isLeaf=false!
		if(parent.getId()!=0 && parent.getIsLeaf()){
			parent.setIsLeaf(false);
			dutyTypeMapper.updateByPrimaryKey(parent);
		}
		
		//先删除以前关联的属性子表数据
		dtprMapper.deleteByDutyTypeId(target.getId());
		
		for(DutyTypePropertyVM  dtpvm: vm.getProperties()){

			DutyTypePropertyRelate dtpr=new DutyTypePropertyRelate();
			
			dtpr.setDutyTypeId(target.getId());
			dtpr.setPropertyId(dtpvm.getId());
			
			dtprMapper.insert(dtpr);
			
		}
		
	}

	
	public void updateUseStateByFullPath(Integer id,Boolean isUsed){
		Map<String,Object> map=new HashMap<String,Object>();
		
		DutyType dt=dutyTypeMapper.selectByPrimaryKey(id);
		
		if(dt !=null){
			map.put("fullPath", dt.getFullpath());
			map.put("isUsed", isUsed);
			map.put("name", dt.getName());
			List<String> ls=TreeHelper.getAllParentFullPath(dt.getFullpath());
			map.put("list", ls);
			dutyTypeMapper.updateUseStateByFullPath(map);
		}
	}

	@Transactional
	public boolean deleteNode(Integer id,String msg) {
		
		int usedCount=dutyTypeMapper.checkUsed(id);
		DutyType dt=dutyTypeMapper.selectByPrimaryKey(id);
		
		if(usedCount>0){
			msg="勤务类型已经被使用，不能删除!";
			return false;
		}else if(!dt.getIsLeaf()){
			msg="只能删除末级节点!";
			return false;
		}else{
			dutyTypeMapper.deleteByPrimaryKey(id);
			return true;
		}
		
	}
 
	
}
