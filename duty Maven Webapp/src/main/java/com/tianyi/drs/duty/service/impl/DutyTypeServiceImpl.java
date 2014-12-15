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
import com.tianyi.drs.duty.util.ResultMsg;
import com.tianyi.drs.duty.util.TreeHelper;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyTypePropertyVM;
import com.tianyi.drs.duty.viewmodel.DutyTypeVM;


/**
 * 勤务类型服务接口实现
 * @author lq
 *
 */
@Service("dutyTypeService")
public class DutyTypeServiceImpl implements DutyTypeService {

	@Resource(name = "dutyTypeMapper")
	private DutyTypeMapper dutyTypeMapper;
	
	@Resource(name = "dutyTypePropertyRelateMapper")
	private DutyTypePropertyRelateMapper dtprMapper;
	

	/**
	 * 获取勤务类型属性列表
	 */
	public List<DutyTypePropertyVM> loadProperties() {
		
		return dutyTypeMapper.loadProperties();
	}

	/**
	 * 获取勤务类型列表数据
	 */
	public List<DutyTypeVM> loadDutyTypeVM(Boolean isUsed) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("isUsed", isUsed);
		return dutyTypeMapper.loadDutyTypeVM(map);
	}

	/**
	 * 保存新增的勤务类型数据
	 */
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

	/**
	 * 更新勤务类型状态，启用或者锁定
	 * 启用，只能一级一级启用，
	 * 锁定，锁定上级节点，一并锁定下级节点
	 */
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

	/**
	 * 根据id，删除勤务类型数据
	 */
	@Transactional
	public ResultMsg deleteNode(Integer id) {
		
		ResultMsg rm=null;
		
		DutyType dt=dutyTypeMapper.selectByPrimaryKey(id);
		
		int usedCount=dutyTypeMapper.checkUsed(dt.getFullpath());
			
		if(usedCount>0){
			rm=new ResultMsg(false,"勤务类型已经被使用，不能删除!");
				
		}else if(!dt.getIsLeaf()){
			rm=new ResultMsg(false,"只能删除末级节点!");
		}else{
			dutyTypeMapper.deleteByPrimaryKey(id);
			rm=new ResultMsg(false,null);
		}
		
		return rm;
	}
 

	/**
	 * 获取勤务类型列表数据，用于勤务类型选择
	 */
	public List<DutyType> loadDutyType(Map<String, Object> map) {
		return dutyTypeMapper.loadDutyType(map);
	}

	/**
	 * 获取勤务类型下级节点属性
	 */
	public List<DutyItemCountVM> loadDutyItemCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dutyTypeMapper.loadDutyItemCount(map);
	}
 
	
}
