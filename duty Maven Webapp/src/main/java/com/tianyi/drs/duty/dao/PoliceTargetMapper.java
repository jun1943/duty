package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.PoliceTarget;

public interface PoliceTargetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PoliceTarget record);

    int insertSelective(PoliceTarget record);

    PoliceTarget selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PoliceTarget record);

    int updateByPrimaryKey(PoliceTarget record);
}