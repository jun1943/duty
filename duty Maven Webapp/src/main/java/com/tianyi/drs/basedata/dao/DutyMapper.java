package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.Duty;

public interface DutyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Duty record);

    int insertSelective(Duty record);

    Duty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Duty record);

    int updateByPrimaryKey(Duty record);
}