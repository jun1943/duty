package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.Duty;

public interface DutyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Duty record);

    int insertSelective(Duty record);

    Duty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Duty record);

    int updateByPrimaryKey(Duty record);
}