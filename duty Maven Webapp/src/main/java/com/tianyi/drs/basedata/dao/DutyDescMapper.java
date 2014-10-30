package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.DutyDesc;

public interface DutyDescMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyDesc record);

    int insertSelective(DutyDesc record);

    DutyDesc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyDesc record);

    int updateByPrimaryKey(DutyDesc record);
}