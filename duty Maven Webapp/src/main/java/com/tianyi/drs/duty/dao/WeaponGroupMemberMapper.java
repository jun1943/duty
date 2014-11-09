package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.WeaponGroupMember;
import com.tianyi.drs.duty.viewmodel.WeaponGroupMemberVM;
@MyBatisRepository
public interface WeaponGroupMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeaponGroupMember record);

    int insertSelective(WeaponGroupMember record);

    WeaponGroupMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeaponGroupMember record);

    int updateByPrimaryKey(WeaponGroupMember record);

	int countMemberByGroupId(Integer groupId);

	Integer existsByMemberId(Map<String, Object> m1);

	void deleteByGroupId(Integer groupId);

	List<WeaponGroupMemberVM> loadMemberByGroupId(Map<String, Object> map);
}