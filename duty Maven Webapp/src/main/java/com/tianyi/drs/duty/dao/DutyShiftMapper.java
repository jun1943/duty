package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyShift;

public interface DutyShiftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyShift record);

    int insertSelective(DutyShift record);

    DutyShift selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyShift record);

    int updateByPrimaryKey(DutyShift record);
}