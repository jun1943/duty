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
 
/**
 * 武器分组接口实现
 * @author lq
 *
 */
@Service("weaponGroupService")
public class WeaponGroupServiceImpl implements WeaponGroupService  {

	/**
	 * 初始化武器分无映射
	 */
	@Resource(name="weaponGroupMapper")
	private WeaponGroupMapper weaponGroupMapper;
	
	@Resource(name="weaponGroupOrgMapper")
	private WeaponGroupOrgMapper weaponGroupOrgMapper;
	
	@Resource(name="weaponGroupMemberMapper")
	private WeaponGroupMemberMapper weaponGroupMemberMapper;
	
	/**
	 * 根据组织机构id，获取武器分组记录总数
	 */
	public int loadVMCountByOrgId(Map<String, Object> map) {
		int count=weaponGroupMapper.countByOrgId(map);
		return count;
	}

	/**
	 * 根据组织机构id，获取武器分组记录列表
	 */
	public List<WeaponGroupVM> loadVMListByOrgId(Map<String, Object> map) {
		List<WeaponGroupVM> ls=weaponGroupMapper.loadVMListByOrgId(map);
		return ls;
	}

	/**
	 * 获取当前组织机构的下级机构，用于共享到下级
	 */
	public List<WeaponGroupOrg> loadShareOrgList(int pgid) {
		List<WeaponGroupOrg> ls=weaponGroupOrgMapper.loadWeaponGroupOrgByPGId(pgid);
		return ls;
	}
	/**
	 * 保存武器分组信息
	 */
	@Transactional
	public void saveWeaponGroup(WeaponGroup pg, Object[] orgIds) {
		 
		if(pg.getId()==0){
		 weaponGroupMapper.insert(pg);
		}else{ 
			weaponGroupMapper.updateByPrimaryKey(pg);
			weaponGroupOrgMapper.deleteByPGId(pg.getId());
		}

		/**
		 * 保存武器分组组织机构对应关系
		 */
		for(Object oid : orgIds){
			Integer id=(Integer)oid;
			
			WeaponGroupOrg pgo=new WeaponGroupOrg();
			pgo.setOrgId(id.intValue());
			pgo.setWeaponGroupId(pg.getId());
			
			weaponGroupOrgMapper.insert(pgo);
		}
		
	}

	/**
	 * 根据id获取武器分组对象信息
	 */
	public WeaponGroup loadById(Integer id) {
		return weaponGroupMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据id，删除武器分组
	 */
	public void deleteById(Integer id) {
		weaponGroupMapper.deleteByPrimaryKey(id);
		weaponGroupOrgMapper.deleteByPGId(id); //删除对应共享的机构
		
	}

	/**
	 * 根据分组id，获取分组成员总记录数
	 */
	public int countMemberByGroupId(Integer groupId) {
		int total=weaponGroupMemberMapper.countMemberByGroupId(groupId);
		return total;
	}

	/**
	 * 根据分组id，获取分组成员列表记录
	 */
	public List<WeaponGroupMemberVM> loadMemberByGroupId(
			Map<String, Object> map) {
		List<WeaponGroupMemberVM> ls=weaponGroupMemberMapper.loadMemberByGroupId(map);

		return ls;
	}
	/**
	 * 增加武器分组成员
	 */
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
	/**
	 * 判断分组是否已经存在
	 */
	public List<WeaponGroup> findByNameAndOrg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return weaponGroupMapper.findByNameAndOrg(map);
	}
 

}
