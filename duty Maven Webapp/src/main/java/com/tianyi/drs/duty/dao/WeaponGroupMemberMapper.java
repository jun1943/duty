package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.WeaponGroupMember;
import com.tianyi.drs.duty.viewmodel.WeaponGroupMemberVM;

/**
 * 武器分组成员对象映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface WeaponGroupMemberMapper {
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的武器分组成员对象
     * @param record
     * @return
     */
    int insert(WeaponGroupMember record);
 
    /**
     * 插入新的武器分组成员对象
     * @param record
     * @return
     */
    int insertSelective(WeaponGroupMember record);
    
    /**
     * 根据id获取武器分组成员变量对象
     * @param id
     * @return
     */
    WeaponGroupMember selectByPrimaryKey(Integer id);

    /**
     * 更新武器分组成员变量对象
     * @param id
     * @return
     */
    int updateByPrimaryKeySelective(WeaponGroupMember record);

    /**
     * 更新武器分组成员变量对象
     * @param id
     * @return
     */
    int updateByPrimaryKey(WeaponGroupMember record);

    /**
     * 根据分组id，查询武器分组成员对象总记录数
     * @param groupId
     * @return
     */
	int countMemberByGroupId(Integer groupId);

	/**
	 * 判断是否分组中存在该成员变量
	 * @param m1
	 * @return
	 */
	Integer existsByMemberId(Map<String, Object> m1);

	/**
	 * 根据分组id，删除成员分组对象
	 * @param groupId
	 */
	void deleteByGroupId(Integer groupId);

	/**
	 * 根据分组id，获取分组成员列表
	 * @param map
	 * @return
	 */
	List<WeaponGroupMemberVM> loadMemberByGroupId(Map<String, Object> map);
}