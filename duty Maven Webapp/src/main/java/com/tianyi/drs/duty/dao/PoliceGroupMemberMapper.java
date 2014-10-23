package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceGroupMember;
import com.tianyi.drs.duty.viewmodel.PoliceGroupMemberVM;

@MyBatisRepository
public interface PoliceGroupMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceGroupMember record);

    int insertSelective(PoliceGroupMember record);

    PoliceGroupMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceGroupMember record);

    int updateByPrimaryKey(PoliceGroupMember record);
    
    int countMemberByGroupId(Integer groupId);
    
    List<PoliceGroupMemberVM> loadMemberByGroupId(Map<String, Object> map);
    
    int existsByMemberId(Map<String, Object> map);
}