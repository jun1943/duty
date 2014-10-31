package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyItemType;

public interface DutyItemTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyItemType record);

    int insertSelective(DutyItemType record);

    DutyItemType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyItemType record);

    int updateByPrimaryKey(DutyItemType record);
}