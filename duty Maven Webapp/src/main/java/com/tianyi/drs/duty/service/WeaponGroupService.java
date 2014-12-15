package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.WeaponGroup;
import com.tianyi.drs.duty.model.WeaponGroupMember;
import com.tianyi.drs.duty.model.WeaponGroupOrg;
import com.tianyi.drs.duty.viewmodel.WeaponGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.WeaponGroupVM;

/**
 * 武器分组逻辑接口
 * @author lq
 *
 */
public interface WeaponGroupService {
	/**
	 * 根据组织机构id，获取武器分组记录总数
	 * @param map
	 * @return
	 */
	int loadVMCountByOrgId(Map<String,Object> map);

	/**
	 * 根据组织机构id，获取武器分组记录列表，并分页
	 * @param map
	 * @return
	 */
	List<WeaponGroupVM> loadVMListByOrgId(Map<String,Object> map);
	
	/**
	 * 获取共享组织机构
	 * @param pgid
	 * @return
	 */
	List<WeaponGroupOrg> loadShareOrgList(int pgid);
	
	/**
	 * 保存武器分组信息
	 * @param pg
	 * @param orgIds
	 */
	void saveWeaponGroup(WeaponGroup pg,Object[] orgIds);
	
	/**
	 * 根据id获取武器分组信息
	 * @param id
	 * @return
	 */
	WeaponGroup loadById(Integer id);
	
	/**
	 * 根据id，删除武器分组信息
	 * @param id
	 */
	void deleteById(Integer id);
	
	/**
	 * 根据分组id，获取分组成员列表总数
	 * @param groupId
	 * @return
	 */
	int countMemberByGroupId(Integer groupId);
	
	/**
	 * 根据分组id，获取分组成员列表，并分页
	 * @param map
	 * @return
	 */
	List<WeaponGroupMemberVM> loadMemberByGroupId(Map<String,Object> map);
	
	/**
	 * 增加武器分组成员列表到分组成员
	 * @param ls
	 */
	void appendMemeber(List<WeaponGroupMember> ls);
	
	/**
	 * 根据成员id，删除成员
	 * @param id
	 */
	void delMemberById(Integer id);
	
	/**
	 * 根据分组id，清空分组成员列表
	 * @param groupId
	 */
	void cleanMember(Integer groupId);

	/**
	 * 获取共享下级组织机构数据成员分组
	 * @param map
	 * @return
	 */
	java.util.List<WeaponGroupVM> loadVMListByOrgIdShared(
			Map<String, Object> map);
}
