package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.PoliceType;

public interface PoliceTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PoliceType record);

    int insertSelective(PoliceType record);

    PoliceType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PoliceType record);

    int updateByPrimaryKey(PoliceType record);
}