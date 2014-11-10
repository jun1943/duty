package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.VehicleGroupMember;
import com.tianyi.drs.duty.viewmodel.VehicleGroupMemberVM;
@MyBatisRepository
public interface VehicleGroupMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleGroupMember record);

    int insertSelective(VehicleGroupMember record);

    VehicleGroupMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleGroupMember record);

    int updateByPrimaryKey(VehicleGroupMember record);

	int countMemberByGroupId(Integer groupId);

	List<VehicleGroupMemberVM> loadMemberByGroupId(Map<String, Object> map);

	Integer existsByMemberId(Map<String, Object> m1);

	void deleteByGroupId(Integer groupId);
}