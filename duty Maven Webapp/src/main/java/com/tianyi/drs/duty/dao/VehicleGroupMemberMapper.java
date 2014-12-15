package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.VehicleGroupMember;
import com.tianyi.drs.duty.viewmodel.VehicleGroupMemberVM;

/**
 * 车辆分组成员对象映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface VehicleGroupMemberMapper {
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的车辆分组成员对向
     * @param record
     * @return
     */
    int insert(VehicleGroupMember record);

    /**
     * 插入新的车辆分组成员查询对象
     * @param record
     * @return
     */
    int insertSelective(VehicleGroupMember record);

    /**
     * 根据id查询车辆分组成员对向
     * @param id
     * @return
     */
    VehicleGroupMember selectByPrimaryKey(Integer id);

    /**
     * 更新车辆分组成员对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(VehicleGroupMember record);

    /**
     * 更新车辆分组成员对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(VehicleGroupMember record);
	/**
	 * 根据分组id查询成员对象总数
	 * @param groupId
	 * @return
	 */
	int countMemberByGroupId(Integer groupId);

	/**
	 * 根据分组id，查询分组成员对象列表
	 * @param map
	 * @return
	 */
	List<VehicleGroupMemberVM> loadMemberByGroupId(Map<String, Object> map);

	/**
	 * 根据添加 的成员id，判断该分组是否在小组里面
	 * 
	 * @param m1
	 * @return
	 */
	Integer existsByMemberId(Map<String, Object> m1);

	/**
	 * 根据分组id，删除所有成员
	 * @param groupId
	 */
	void deleteByGroupId(Integer groupId);
}