package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.GpsGroupMember;
import com.tianyi.drs.duty.viewmodel.GpsGroupMemberVM;

/**
 * 定位设备组成员映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface GpsGroupMemberMapper {
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的定位设备组成员对象
     * @param record
     * @return
     */
    int insert(GpsGroupMember record);

    /**
     * 插入新的定位设备组成员对象
     * @param record
     * @return
     */
    int insertSelective(GpsGroupMember record);

    /**
     * 根据id获取定位设备分组对象
     * @param id
     * @return
     */
    GpsGroupMember selectByPrimaryKey(Integer id);

    /**
     * 更新定位设备分组成员对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GpsGroupMember record);

    /**
     * 更新定位设备分组成员对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(GpsGroupMember record);

    /**
     * 根据分组id获取组成员数据数量
     * @param groupId
     * @return
     */
	int countMemberByGroupId(Integer groupId);

	/**
	 * 根据分组id获取分组成员列表
	 * @param map
	 * @return
	 */
	List<GpsGroupMemberVM> loadMemberByGroupId(Map<String, Object> map);

	/**
	 * 判断该组成员是否存在
	 * @param m1
	 * @return
	 */
	Integer existsByMemberId(Map<String, Object> m1);

	/**
	 * 根据id删除分组
	 * @param groupId
	 */
	void deleteByGroupId(Integer groupId);
}