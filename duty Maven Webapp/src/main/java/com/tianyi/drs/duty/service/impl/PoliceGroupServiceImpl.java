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
import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.model.PoliceGroupMember;
import com.tianyi.drs.duty.model.PoliceGroupOrg;
import com.tianyi.drs.duty.service.PoliceGroupService;
import com.tianyi.drs.duty.viewmodel.PoliceGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

/**
 * 警员分组服务接口实现
 * @author lq
 *
 */
@Service("policeGroupService")
public class PoliceGroupServiceImpl implements PoliceGroupService{

	@Resource(name="policeGroupMapper")
	private PoliceGroupMapper policeGroupMapper;
	
	@Resource(name="policeGroupOrgMapper")
	private PoliceGroupOrgMapper policeGroupOrgMapper;
	
	@Resource(name="policeGroupMemberMapper")
	private PoliceGroupMemberMapper policeGroupMemberMapper;
	
	/**
	 * 根据组织机构id，获取分组成员列表记录总数
	 */
	public int loadVMCountByOrgId(Map<String, Object> map) {
		
		int count=policeGroupMapper.countByOrgId(map);
		return count;
	}

	/**
	 * 根据组织机构id，获取分组成员列表并分页
	 */
	public List<PoliceGroupVM> loadVMListByOrgId(Map<String, Object> map) {
		List<PoliceGroupVM> ls=policeGroupMapper.loadVMListByOrgId(map);
		return ls;
	}

	/**
	 * 获取下级共享组织机构列表
	 */
	public List<PoliceGroupOrg> loadShareOrgList(int pgid) {
		
		List<PoliceGroupOrg> ls=policeGroupOrgMapper.loadPoliceGroupOrgByPGId(pgid);
		return ls;
	}
	
	/**
	 * 保存警员分组信息
	 */
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
	
	/**
	 * 根据id，获取警员分组信息
	 */
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

	/**
	 * 根据获取分组成员列表记录并分页
	 */
	public List<PoliceGroupMemberVM> loadMemberByGroupId(Map<String, Object> map) {
		List<PoliceGroupMemberVM> ls=policeGroupMemberMapper.loadMemberByGroupId(map);

		return ls;
	}

	/**
	 * 根据分组id，获取分组记录成员列表，记录总数
	 */
	public int countMemberByGroupId(Integer groupId) {
		int total=policeGroupMemberMapper.countMemberByGroupId(groupId);
		return total;
	}
	
	/**
	 * 增加分组成员列表
	 */
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

	/**
	 * 根据id，删除分组成员
	 */
	public void delMemberById(Integer id) {
		policeGroupMemberMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 清空分组成员列表
	 */
	public void cleanMember(Integer groupId) {
		policeGroupMemberMapper.deleteByGroupId(groupId);
	}

	/**
	 * 根据组织机构id，获取警员分组下级组织机构及成员
	 */
	public List<PoliceGroupVM> loadVMListByOrgIdShared(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceGroupVM> ls=policeGroupMapper.loadVMListByOrgIdShared(map);
		return ls;
	}

}
