package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.drs.duty.dao.GpsGroupMapper;
import com.tianyi.drs.duty.dao.GpsGroupMemberMapper;
import com.tianyi.drs.duty.dao.GpsGroupOrgMapper; 
import com.tianyi.drs.duty.model.GpsGroup;
import com.tianyi.drs.duty.model.GpsGroupMember;
import com.tianyi.drs.duty.model.GpsGroupOrg; 
import com.tianyi.drs.duty.service.GpsGroupService;
import com.tianyi.drs.duty.viewmodel.GpsGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.GpsGroupVM; 
 

@Service("gpsGroupService")
public class GpsGroupServiceImpl implements GpsGroupService {

	@Resource(name="gpsGroupMapper")
	private GpsGroupMapper gpsGroupMapper;
	
	@Resource(name="gpsGroupOrgMapper")
	private GpsGroupOrgMapper gpsGroupOrgMapper;
	
	@Resource(name="gpsGroupMemberMapper")
	private GpsGroupMemberMapper gpsGroupMemberMapper;
	
	public int loadVMCountByOrgId(Map<String, Object> map) {
		int count=gpsGroupMapper.countByOrgId(map);
		return count;
	}

	public List<GpsGroupVM> loadVMListByOrgId(Map<String, Object> map) {
		List<GpsGroupVM> ls=gpsGroupMapper.loadVMListByOrgId(map);
		return ls;
	}

	public List<GpsGroupOrg> loadShareOrgList(int pgid) {
		List<GpsGroupOrg> ls=gpsGroupOrgMapper.loadGpsGroupOrgByPGId(pgid);
		return ls;
	}
	@Transactional
	public void saveGpsGroup(GpsGroup pg, Object[] orgIds) {
		if(pg.getId()==0){
			gpsGroupMapper.insert(pg);
		}else{
			gpsGroupMapper.updateByPrimaryKey(pg);
			gpsGroupOrgMapper.deleteByPGId(pg.getId());
		}

		for(Object oid : orgIds){
			Integer id=(Integer)oid;
			
			GpsGroupOrg pgo=new GpsGroupOrg();
			pgo.setOrgId(id.intValue());
			pgo.setGpsGroupId(pg.getId());
			
			gpsGroupOrgMapper.insert(pgo);
		}
		
	}

	public GpsGroup loadById(Integer id) {
		return gpsGroupMapper.selectByPrimaryKey(id);
	}

	public void deleteById(Integer id) {
		gpsGroupMapper.deleteByPrimaryKey(id);
		gpsGroupOrgMapper.deleteByPGId(id); //删除对应共享的机构
		
	}

	public int countMemberByGroupId(Integer groupId) {
		int total=gpsGroupMemberMapper.countMemberByGroupId(groupId);
		return total;
	}

	public List<GpsGroupMemberVM> loadMemberByGroupId(
			Map<String, Object> map) {
		List<GpsGroupMemberVM> ls=gpsGroupMemberMapper.loadMemberByGroupId(map);

		return ls;
	}
	@Transactional
	public void appendMemeber(List<GpsGroupMember> ls) {
		for(GpsGroupMember pgm : ls){
			Map<String,Object> m1=new HashMap<String,Object>();
			m1.put("groupId", pgm.getGroupId());
			m1.put("memberId", pgm.getGpsId());
			
			Integer count=gpsGroupMemberMapper.existsByMemberId(m1);
			
			if(count==0){
				gpsGroupMemberMapper.insert(pgm);
			}
			
		}
		
	}

	public void delMemberById(Integer id) {
		gpsGroupMemberMapper.deleteByPrimaryKey(id);
		
	}

	public void cleanMember(Integer groupId) {
		gpsGroupMemberMapper.deleteByGroupId(groupId);
		
	}

	public List<GpsGroupVM> loadVMListByOrgIdShared(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<GpsGroupVM> ls=gpsGroupMapper.loadVMListByOrgIdShared(map);
		return ls;
	}
 

}
