package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.drs.duty.dao.PoliceGroupMapper;
import com.tianyi.drs.duty.dao.PoliceGroupMemberMapper;
import com.tianyi.drs.duty.dao.PoliceGroupOrgMapper;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.model.PoliceGroupMember;
import com.tianyi.drs.duty.model.PoliceGroupOrg;
import com.tianyi.drs.duty.service.PoliceGroupService;
import com.tianyi.drs.duty.viewmodel.PoliceGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

@Service("policeGroupService")
public class PoliceGroupServiceImpl implements PoliceGroupService{

	@Resource(name="policeGroupMapper")
	private PoliceGroupMapper policeGroupMapper;
	
	@Resource(name="policeGroupOrgMapper")
	private PoliceGroupOrgMapper policeGroupOrgMapper;
	
	@Resource(name="policeGroupMemberMapper")
	private PoliceGroupMemberMapper policeGroupMemberMapper;
	
	public int loadVMCountByOrgId(Map<String, Object> map) {
		
		int count=policeGroupMapper.countByOrgId(map);
		return count;
	}

	public List<PoliceGroupVM> loadVMListByOrgId(Map<String, Object> map) {
		List<PoliceGroupVM> ls=policeGroupMapper.loadVMListByOrgId(map);
		return ls;
	}

	public List<PoliceGroupOrg> loadShareOrgList(int pgid) {
		
		List<PoliceGroupOrg> ls=policeGroupOrgMapper.loadPoliceGroupOrgByPGId(pgid);
		return ls;
	}
	
	@Transactional
	public void savePoliceGroup(PoliceGroup pg, Object[] orgIds) {
		if(pg.getId()==0){
			policeGroupMapper.insert(pg);
		}else{
			policeGroupMapper.updateByPrimaryKey(pg);
			policeGroupOrgMapper.deleteByPGId(pg.getId());
		}

		for(Object oid : orgIds){
			Integer id=(Integer)oid;
			
			PoliceGroupOrg pgo=new PoliceGroupOrg();
			pgo.setOrgId(id.intValue());
			pgo.setPoliceGroupId(pg.getId());
			
			policeGroupOrgMapper.insert(pgo);
		}
	}
	
	public PoliceGroup loadById(Integer id){
		return policeGroupMapper.selectByPrimaryKey(id);
	}
	/***
	 * 删除警员组
	 * id= 警员组id
	 */
	@Transactional
	public void deleteById(Integer id){
		policeGroupMapper.deleteByPrimaryKey(id);
		policeGroupOrgMapper.deleteByPGId(id); //删除对应共享的机构
		
	}

	public List<PoliceGroupMemberVM> loadMemberByGroupId(Map<String, Object> map) {
		List<PoliceGroupMemberVM> ls=policeGroupMemberMapper.loadMemberByGroupId(map);

		return ls;
	}

	public int countMemberByGroupId(Integer groupId) {
		int total=policeGroupMemberMapper.countMemberByGroupId(groupId);
		return total;
	}
	
	@Transactional
	public void appendMemeber(List<PoliceGroupMember> ls) {
		for(PoliceGroupMember pgm : ls){
			Map<String,Object> m1=new HashMap<String,Object>();
			m1.put("groupId", pgm.getGroupId());
			m1.put("memberId", pgm.getPoliceId());
			
			Integer count=policeGroupMemberMapper.existsByMemberId(m1);
			
			if(count==0){
				policeGroupMemberMapper.insert(pgm);
			}
			
		}
		
	}
	

	
}
