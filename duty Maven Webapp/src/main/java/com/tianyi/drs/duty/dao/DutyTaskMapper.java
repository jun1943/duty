package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyTask;

public interface DutyTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DutyTask record);

    int insertSelective(DutyTask record);

    DutyTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DutyTask record);

    int updateByPrimaryKey(DutyTask record);
}