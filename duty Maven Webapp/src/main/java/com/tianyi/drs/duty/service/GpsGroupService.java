package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.GpsGroup;
import com.tianyi.drs.duty.model.GpsGroupMember;
import com.tianyi.drs.duty.model.GpsGroupOrg;
import com.tianyi.drs.duty.viewmodel.GpsGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.GpsGroupVM;

/**
 * 定位设备分组管理接口层
 * @author lq
 *
 */
public interface GpsGroupService {
	
	/**
	 * 根据组织机构id，获取分组数据总数
	 * @param map
	 * @return
	 */
	int loadVMCountByOrgId(Map<String,Object> map);

	/**
	 * 根据组织机构id，获取定位设备分组列表并分页
	 * @param map
	 * @return
	 */
	List<GpsGroupVM> loadVMListByOrgId(Map<String,Object> map);
	
	/**
	 * 根据分组id，获取共享的下级机构数列表
	 * @param pgid
	 * @return
	 */
	List<GpsGroupOrg> loadShareOrgList(int pgid);
	
	/**
	 * 保存定位设备分组信息
	 * @param pg
	 * @param orgIds
	 */
	void saveGpsGroup(GpsGroup pg,Object[] orgIds);
	/**
	 * 根据id，获取分组详细信息
	 * @param id
	 * @return
	 */
	GpsGroup loadById(Integer id);
	
	
	/**
	 * 根据id，删除定位设备分组
	 * @param id
	 */
	void deleteById(Integer id);
	
	/**
	 * 根据分组id，查询分组成员总数
	 * @param groupId
	 * @return
	 */
	int countMemberByGroupId(Integer groupId);
	
	/**
	 * 根据分组id，获取定位设备分组成员列表
	 * @param map
	 * @return
	 */
	List<GpsGroupMemberVM> loadMemberByGroupId(Map<String,Object> map);
	
	/**
	 * 增加分组成员列表，保存到数据库
	 * @param ls
	 */
	void appendMemeber(List<GpsGroupMember> ls);
	
	/**
	 * 根据成员id，删除分组成员
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
	java.util.List<GpsGroupVM> loadVMListByOrgIdShared(
			Map<String, Object> map);
}
