package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.model.PoliceGroupMember;
import com.tianyi.drs.duty.model.PoliceGroupOrg;
import com.tianyi.drs.duty.viewmodel.PoliceGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

/**
 * 警员分组逻辑接口层
 * @author lq
 *
 */
public interface PoliceGroupService {

	/**
	 * 根据组织机构id，获取分组记录总数
	 * @param map
	 * @return
	 */
	int loadVMCountByOrgId(Map<String,Object> map);

	/**
	 * 根据组织机构id，获取警员分组列表数据，并分页 
	 * @param map
	 * @return
	 */
	List<PoliceGroupVM> loadVMListByOrgId(Map<String,Object> map);
	
	/**
	 * 获取下级共享组织机构数据
	 * @param pgid
	 * @return
	 */
	List<PoliceGroupOrg> loadShareOrgList(int pgid);
	
	/**
	 * 保存警员分组对象信息
	 * @param pg
	 * @param orgIds
	 */
	void savePoliceGroup(PoliceGroup pg,Object[] orgIds);
	
	/**
	 * 根据id，获取警员分组信息详细
	 * @param id
	 * @return
	 */
	PoliceGroup loadById(Integer id);
	
	/**
	 * 根据id删除警员分组对象
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
	List<PoliceGroupMemberVM> loadMemberByGroupId(Map<String,Object> map);
	
	/**
	 * 增加成员列表到分组
	 * @param ls
	 */
	void appendMemeber(List<PoliceGroupMember> ls);
	
	/**
	 * 根据成员id，删除成员对象
	 * @param id
	 */
	void delMemberById(Integer id);
	
	/**
	 * 清空所有分组成员数据
	 * @param groupId
	 */
	void cleanMember(Integer groupId);

	/**
	 * 获取共享下级组织机构数据成员分组
	 * @param map
	 * @return
	 */
	java.util.List<PoliceGroupVM> loadVMListByOrgIdShared(
			Map<String, Object> map);

}
