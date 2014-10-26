package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyDetail;

public interface DutyDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyDetail record);

    int insertSelective(DutyDetail record);

    DutyDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyDetail record);

    int updateByPrimaryKey(DutyDetail record);
}