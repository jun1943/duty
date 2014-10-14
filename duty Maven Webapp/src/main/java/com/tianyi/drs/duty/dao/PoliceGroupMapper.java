package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.PoliceGroup;

public interface PoliceGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PoliceGroup record);

    int insertSelective(PoliceGroup record);

    PoliceGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PoliceGroup record);

    int updateByPrimaryKey(PoliceGroup record);
}