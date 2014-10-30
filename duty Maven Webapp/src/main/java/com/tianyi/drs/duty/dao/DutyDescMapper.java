package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyDesc;

public interface DutyDescMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyDesc record);

    int insertSelective(DutyDesc record);

    DutyDesc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyDesc record);

    int updateByPrimaryKey(DutyDesc record);
}