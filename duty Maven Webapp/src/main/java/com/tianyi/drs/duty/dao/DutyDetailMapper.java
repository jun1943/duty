package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyDetail;

public interface DutyDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DutyDetail record);

    int insertSelective(DutyDetail record);

    DutyDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DutyDetail record);

    int updateByPrimaryKey(DutyDetail record);
}