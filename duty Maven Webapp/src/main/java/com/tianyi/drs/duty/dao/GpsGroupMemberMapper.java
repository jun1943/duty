package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.GpsGroupMember;
import com.tianyi.drs.duty.viewmodel.GpsGroupMemberVM;
@MyBatisRepository
public interface GpsGroupMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GpsGroupMember record);

    int insertSelective(GpsGroupMember record);

    GpsGroupMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GpsGroupMember record);

    int updateByPrimaryKey(GpsGroupMember record);

	int countMemberByGroupId(Integer groupId);

	List<GpsGroupMemberVM> loadMemberByGroupId(Map<String, Object> map);

	Integer existsByMemberId(Map<String, Object> m1);

	void deleteByGroupId(Integer groupId);
}