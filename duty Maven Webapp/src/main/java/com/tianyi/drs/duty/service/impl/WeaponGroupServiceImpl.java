package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.tianyi.drs.duty.dao.WeaponGroupMapper;
import com.tianyi.drs.duty.dao.WeaponGroupMemberMapper;
import com.tianyi.drs.duty.dao.WeaponGroupOrgMapper; 
import com.tianyi.drs.duty.model.WeaponGroup;
import com.tianyi.drs.duty.model.WeaponGroupMember;
import com.tianyi.drs.duty.model.WeaponGroupOrg;
import com.tianyi.drs.duty.service.WeaponGroupService; 
import com.tianyi.drs.duty.viewmodel.WeaponGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.WeaponGroupVM;
 

@Service("weaponGroupService")
public class WeaponGroupServiceImpl implements WeaponGroupService  {

	@Resource(name="weaponGroupMapper")
	private WeaponGroupMapper weaponGroupMapper;
	
	@Resource(name="weaponGroupOrgMapper")
	private WeaponGroupOrgMapper weaponGroupOrgMapper;
	
	@Resource(name="weaponGroupMemberMapper")
	private WeaponGroupMemberMapper weaponGroupMemberMapper;
	
	public int loadVMCountByOrgId(Map<String, Object> map) {
		int count=weaponGroupMapper.countByOrgId(map);
		return count;
	}

	public List<WeaponGroupVM> loadVMListByOrgId(Map<String, Object> map) {
		List<WeaponGroupVM> ls=weaponGroupMapper.loadVMListByOrgId(map);
		return ls;
	}

	public List<WeaponGroupOrg> loadShareOrgList(int pgid) {
		List<WeaponGroupOrg> ls=weaponGroupOrgMapper.loadWeaponGroupOrgByPGId(pgid);
		return ls;
	}
	@Transactional
	public void saveWeaponGroup(WeaponGroup pg, Object[] orgIds) {
		 
		if(pg.getId()==0){
		 weaponGroupMapper.insert(pg);
		}else{ 
			weaponGroupMapper.updateByPrimaryKey(pg);
			weaponGroupOrgMapper.deleteByPGId(pg.getId());
		}

		for(Object oid : orgIds){
			Integer id=(Integer)oid;
			
			WeaponGroupOrg pgo=new WeaponGroupOrg();
			pgo.setOrgId(id.intValue());
			pgo.setWeaponGroupId(pg.getId());
			
			weaponGroupOrgMapper.insert(pgo);
		}
		
	}

	public WeaponGroup loadById(Integer id) {
		return weaponGroupMapper.selectByPrimaryKey(id);
	}

	public void deleteById(Integer id) {
		weaponGroupMapper.deleteByPrimaryKey(id);
		weaponGroupOrgMapper.deleteByPGId(id); //删除对应共享的机构
		
	}

	public int countMemberByGroupId(Integer groupId) {
		int total=weaponGroupMemberMapper.countMemberByGroupId(groupId);
		return total;
	}

	public List<WeaponGroupMemberVM> loadMemberByGroupId(
			Map<String, Object> map) {
		List<WeaponGroupMemberVM> ls=weaponGroupMemberMapper.loadMemberByGroupId(map);

		return ls;
	}
	@Transactional
	public void appendMemeber(List<WeaponGroupMember> ls) {
		for(WeaponGroupMember pgm : ls){
			Map<String,Object> m1=new HashMap<String,Object>();
			m1.put("groupId", pgm.getGroupId());
			m1.put("memberId", pgm.getWeaponId());
			
			Integer count=weaponGroupMemberMapper.existsByMemberId(m1);
			
			if(count==0){
				weaponGroupMemberMapper.insert(pgm);
			}
			
		}
		
	}

	public void delMemberById(Integer id) {
		weaponGroupMemberMapper.deleteByPrimaryKey(id);
		
	}

	public void cleanMember(Integer groupId) {
		weaponGroupMemberMapper.deleteByGroupId(groupId);
		
	}

	public List<WeaponGroupVM> loadVMListByOrgIdShared(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<WeaponGroupVM> ls=weaponGroupMapper.loadVMListByOrgIdShared(map);
		return ls;
	}
 

}
