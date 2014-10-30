package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.DutyItem;

public interface DutyItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyItem record);

    int insertSelective(DutyItem record);

    DutyItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyItem record);

    int updateByPrimaryKey(DutyItem record);
}