package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.tianyi.drs.duty.dao.VehicleGroupMapper;
import com.tianyi.drs.duty.dao.VehicleGroupMemberMapper;
import com.tianyi.drs.duty.dao.VehicleGroupOrgMapper; 
import com.tianyi.drs.duty.model.VehicleGroup;
import com.tianyi.drs.duty.model.VehicleGroupMember;
import com.tianyi.drs.duty.model.VehicleGroupOrg;
import com.tianyi.drs.duty.service.VehicleGroupService; 
import com.tianyi.drs.duty.viewmodel.VehicleGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.VehicleGroupVM;
 
@Service("vehicleGroupService")
public class VehicleGroupServiceImpl implements VehicleGroupService {

	@Resource(name="vehicleGroupMapper")
	private VehicleGroupMapper vehicleGroupMapper;
	
	@Resource(name="vehicleGroupOrgMapper")
	private VehicleGroupOrgMapper vehicleGroupOrgMapper;
	
	@Resource(name="vehicleGroupMemberMapper")
	private VehicleGroupMemberMapper vehicleGroupMemberMapper;
	
	public int loadVMCountByOrgId(Map<String, Object> map) {
		int count=vehicleGroupMapper.countByOrgId(map);
		return count;
	}

	public List<VehicleGroupVM> loadVMListByOrgId(Map<String, Object> map) {
		List<VehicleGroupVM> ls=vehicleGroupMapper.loadVMListByOrgId(map);
		return ls;
	}

	public List<VehicleGroupOrg> loadShareOrgList(int pgid) {
		List<VehicleGroupOrg> ls=vehicleGroupOrgMapper.loadVehicleGroupOrgByPGId(pgid);
		return ls;
	}
	@Transactional
	public void saveVehicleGroup(VehicleGroup pg, Object[] orgIds) {
		if(pg.getId()==0){
			vehicleGroupMapper.insert(pg);
		}else{
			vehicleGroupMapper.updateByPrimaryKey(pg);
			vehicleGroupOrgMapper.deleteByPGId(pg.getId());
		}

		for(Object oid : orgIds){
			Integer id=(Integer)oid;
			
			VehicleGroupOrg pgo=new VehicleGroupOrg();
			pgo.setOrgId(id.intValue());
			pgo.setVehicleGroupId(pg.getId());
			
			vehicleGroupOrgMapper.insert(pgo);
		}
		
	}

	public VehicleGroup loadById(Integer id) {
		return vehicleGroupMapper.selectByPrimaryKey(id);
	}

	public void deleteById(Integer id) {
		vehicleGroupMapper.deleteByPrimaryKey(id);
		vehicleGroupOrgMapper.deleteByPGId(id); //删除对应共享的机构
		
	}

	public int countMemberByGroupId(Integer groupId) {
		int total=vehicleGroupMemberMapper.countMemberByGroupId(groupId);
		return total;
	}

	public List<VehicleGroupMemberVM> loadMemberByGroupId(
			Map<String, Object> map) {
		List<VehicleGroupMemberVM> ls=vehicleGroupMemberMapper.loadMemberByGroupId(map);

		return ls;
	}
	@Transactional
	public void appendMemeber(List<VehicleGroupMember> ls) {
		for(VehicleGroupMember pgm : ls){
			Map<String,Object> m1=new HashMap<String,Object>();
			m1.put("groupId", pgm.getGroupId());
			m1.put("memberId", pgm.getVehicleId());
			
			Integer count=vehicleGroupMemberMapper.existsByMemberId(m1);
			
			if(count==0){
				vehicleGroupMemberMapper.insert(pgm);
			}
			
		}
		
	}

	public void delMemberById(Integer id) {
		vehicleGroupMemberMapper.deleteByPrimaryKey(id);
		
	}

	public void cleanMember(Integer groupId) {
		vehicleGroupMemberMapper.deleteByGroupId(groupId);
		
	}

	public List<VehicleGroupVM> loadVMListByOrgIdShared(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<VehicleGroupVM> ls=vehicleGroupMapper.loadVMListByOrgIdShared(map);
		return ls;
	}
 

}
