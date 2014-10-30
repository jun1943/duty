package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyItem;

public interface DutyItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyItem record);

    int insertSelective(DutyItem record);

    DutyItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyItem record);

    int updateByPrimaryKey(DutyItem record);
}