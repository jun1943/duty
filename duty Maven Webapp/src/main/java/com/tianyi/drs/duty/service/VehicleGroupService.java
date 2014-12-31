package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.VehicleGroup;
import com.tianyi.drs.duty.model.VehicleGroupMember;
import com.tianyi.drs.duty.model.VehicleGroupOrg;
import com.tianyi.drs.duty.viewmodel.VehicleGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.VehicleGroupVM;
 
/**
 * 车辆分组逻辑接口层
 * @author lq
 *
 */
public interface VehicleGroupService {

	/**
	 * 根据id，获取分组列表数据总数
	 * @param map
	 * @return
	 */
	int loadVMCountByOrgId(Map<String,Object> map);

	/**
	 * 根据组织结构id，获取车辆分组列表，并分页
	 * @param map
	 * @return
	 */
	List<VehicleGroupVM> loadVMListByOrgId(Map<String,Object> map);
	
	/**
	 * 获取下级共享组织机构列表
	 * @param pgid
	 * @return
	 */
	List<VehicleGroupOrg> loadShareOrgList(int pgid);
	
	/**
	 * 保存车辆分组信息
	 * @param pg
	 * @param orgIds
	 */
	void saveVehicleGroup(VehicleGroup pg,Object[] orgIds);
	
	/**
	 * 根据id，获取车辆分组信息
	 * @param id
	 * @return
	 */
	VehicleGroup loadById(Integer id);
	
	/**
	 * 根据id，删除对象
	 * @param id
	 */
	void deleteById(Integer id);
	
	/**
	 * 根据分组id，获取成员列表总记录数
	 * @param groupId
	 * @return
	 */
	int countMemberByGroupId(Integer groupId);
	
	/**
	 * 根据分组id，获取分组成员列表对象，并分页
	 * @param map
	 * @return
	 */
	List<VehicleGroupMemberVM> loadMemberByGroupId(Map<String,Object> map);
	
	/**
	 * 增加分组成员列表到分组
	 * @param ls
	 */
	void appendMemeber(List<VehicleGroupMember> ls);
	
	/**
	 * 根据成员id，删除分组成员
	 * @param id
	 */
	void delMemberById(Integer id);
	
	/**
	 * 清空分组所有成员列表
	 * @param groupId
	 */
	void cleanMember(Integer groupId);

	/**
	 * 获取共享下级组织机构数据成员分组
	 * @param map
	 * @return
	 */
	java.util.List<VehicleGroupVM> loadVMListByOrgIdShared(
			Map<String, Object> map);

	/**
	 * 判断是否分组已存在
	 * @param map
	 * @return
	 */
	java.util.List<VehicleGroup> findByNameAndOrg(Map<String, Object> map);

}
